/**
 * ServerThread class for the Server of the minigame game. Takes an incoming connection and creates a new thread.
 *
 * @author Connor Wilkes
 * @version 1/3/2018
 */

package Server;

import Login.ServerRequest;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread implements Callable<Void> {

    private Socket connection;
    private Server server;
    private InputStream in;
    private OutputStream out;
    private DataOutputStream dos;
    private DataInputStream dis;
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
    public Void call() {
        try {
            auditLogger.info("Connected to: " + connection.getRemoteSocketAddress() + " on " + new Date());
            in = connection.getInputStream();
            out = connection.getOutputStream();
            dos = new DataOutputStream(out);
            dis = new DataInputStream(in);
            dos.writeByte(ServerRequest.welcome);
            while (true) {
                byte b = dis.readByte();
                if (b == ServerRequest.login) {
                    loginUser();
                } if (b == ServerRequest.endConnection) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                connection.close();
            } catch (IOException e) {

            }
        }
        return null;
    }


    private void loginUser() throws IOException {
        out.write(ServerRequest.requestUsername);
        out.flush();
        String username = dis.readUTF();
        out.write(ServerRequest.requestPassword);
        out.flush();
        String password = dis.readUTF();
        User currentUser = new User(username, password);
        Session session = new Session(currentUser, this);
        out.write(ServerRequest.finish);
        out.flush();
    }

    /**
     * Sends a string to the client with confirmation of connection to the server
     *
     * @throws IOException if connection fails
     */
    public void sendConfirmationOfConnection() throws IOException {
        out.write(ServerRequest.welcome);
        out.flush();
    }

    /**
     * Processes network requests
     */
    private void processRequests() {
        try {
            if (in.read() == ServerRequest.createLobby) {
                GameOwner owner = new GameOwner(this);
                GameLobby lobby = new GameLobby("testLobby", "password", owner);
                owner.setGameLobby(lobby);
                server.addLobby(lobby);
                new Thread(lobby).start();
            } else if (in.read() == ServerRequest.joinLobby) {
                joinLobby("testLobby", "password");
            } else if (in.read() == ServerRequest.quit) {
                connection.close();
            } else {
                System.err.println("Not a valid request");
            }
        } catch (IOException ex) {
            errorLogger.log(Level.SEVERE, "could not process request", ex);
        }
    }

    private void joinLobby(String lobbyName, String lobbyPassword) {
        List<GameLobby> allLobbies = server.getLobbies();
        for (GameLobby lobby : allLobbies) {
            if (lobby.getLobbyName().equals(lobbyName) && lobby.getPassword().equals(lobbyPassword)) {
                lobby.addPlayer(new Player(this));
                return;
            }
        }
        System.err.println("No server with that name exists and/or the password is incorrect");
    }

    private void writeOut(String toWrite) throws IOException {
        Writer writer = new OutputStreamWriter(out, "ASCII");
        writer = new BufferedWriter(writer);
        writer.write(toWrite + "\r\n");
        writer.flush();
    }

    private String readFromInput() throws IOException {
        StringBuilder message = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "ASCII"));
        return reader.readLine();
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

