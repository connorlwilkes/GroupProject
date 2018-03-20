package Client;

import Server.Message;
import Server.ServerProtocol;
import Server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

    final private int port = 5000;
    final private String host = "localhost";
    public ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    private Socket connection;
    private User user;
    private ClientGui gui;

    public Client(ClientGui gui) {
        this.gui = gui;
    }

    public void connect() {
        try {
            connection = new Socket(host, port);
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            inputStream = new ObjectInputStream(connection.getInputStream());
        } catch (ConnectException ex) {
            System.out.println("Connection failure");
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

    public ServerProtocol serverRequest(String... args) {
        try {
            ServerProtocol message = new ServerProtocol(args);
            outputStream.writeObject(message);
            outputStream.flush();
            if (args[0].equals("login")) {
                user = new User(args[1], args[2]);
            }
            return (ServerProtocol) inputStream.readObject();
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
                String oldMessage = gui.chat.chatBox.getText();
                gui.chat.chatBox.setText(oldMessage + "\n" + newMessage.toString());
            } else {
                //TODO: Game logic here?

            }
        }
    }
}
