/**
 * ServerThread class for the Server of the minigame game. Takes an incoming connection and creates a new thread.
 *
 * @author Connor Wilkes
 * @version 1/3/2018
 */

package Server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread implements Runnable {

    private Socket connection;
    private Server server;
    private InputStream in;
    private OutputStream out;
    private BufferedReader reader;
    private BufferedWriter writer;
    private User currentUser;
    private final static Logger auditLogger = Logger.getLogger("requests");
    private final static Logger errorLogger = Logger.getLogger("errors");

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
            while (true) {
                System.out.println(connection.isConnected());
                String line = reader.readLine();
                while (line == null) {
                    line = reader.readLine();
                }
                String password = null;
                String username = null;
                switch (line) {
                    case "hello":
                        System.out.println("hello");
                    case "login":
                        writer.write("username");
                        writer.flush();
                        username = reader.readLine();
                        writer.write("password");
                        writer.flush();
                        password = reader.readLine();
                        loginUser(username, password);
                        processRequests();
                        break;
                    case "createAccount":
                        username = reader.readLine();
                        password = reader.readLine();
                        setUpAccount(username, password);
                        auditLogger.info("Created account: " + currentUser.getUsername() + "\n Closing connection");
                        connection.close();
                        break;
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        in = connection.getInputStream();
        out = connection.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new BufferedWriter(new OutputStreamWriter(out));
    }

    /**
     * Processes network requests
     */
    private void processRequests() throws IOException, ClassNotFoundException {
    }

    private void loginUser(String username, String password) throws IOException {
        if (!(server.checkPassword(username, password))) {
            writer.write("Error: Username or password incorrect");
            writer.flush();
        } else {
            currentUser = new User(username, password);
            writer.write("success");
            writer.flush();
            writer.write("Success: User " + username + " logged in");
            writer.flush();
        }
    }

    private void setUpAccount(String username, String password) throws IOException {
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
    }

    private void joinLobby(String lobbyName, String lobbyPassword) {
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

}

