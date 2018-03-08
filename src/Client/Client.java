package Client;

import Server.GameLobby;
import Server.Login.RegisterUser;
import Server.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {

    private final static Logger logger = Logger.getLogger("Client Logger");
    final private int port = 5000;
    final private String host = "localhost";
    private Socket connection;
    private DataOutputStream out;
    private BufferedReader in;

    public static void main(String[] args) {
        Client.Client test = new Client.Client();
        test.connect();
    }

    public void connect() {
        try {
            connection = new Socket(host, port);
            out = new DataOutputStream(connection.getOutputStream());
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String serverSent = in.readLine();
            if (serverSent.startsWith("hello")) {
                System.out.println("hi there server");
                out.writeBytes("hello\r\n");
                out.flush();
            }
            logIn("connor", "password");
        } catch (IOException e) {
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

    private void logIn(String username, String password) throws IOException {
        out.writeBytes("login\r\n");
        out.flush();
        out.writeBytes(username + "\r\n");
        out.flush();
        out.writeBytes(password + "\r\n");
        out.flush();
    }

    private void createAccount(String username, String password) throws IOException {
        out.writeBytes("createAccount\r\n");
        out.flush();
        out.writeBytes(username + "\r\n");
        out.flush();
        out.writeBytes(password + "\r\n");
        out.flush();
    }

    private void joinLobby(GameLobby lobby) {

    }

}
