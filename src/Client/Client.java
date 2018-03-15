package Client;
import Server.Message;
import Server.ServerProtocol;
import Server.User;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

    final private int port = 5000;
    final private String host = "localhost";
    private Socket connection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private User user;


    public void connect() {
        try {
            connection = new Socket(host, port);
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            inputStream = new ObjectInputStream(connection.getInputStream());
            ServerProtocol message = (ServerProtocol) inputStream.readObject();
        } catch (ConnectException ex) {
        System.out.println("Connection failure");
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    public ServerProtocol serverRequest(String... args) {
        try {
            ServerProtocol message = new ServerProtocol(args);
            outputStream.writeObject(message);
            outputStream.flush();
            if (args[0].equals("login")){
                user = new User(args[1], args[2]);
            }
            ServerProtocol toReturn = (ServerProtocol) inputStream.readObject();
            System.out.println(toReturn);
            return toReturn;
        } catch (ConnectException ex) {
            System.out.println("Connection failure");
            return new ServerProtocol("false", "Server connection error");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
        return new ServerProtocol("false", "Server error");
    }

    public void sendMessage(String content) {
        try {
            Message message = new Message(content, user);
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void listenForInput() throws IOException, ClassNotFoundException {
        while (true) {
            Object o = inputStream.readObject();
            if (o instanceof Message) {
                //TODO: Get message, add to list and display?
            	Message newMessage = (Message) o;
            	String oldMessage = ChatDisplay.chatBox.getText();
            	ChatDisplay.chatBox.setText(oldMessage + "\n" + newMessage.toString());
            } else {
                //TODO: Game logic here?
            	
            }
        }
    }

    public static void main(String[] args) {
        Client test = new Client();
        test.connect();
    }
}
