/**
 * Client class for the minigame game. Client sends a request to connect to the game server on port 4000. The client can
 * then interact using I/O with the server
 *
 * @author Connor Wilkes
 * @version 1/3/2018s
 */

package Client;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private final static Logger errorLogger = Logger.getLogger("errors");
    final private int port = 5000;
    private Socket connection;
    private String host;
    private InputStream in;
    private OutputStream out;
    private BufferedWriter writer;
    private BufferedReader reader;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    /**
     * Constructor for the Client class
     *
     * @param host destination of the connection (IP address or DSN)
     */
    public Client(String host) {
        this.host = host;
    }

    public static void main(String[] args) {
        Client test = new Client("localhost");
        test.connect();
        System.out.println("connected");
        try {
            test.login("connor", "password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Socket getConnection() {
        return connection;
    }

//    private void setUp() throws IOException, ClassNotFoundException {
//        in = connection.getInputStream();
//        out = connection.getOutputStream();
//        objectInputStream = new ObjectInputStream(in);
//        objectOutputStream = new ObjectOutputStream(out);
//        writer = new BufferedWriter(new OutputStreamWriter(out));
//        reader = new BufferedReader(new InputStreamReader(in));
//    }

    /**
     * Connects to a server
     */
    public void connect() {
        try (Socket socket = new Socket(host, port)) {
            connection = socket;
            dos = new DataOutputStream(connection.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            dis = new DataInputStream(connection.getInputStream());
            dos.writeBytes("hello\r\n");
            dos.flush();
        } catch (IOException ex) {
            errorLogger.log(Level.SEVERE, "Could not connect", ex);
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

    /**
     * Method allows the server to log in
     *
     * @param username username of the user
     * @param password password of the user
     * @throws IOException
     */
    public void login(String username, String password) throws IOException {
        dos = new DataOutputStream(connection.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        dos.writeBytes("login\r\n");
        dos.flush();
        while (true) {
            String line = reader.readLine();
            if (line.equals("username")) {
                dos.writeBytes(username + "\r\n");
                dos.flush();
            } else if (line.equals("password")) {
                dos.writeBytes(password + "\r\n");
                dos.flush();
            } else if (line.equals("error") || line.equals("success")) {
                break;
            }
        }
//        ServerProtocol login = new ServerProtocol("login", username, password);
//        objectOutputStream.writeObject(login);
//        objectOutputStream.flush();
    }
}
