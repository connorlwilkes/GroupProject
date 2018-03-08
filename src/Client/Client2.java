package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

public class Client2 {

    private final static Logger logger = Logger.getLogger("Client Logger");
    final private int port = 5000;
    final private String host = "localhost";
    private Socket connection;
    private DataOutputStream out;
    private BufferedReader in;

    public static void main(String[] args) {
        Client2 test = new Client2();
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

    private void logIn(String username, String password) throws IOException {

        System.out.println("here");
        out.writeBytes("login\r\n");
        out.flush();
        System.out.println("Sent login request");
        out.writeBytes(username + "\r\n");
        out.flush();
        out.writeBytes(password + "\r\n");
        out.flush();
        String line = in.readLine();
        System.out.println(line);
    }
}
