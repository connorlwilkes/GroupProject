/**
 * ServerThread class for the Server of the minigame game. Takes an incoming connection and creates a new thread.
 *
 * @author Connor Wilkes
 * @version 1/3/2018
 */

package Server;

import Server.Login.RegisterUser;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    public static boolean isValidLength(String word) {
        return (word.length() >= 5 && word.length() <= 20);
    }

    public Socket getConnection() {
        return connection;
    }

    /**
     * Run method for the thread
     *
     * @return null
     */
    @Override
    public void run() {
        try {
            auditLogger.info("Connected to: " + connection.getRemoteSocketAddress() + " on " + new Date());
            setUp();
            logInOrSignUp();
            if (!(connection.isClosed())) {
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

            }
        }
    }

    public void setUp() throws IOException {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.write("hello\r\n");
        writer.flush();
    }

    private void logInOrSignUp() throws IOException {
        while (true) {
            String line = reader.readLine();
            while (line == null) {
                line = reader.readLine();
            }
            if (line.startsWith("login")) {
                loginUser();
                break;
            } else if (line.startsWith("createAccount")) {
                setUpAccount();
                connection.close();
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
            while (line == null) {
                line = reader.readLine();
            }
            if (line.startsWith("enterLobby")) {
                joinLobby();
            }
        }
    }

    private void loginUser() throws IOException {
        String username = reader.readLine();
        String password = reader.readLine();
        if (!(server.checkPassword(username, password))) {
            writer.write("Error: Username or password incorrect\r\n");
            writer.flush();
        } else {
            currentUser = new User(username, password);
            writer.write("Success: User " + username + " logged in\r\n");
            writer.flush();
        }

    }

    private void setUpAccount() throws IOException {
        String username = reader.readLine();
        String password = reader.readLine();
        if (server.checkUsername(username)) {
            ServerProtocol error = new ServerProtocol("error", "User with that username already exists");
        } else if (!(isValidLength(username) && isValidLength(password))) {
            ServerProtocol error = new ServerProtocol("error", "Username and password must be between 5" +
                    "and 20 characters long inclusive");
        } else {
            currentUser = new User(username, password);
            ServerProtocol success = new ServerProtocol("success", "Successfully created account with " +
                    "username: " + username);
        }
        writer.write(RegisterUser.checkUser(new User(username, password)));
        writer.flush();
    }

    private void joinLobby() throws IOException {
        int lobbyNumber = reader.read();
        if (lobbyNumber > 4 || 1 > lobbyNumber) {
            System.err.println("lobby does not exist");
            return;
        }
        GameLobby lobby = server.getLobbies().get(lobbyNumber - 1);
        Player player = new Player(this, lobby);
        if(lobby.)
    }

}

