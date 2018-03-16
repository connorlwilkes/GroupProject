package Testing;

import Server.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestThread implements Runnable {

    private Socket connection;
    private ServerTest server;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public TestThread(Socket connection, ServerTest server) {
        this.connection = connection;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            server.outputStreams.add(out);
            while (true) {
                Message toAdd = (Message) in.readObject();
                for (ObjectOutputStream out : server.outputStreams) {
                    out.writeObject(toAdd);
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
