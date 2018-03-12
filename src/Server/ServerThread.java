package Server;

import java.io.*;
import java.net.Socket;
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
    private Socket connection;
    private Server server;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private User currentUser;
    private Player player;


    /**
     * Constructor for testing purposes
     *
     * @param connection Socket associated with the Thread
     */
    public ServerThread(Socket connection) {
        this.connection = connection;
    }

    /**
     * Constructor for the ServerThread class
     *
     * @param connection Socket associated with the Thread
     * @param server     server associated with this thread
     */
    public ServerThread(Socket connection, Server server) {
        this.connection = connection;
        this.server = server;
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
            if (currentUser != null) {
                server.removeUser(currentUser);
            }
            if (player != null) {
                player.getLobby().removePlayer(player);
            }
            try {
                auditLogger.info("Closing connection to " + connection.getRemoteSocketAddress() + " on " + new Date());
                connection.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Sets up the server's variables
     *
     * @throws IOException ClassNotFoundException
     */
    public void setUp() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        outputStream = new ObjectOutputStream(connection.getOutputStream());
        inputStream = new ObjectInputStream(connection.getInputStream());
        ServerProtocol welcome = new ServerProtocol("welcome", "welcome to the server");
        System.out.println(welcome);
        outputStream.writeObject(welcome);
        outputStream.flush();
    }

    /**
     * Processes log in or sign up requests
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
                connection.close();
            } else if (connection.isClosed()) {
                break;
            }
        }
    }

    /**
     * Processes network requests
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
     * Logs in a client
     *
     * @throws IOException
     */
    private synchronized void loginUser(String username, String password) throws IOException {
        //TODO: Code for loginuser from database - Sophia
        if (!(server.checkUsername(username))) {
            ServerProtocol response = new ServerProtocol("false", "User does not exist");
            outputStream.writeObject(response);
            outputStream.flush();
        } else if (!(server.checkPassword(username, password))) {
            ServerProtocol response = new ServerProtocol("false", "Username or password does not match");
            outputStream.writeObject(response);
            outputStream.flush();
        } else {
            currentUser = new User(username, password);
            server.addActiveUser(currentUser);
            ServerProtocol response = new ServerProtocol("true", "Success: User " + username + " logged in");
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    /**
     * Sets up an account
     *
     * @throws IOException
     */
    private synchronized void setUpAccount(String username, String password) throws IOException {
        //TODO: add database add and check for adding a new user - Sophia
        if (username.startsWith("fail") || password.startsWith("fail")) {
            ServerProtocol response = new ServerProtocol("false", " test error");
            outputStream.writeObject(response);
            outputStream.flush();
        } else {
            ServerProtocol response = new ServerProtocol("true", "User: " + username + " created");
            outputStream.writeObject(response);
            outputStream.flush();
        }
        server.addActiveUser(new User(username, password));
    }

    /**
     * Joins a lobby
     *
     * @throws IOException
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
            lobby.addPlayer(player);
            ServerProtocol message = new ServerProtocol("true", "Successfully joined lobby");
            outputStream.writeObject(message);
            outputStream.flush();
            player.processGameRequests();
        }
    }

    /**
     * Logs out a user
     *
     * @throws IOException
     */
    private synchronized void logOut() throws IOException {
        server.removeUser(currentUser);
        ServerProtocol message = new ServerProtocol("true", "logged out successfully");
        outputStream.writeObject(message);
        outputStream.flush();
        connection.close();
    }

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
}

