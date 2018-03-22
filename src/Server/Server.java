/**
 * Server class for the minigame game. Server accepts clients on port 4000 that have been authenticated and assigns
 * them a new thread.
 *
 * @author Connor Wilkes
 * @version 1/3/2018
 */

package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
    private int port;
    private InetAddress host;
    private ExecutorService threadPool;
    private List<GameLobby> lobbies;
    private List<ServerThread> activeUsers;

    /**
     * Primary constructor for the Server class taking just portNumber and automatically binds to localhost
     *
     * @param portNumber port number
     */
    public Server(String portNumber) {
        port = Integer.valueOf(portNumber);
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * Secondary constructor for the Server class taking portNumber and address
     *
     * @param portNumber port number
     * @param address    address of the server
     */
    public Server(String portNumber, String address) {
        try {
            port = Integer.valueOf(portNumber);
            host = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to begin the server
     *
     * @param args
     */
    public static void main(String[] args) {
        Server testServer = new Server(args[0]);
        testServer.start();
    }

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
     * Adds a client to the active user list
     *
     * @param client client to add
     */
    public synchronized void addActiveUser(ServerThread client) {
        activeUsers.add(client);
    }

    /**
     * Removes a user from the currently active users list
     *
     * @param userToRemove the user to remove
     */
    public synchronized void removeUser(User userToRemove) {
        String toRemove = userToRemove.getUsername();
        List<ServerThread> list = activeUsers;
            for (ServerThread connection : list) {
                if (connection.currentUser.getUsername().equals(toRemove)) {
                    list.remove(connection);
                }
            }
        activeUsers = list;
    }

    /**
     * Getter for the activeusers list
     *
     * @return list of active users in the server
     */
    public List<ServerThread> getActiveUsers() {
        return activeUsers;
    }

    /**
     * Starts the server
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        lobbies = new ArrayList<>();
        activeUsers = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(50);
        setUpGameLobbies();
        monitorLobby();
        try (ServerSocket server = new ServerSocket(port, 500, host)) {
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
     * Keeps up to date all active lobbies
     */
    private void monitorLobby() {
        Runnable monitor = () -> {
            while (true) {
                for (GameLobby lobby : lobbies) {
                    if (lobby.gameIsOver) {
                         int lobbyNumber = lobby.getId();
                         lobbies.remove(lobby);
                         lobbies.add(new GameLobby(lobbyNumber));
                    }
                }
            }
        };
        new Thread(monitor).start();
    }
}
