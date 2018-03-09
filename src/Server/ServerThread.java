package Server;

import Server.Login.RegisterUser;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ServerThread class for the Server of the minigame game. Takes an incoming connection and creates a new thread.
 *
 * @author Florence
 * @version 9/3/2018
 */
public class ServerThread implements Runnable {

    private final static Logger auditLogger = Logger.getLogger("requests");
    private final static Logger errorLogger = Logger.getLogger("errors");
    private Socket connection;
    private Server server;
    private BufferedReader reader;
    private BufferedWriter writer;
    private User currentUser;


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

    public BufferedWriter getWriter() {
        return writer;
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
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                System.out.println("here");
                auditLogger.info("Closing connection to " + connection.getRemoteSocketAddress() + " on " + new Date());
                connection.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Sets up the server's variables
     * @throws IOException
     */
    public void setUp() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.write("hello\r\n");
        writer.flush();
    }

    /**
     * Processes log in or sign up requests
     * @throws IOException
     */
    private void logInOrSignUp() throws IOException {
        while (true) {
            String line = reader.readLine();
            Optional<String> optional = Optional.ofNullable(line);
            while (!(optional.isPresent())) {
                line = reader.readLine();
            }
            if ("login".startsWith(line)) {
                loginUser();
                break;
            } else if ("createAccount".startsWith(line)) {
                setUpAccount();
                break;
            } else if (connection.isClosed()) {
                System.out.println("closed connection");
                break;
            }
        }
    }

    /**
     * Processes network requests
     */
    private void processRequests() throws IOException {
        while (true) {
            String line = reader.readLine();
            Optional<String> optional = Optional.ofNullable(line);
            while (!(optional.isPresent())) {
                line = reader.readLine();
            }
            if ("enterLobby".startsWith(line)) {
                joinLobby();
            } else if ("leaveLobby".startsWith(line)) {

            } else if ("logout".startsWith(line)) {
                logOut();
                break;
            }
        }
    }

    /**
     * Logs in a client
     * @throws IOException
     */
    private synchronized void loginUser() throws IOException {
        String username = reader.readLine();
        String password = reader.readLine();
        if (!(server.checkPassword(username, password))) {
            writer.write("Error: Username or password incorrect\r\n");
            writer.flush();
        } else {
            currentUser = new User(username, password);
            server.addUser(currentUser);
            writer.write("Success: User " + username + " logged in\r\n");
            writer.flush();
        }

    }

    /**
     * Sets up an account
     * @throws IOException
     */
    private synchronized void setUpAccount() throws IOException {
        String username = reader.readLine();
        String password = reader.readLine();
        writer.write(RegisterUser.checkUser(new User(username, password)));
        writer.flush();
        connection.close();
    }

    /**
     * Joins a lobby
     * @throws IOException
     */
    private void joinLobby() throws IOException {
        int lobbyNumber = reader.read();
        if (lobbyNumber > 4 || 1 > lobbyNumber) {
            System.err.println("lobby does not exist");
            return;
        }
        GameLobby lobby = server.getLobbies().get(lobbyNumber - 1);
            if (lobby.isFull()) {
                System.err.println("lobby is full");
            } else {
                Player player = new Player(this, lobby);
                lobby.addPlayer(player);
            }
        processLobbyRequests(lobby);
    }


    private void processLobbyRequests(GameLobby lobby) throws IOException {
        while (lobby.isRunning()) {
            String line = reader.readLine();
            Optional<String> optional = Optional.ofNullable(line);
            while (!(optional.isPresent())) {
                line = reader.readLine();
            }
            if (line.startsWith("getMessage")) {

            } else if (line.startsWith("sendAnswer")) {

            } else if (line.startsWith("getQuestions")) {

            } else if (line.startsWith("getScores")) {

            } else if (line.startsWith("quitLobby")) {
                break;
            } else {
                System.err.println("unknown request");
                connection.close();
                break;
            }
        }
    }

    /**
     * Logs out a user
     * @throws IOException
     */
    private synchronized void logOut() throws IOException {
        server.removeUser(currentUser);
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

