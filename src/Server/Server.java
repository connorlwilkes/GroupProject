/**
 * Server class for the minigame game. Server accepts clients on port 4000 that have been authenticated and assigns
 * them a new thread.
 *
 * @author Connor Wilkes
 * @version 1/3/2018
 */

package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server class for the minigame game. Sets up a server on port 5000 on a host.
 *
 * @author Florence
 * @version 9/3/2018
 */
public class Server {

    private final static Logger serverErrorLogger = Logger.getLogger("ServerErrors");
    private final static Logger serverConnectionLogger = Logger.getLogger("ServerConnections");
    private final int port = 5000;
    private ExecutorService threadPool;
    private List<GameLobby> lobbies;
    private List<User> userDatabase;
    private List<User> activeUsers;

    /**
     * Getter for the lobbies list
     *
     * @return lobbies
     */
    public List<GameLobby> getLobbies() {
        return lobbies;
    }

    /**
     * Adds a lobby to the lobby list
     *
     * @param lobby lobby to add
     */
    public void addLobby(GameLobby lobby) {
        lobbies.add(lobby);
    }

    /**
     * Adds a user to the list of currently active users
     *
     * @param user user to add
     */
    public void addUser(User user) {
        userDatabase.add(user);
    }

    // Testing purposes - will perform same function as database call
    public boolean checkUsername(String toCheck) {
        return userDatabase.stream()
                .anyMatch(user -> (user.getUsername().equals(toCheck)));
    }

    // Testing purposes - will perform same function as database call
    public boolean checkPassword(String username, String password) {
        return userDatabase.stream()
                .anyMatch(user -> (user.getUsername().equals(username) && user.verifyPassword(password)));
    }

    // Testing purposes - will perform same function as database call
    public User findUser(String username) {
        return userDatabase.stream()
                .filter(user -> (user.getUsername().equals(username)))
                .findFirst().orElse(null);
    }

    /**
     * Adds a user to the active user list
     * @param user user to add
     */
    public void addActiveUser(User user) {
        activeUsers.add(user);
    }

    /**
     * Removes a user from the currently active users list
     *
     * @param userToRemove the user to remove
     */
    public void removeUser(User userToRemove) {
        String toRemove = userToRemove.getUsername();
        for (User user : userDatabase) {
            if (user.getUsername().equals(toRemove)) {
                userDatabase.remove(user);
            }
        }
    }

    /**
     * Starts the server
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        lobbies = new ArrayList<>();
        userDatabase = new ArrayList<>();
        activeUsers = new ArrayList<>();
        addUser(new User("connor", "password"));    // for testing, remove!
        threadPool = Executors.newFixedThreadPool(50);
        setUpGameLobbies();
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                    serverConnectionLogger.info("Starting server on port " + port);
                    Socket connection = server.accept();
                    ServerThread task = new ServerThread(connection, this);
                    threadPool.execute(task);
                } catch (IOException ex) {
                    serverErrorLogger.log(Level.SEVERE, "accept error", ex);
                } catch (RuntimeException ex) {
                    serverErrorLogger.log(Level.SEVERE, "unexpected error" + ex.getMessage(), ex);
                }
            }
        } catch (IOException ex) {
            serverErrorLogger.log(Level.SEVERE, "Couldn't start server", ex);
        } catch (RuntimeException ex) {
            serverErrorLogger.log(Level.SEVERE, "Couldn't start server", ex);
        }
    }

    /**
     * Stops the server safely
     */
    public void stop() {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    serverErrorLogger.log(Level.SEVERE, "Server Threads failed to shutdown");
                }
            }
        } catch (InterruptedException ex) {
            serverErrorLogger.log(Level.SEVERE, "Server Thread shutdown interrupted");
        }
    }

    /**
     * Sets up the lobbies for the server. Creating 3 lobbies with ids 1, 2 and 3 respectively.
     */
    private void setUpGameLobbies() {
        for (int i = 1; i <= 3; i++) {
            lobbies.add(new GameLobby(i));
        }
    }

    /**
     * Main method to begin the server
     *
     * @param args
     */
    public static void main(String[] args) {
        Server testServer = new Server();
        testServer.start();
    }

}
