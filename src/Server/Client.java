/**
 * Client class for the minigame game. Client sends a request to connect to the game server on port 4000. The client can
 * then interact using I/O with the server
 *
 * @author Connor Wilkes
 * @version 1/3/2018s
 */

package Server;

import Login.ServerRequest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    final private int port = 4000;
    private Socket connection;
    private InetAddress destination;
    private InputStream in;
    private OutputStream out;
    private DataInputStream dis;
    private DataOutputStream dos;
    private final static Logger errorLogger = Logger.getLogger("errors");

    /**
     * Constructor for the Client class
     *
     * @param destination destination of the connection (IP address or DSN)
     */
    public Client(InetAddress destination) {
        this.destination = destination;
    }

    public Socket getConnection() {
        return connection;
    }

    /**
     * Connects to a server
     */
    public void connect() {
        try (Socket socket = new Socket(destination, port)) {
            connection = socket;
            in = connection.getInputStream();
            out = connection.getOutputStream();
            dis = new DataInputStream(in);
            dos = new DataOutputStream(out);
            dos.write(ServerRequest.login);
            dos.flush();
            login();
            dos.write(ServerRequest.endConnection);
            dos.flush();
            connection.close();
        } catch (IOException ex) {
            errorLogger.log(Level.SEVERE, "Could not connect", ex);
        }
    }

    /**
     * Helper method to read Strings from a server
     *
     * @return
     * @throws IOException
     */
    private String readFromServer() throws IOException {
        if (in == null) {
            System.err.println("No inputstream");
            return " ";
        } else if (connection == null) {
            System.err.println("No connection");
            return " ";
        } else {
            StringBuilder message = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "ASCII"));
            message.append(reader.readLine());
            return message.toString();
        }
    }

    private void writeOut(String toWrite) throws IOException {
        Writer writer = new OutputStreamWriter(out, "ASCII");
        writer = new BufferedWriter(writer);
        writer.write(toWrite + "\r\n");
        writer.flush();
    }

    private void processRequests() throws IOException {
        if (in.read() == ServerRequest.login) {
            login();
        } else if (in.read() == ServerRequest.welcome) {
            System.out.println("Connected to server");
        }
    }

    private void login() throws IOException {
        Scanner scanner = new Scanner(System.in);

        byte b = dis.readByte();
        while (b != ServerRequest.finish) {
            b = dis.readByte();
            if (b == ServerRequest.requestUsername) {
                System.out.println("Username:");
                dos.writeUTF(scanner.nextLine());
                dos.flush();
            } else if (b == ServerRequest.requestPassword) {
                System.out.println("Password");
                dos.writeUTF(scanner.nextLine());
                dos.flush();
            }
        }
    }

    /**
     * Main method to start the client connection
     *
     * @param args command line arguments z
     */
    public static void main(String[] args) {
        try {
            Client testClient = new Client(InetAddress.getLocalHost());
            testClient.connect();
        } catch (IOException ex) {

        }

    }


}
