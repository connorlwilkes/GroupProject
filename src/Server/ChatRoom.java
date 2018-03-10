package Server;

import Client.Client;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * ChatRoom class for the GameLobby of the minigame
 *
 * @author Florence
 * @version 9/3/2018
 */
public class ChatRoom implements Runnable {

    private HashSet<ObjectOutputStream> objectOutputStreams;
    private List<Message> messages;
    private List<Player> players;
    private GameLobby lobby;

    /**
     * Constructor for the ChatRoom class
     *
     * @param lobby lobby associated with the chat
     */
    public ChatRoom(GameLobby lobby) {
        messages = new ArrayList<>();
        players = new ArrayList<>();
        this.lobby = lobby;
    }

    /**
     * Adds a player to the chatroom
     *
     * @param player player to add
     */
    public synchronized void addPlayer(Player player) {
        players.add(player);
        objectOutputStreams.add(player.getClient().getOutputStream());
    }

    /**
     * Removes a player from the chatroom, also removes the player's outputstream
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
     * @param players
     */
    public synchronized void setPlayers(List<Player> players) {
        this.players = players;
        updateStreams();
    }

    /**
     * Updates the active output streams
     */
    public synchronized void updateStreams() {
        objectOutputStreams.clear();
        for (Player player : players) {
            objectOutputStreams.add(player.getClient().getOutputStream());
        }
    }

    private void broadcastMessages() {

    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    private void readFromClients() {
    }

    /**
     * Run method for the thread
     */
    @Override
    public void run() {
        while (true) {
            readFromClients();
            // sendToClients();
        }
    }
}
