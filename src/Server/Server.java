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

public class Server {

    private final static Logger serverErrorLogger = Logger.getLogger("ServerErrors");
    private final static Logger serverConnectionLogger = Logger.getLogger("ServerConnections");
    private final int port = 5000;
    private ExecutorService threadPool;
    private List<GameLobby> lobbies;
    private List<User> activeUsers;

    /**
     * Main method to begin the server
     *
     * @param args
     */
    public static void main(String[] args) {
        Server testServer = new Server();
        testServer.start();
    }

    public List<GameLobby> getLobbies() {
        return lobbies;
    }

    public void addLobby(GameLobby lobby) {
        lobbies.add(lobby);
    }

    public void addUser(User user) {
        activeUsers.add(user);
    }

    /**
     * Starts the server
     */
    public void start() {
        lobbies = new ArrayList<>();
        activeUsers = new ArrayList<>();
        addUser(new User("connor", "password"));    // for testing, remove!
        threadPool = Executors.newFixedThreadPool(50);
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

    public boolean checkUsername(String toCheck) {
        for (User user : activeUsers) {
            if (user.getUsername().equals(toCheck)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String username, String password) {
        for (User user : activeUsers) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                return true;
            }
        }
        return false;
    }

    public User findUser(String username) {
        for (User user : activeUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
