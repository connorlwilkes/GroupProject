package Client;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client2 {

    final private int port = 5000;
    final private String host = "localhost";
    private Socket connection;
    private DataOutputStream out;
    private BufferedReader in;
    private final static Logger logger = Logger.getLogger("Client Logger");

    public void connect() {
        try {
            connection = new Socket(host, port);
            out = new DataOutputStream(connection.getOutputStream());
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String serverSent = in.readLine();
            System.out.println(serverSent);
            if (serverSent.startsWith("hello")) {
                out.writeBytes("");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Client2 test = new Client2();
        test.connect();
    }

}
