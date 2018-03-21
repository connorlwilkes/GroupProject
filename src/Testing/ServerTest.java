package Testing;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("Duplicates")
public class ServerTest {

    public Set<ObjectOutputStream> outputStreams = new HashSet<>();

    public static void main(String[] args) {
        ServerTest test = new ServerTest();
        test.start();
    }

    public void start() {
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        System.out.println("Started server");
        try (ServerSocket server = new ServerSocket(6000)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    TestThread task = new TestThread(connection, this);
                    threadPool.execute(task);
                } catch (IOException | RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }

    }
}
