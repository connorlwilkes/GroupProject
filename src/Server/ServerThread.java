package Server;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ServerThread class for the Server of the minigame game. Takes an incoming connection and creates a new thread.
 *
 * @author Florence
 * @version 9/3/2018
 */
@SuppressWarnings("InfiniteLoopStatement")
public class ServerThread implements Runnable {

    private final static Logger auditLogger = Logger.getLogger("requests");
    private final static Logger errorLogger = Logger.getLogger("errors");
    public Socket connection;
    public User currentUser;
    private Server server;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Player player;

    /**
     * Constructor for the ServerThread class, reuses the fields connection and server as arguments
     *
     * @param connection Socket associated with the Thread
     * @param server     server associated with this thread
     */
    public ServerThread(Socket connection, Server server) {
        this.connection = connection;
        this.server = server;
    }
    
    /**
     * Constructor for the ServerThread class, reuses the field connection as an argument
     *
     * @param connection Socket associated with the Thread
     */
      public ServerThread(Socket connection) {
        this.connection = connection;
    }
   
    
    /**
     * Getter for server
     * 
     * @return the server
     */
    public Server getServer(){ return server;}
    
    /**
     * Helper function to close the server
     *
     * @param socket socket to close
     */
    public static void closeSilently(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                errorLogger.log(Level.SEVERE, "could not close connection", ex);
            }
        }
    }

    /**
     * Getter for the objectOutputStream
     *
     * @return outputStream of the thread
     */
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Getter for the objectInputStream
     *
     * @return inputStream of the thread
     */
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Run method for the thread
     */
    @Override
    public void run() {
        try {
            auditLogger.info("Connected to: " + connection.getRemoteSocketAddress() + " on " + new Date());
            setUp();
            while (connection.isConnected()) {
                logInOrSignUp();
                processRequests();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        } finally {
            try {
                auditLogger.info("Closing connection to " + connection.getRemoteSocketAddress() + " on " + new Date());
                connection.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Method to sets up the server's variables
     *
     * @throws IOException ClassNotFoundException
     */
    public void setUp() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.flush();
        outputStream = new ObjectOutputStream(connection.getOutputStream());
        outputStream.flush();
        inputStream = new ObjectInputStream(connection.getInputStream());
    }

    /**
     * Method to process log in or sign up requests
     *
     * @throws IOException ClassNotFoundException
     */
    private void logInOrSignUp() throws IOException, ClassNotFoundException {
        while (true) {
            ServerProtocol request = (ServerProtocol) inputStream.readObject();
            String requestType = request.type;
            if (requestType.startsWith("login")) {
                loginUser(request.message[0], request.message[1]);
                processRequests();
            } else if (requestType.startsWith("create-account")) {
                setUpAccount(request.message[0], request.message[1]);
            } else if (connection.isClosed()) {
                break;
            } else if (request.type.startsWith("close")) {
                connection.close();
            }
        }
    }

    /**
     * Method to processes network requests
     * @throws IOException ClassNotFoundException
     */
    private void processRequests() throws IOException, ClassNotFoundException {
        while (true) {
            ServerProtocol request = (ServerProtocol) inputStream.readObject();
            if (request.type.startsWith("join-lobby")) {
                joinLobby(Integer.valueOf(request.message[0]));
            } else if (request.type.startsWith("logout")) {
                logOut();
            } else {
                ServerProtocol message = new ServerProtocol("error", "unknown request");
                outputStream.writeObject(message);
                outputStream.flush();
            }
        }
    }

    /**
     * Method to log in a client
     *
     * @param username The username of the user
     * @param password The password of the user
     * @throws IOException
     */
    private synchronized void loginUser(String username, String password) throws IOException {
        if (server.getActiveUsers().contains(this)) {
            ServerProtocol response = new ServerProtocol("false", "user already logged in");
            outputStream.writeObject(response);
            outputStream.flush();
            return;
        }
        ServerProtocol response = LoginUser.CheckLogin(new User(username, password));
        currentUser = new User(username, password);
        if (response.type.equals("true")) {
            currentUser = new User(username, password);
            server.addActiveUser(this);
        }
        outputStream.writeObject(response);
        outputStream.flush();
    }


    /**
     * Method to set up an account
     *
     * @param username The username of the user
     * @param password The password of the user
     * @throws IOException
     */
    private synchronized void setUpAccount(String username, String password) throws IOException {
        currentUser = new User(username, password);
        try {
            ServerProtocol response = RegisterUser.checkUser(currentUser);
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to join a lobby
     *
     * @param lobbyNumber The lobby id number
     * @throws IOException ClassNotFoundException
     */
    private void joinLobby(int lobbyNumber) throws IOException, ClassNotFoundException {
        if (lobbyNumber > 4 || 1 > lobbyNumber) {
            ServerProtocol message = new ServerProtocol("false", "Lobby does not exist");
            outputStream.writeObject(message);
            outputStream.flush();
            return;
        }
        GameLobby lobby = server.getLobbies().get(lobbyNumber - 1);
        if (lobby.isFull()) {
            ServerProtocol message = new ServerProtocol("false", "Lobby is full");
            outputStream.writeObject(message);
            outputStream.flush();
        } else {
            this.player = new Player(this, lobby, currentUser);
            lobby.addPlayer(this.player);
            ServerProtocol message = new ServerProtocol("true", "Successfully joined lobby");
            outputStream.writeObject(message);
            outputStream.flush();
            player.processGameRequests();
        }
    }

    /**
     * Method to log out a user
     *
     * @throws IOException
     */
    private synchronized void logOut() throws IOException {
        ServerProtocol message = new ServerProtocol("true", "logged out successfully");
        outputStream.writeObject(message);
        outputStream.flush();
        try {
            Thread.sleep(500);
            connection.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}

