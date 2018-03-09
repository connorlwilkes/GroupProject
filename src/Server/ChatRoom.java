package Server;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * ChatRoom class for the GameLobby of the minigame
 * @author Florence
 * @version 9/3/2018
 */
public class ChatRoom {

    private HashSet<BufferedWriter> writers;
    private List<Message> messages;
    private HashSet<User> users;

    /**
     * Constructor for the ChatRoom class
     */
    public ChatRoom() {
        messages = new ArrayList<>();
        users = new HashSet<>();
    }
}
