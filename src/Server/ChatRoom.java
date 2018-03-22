package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ChatRoom class for the GameLobby of the minigame
 *
 * @author Florence
 * @version 9/3/2018
 */
public class ChatRoom {

    private List<ObjectOutputStream> objectOutputStreams;
    private List<Message> messages;
    private List<Player> players;
    private GameLobby lobby;

    /**
     * Constructor for the ChatRoom class, reuses the field lobby as an argument.
     *
     * @param lobby The lobby associated with the chat
     */
    public ChatRoom(GameLobby lobby) {
        messages = new ArrayList<>();
        objectOutputStreams = new ArrayList<>();
        players = new ArrayList<>();
        this.lobby = lobby;
    }

    /**
     * Method to add a player to the chatroom
     *
     * @param player The player to add
     */
    public synchronized void addPlayer(Player player) {
        players.add(player);
        objectOutputStreams.add(player.getClient().getOutputStream());
    }

    /**
     * Method to remove a player from the chatroom, also removes the player's outputstream
     *
     * @param playerToRemove player to remove
     */
    public synchronized void removePlayer(Player playerToRemove) {
        players.remove(playerToRemove);
        objectOutputStreams.remove(playerToRemove.getClient().getOutputStream());
    }

    /**
     * Setter for the list of players
     *
     * @param players The players in the chatRoom
     */
    public synchronized void setPlayers(List<Player> players) {
        this.players = players;
        updateStreams();
    }

    /**
     * Method to update the active output streams
     */
    public synchronized void updateStreams() {
        objectOutputStreams.clear();
        for (Player player : players) {
            objectOutputStreams.add(player.getClient().getOutputStream());
        }
    }

    /**
     * Method to add a message to the chatRoom
     * @param message The message to be added
     */
    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Method to broadcast the message to make it viewable
     * @param message The message to be broadcast
     */
    public synchronized void broadcastMessage(Message message) {
        for (ObjectOutputStream out : objectOutputStreams) {
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
