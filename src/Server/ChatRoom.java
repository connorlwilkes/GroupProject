package Server;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChatRoom {

    private HashSet<BufferedWriter> writers;
    private List<Message> messages;
    private HashSet<User> users;

    public ChatRoom() {
        messages = new ArrayList<>();
        users = new HashSet<>();
    }
}
