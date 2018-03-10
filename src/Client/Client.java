package Client;

import Server.GameLobby;
import Server.ServerProtocol;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {

    private final static Logger logger = Logger.getLogger("Client Logger");
    final private int port = 5000;
    final private String host = "localhost";
    private Socket connection;
    private DataOutputStream out;
    private BufferedReader in;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void connect() {
        try {
            connection = new Socket(host, port);
            out = new DataOutputStream(connection.getOutputStream());
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            inputStream = new ObjectInputStream(connection.getInputStream());
            ServerProtocol in = (ServerProtocol) inputStream.readObject();
            if (in.type.startsWith("welcome")) {
                System.out.println(in.message[0]);
                ServerProtocol hello = new ServerProtocol("hello", "oh hi there!");
                outputStream.writeObject(hello);
                outputStream.flush();
                System.out.println("it worked!");
            } else {
                System.out.println("Something has gone wrong here");
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    /**
     * Stops the connection to the server
     */
    public void stop() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerProtocol logIn(String username, String password) {
        try {
            ServerProtocol message = new ServerProtocol("login", username, password);
            outputStream.writeObject(message);
            outputStream.flush();
            ServerProtocol response = null;
            response = (ServerProtocol) inputStream.readObject();
            return response;
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return null;
    }

    public void createAccount(String username, String password) throws IOException {
        out.writeBytes("createAccount\r\n");
        out.flush();
        out.writeBytes(username + "\r\n");
        out.flush();
        out.writeBytes(password + "\r\n");
        out.flush();
    }

    private void joinLobby(GameLobby lobby) {

    }

    public static void main(String[] args) {
        Client test = new Client();
        test.connect();
    }
}
