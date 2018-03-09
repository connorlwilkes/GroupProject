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
    private List<Player> players;
    private GameLobby lobby;

    /**
     * Constructor for the ChatRoom class
     * @param lobby lobby associated with the chat
     */
    public ChatRoom(GameLobby lobby) {
        messages = new ArrayList<>();
        players = new ArrayList<>();
        this.lobby = lobby;
    }

    public void addPlayer(Player player) {
        players.add(player);
        writers.add(player.getClient().getWriter());
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        updateWriters();
    }

    public void updateWriters() {
        writers.clear();
        for (Player player : players) {
            writers.add(player.getClient().getWriter());
        }
    }

}
