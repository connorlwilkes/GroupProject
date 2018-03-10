package Client;

import Server.ServerProtocol;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {

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
            ServerProtocol response = (ServerProtocol) inputStream.readObject();
            return response;
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return new ServerProtocol("false", "Server error");
    }

    public ServerProtocol createAccount(String username, String password) {
        try {
            ServerProtocol message = new ServerProtocol("create-account", username, password);
            outputStream.writeObject(message);
            outputStream.flush();
            connection.close();
            return (ServerProtocol) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        return new ServerProtocol("false", "Server error");
    }

    public ServerProtocol joinLobby(int lobbyNumber) {
        try {
            ServerProtocol message = new ServerProtocol("join-lobby", String.valueOf(lobbyNumber));
            outputStream.writeObject(message);
            outputStream.flush();
            ServerProtocol response = (ServerProtocol) inputStream.readObject();
            return response;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        return new ServerProtocol("false", "Server error");
    }

    public static void main(String[] args) {
        Client test = new Client();
        test.connect();
    }
}
