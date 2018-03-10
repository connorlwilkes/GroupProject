package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GameLobby class for the game. Players enter the lobby and play rounds of minigames. The GameLobby class keeps track
 * of the scores and moderates user input.
 *
 * @author Florence
 * @version 9/3/2018
 */
public class GameLobby implements Runnable {

    private String lobbyName;
    private int id;
    private int totalScore;
    private List<Player> players;
    private final int maxPlayers = 4;
    private List<Minigame> games;
    private ChatRoom room;
    private boolean isFull;
    private boolean isRunning;

    /**
     * Constructor for the GameLobby class
     *
     * @param id id of the GameLobby
     */
    public GameLobby(int id) {
        this.lobbyName = "Lobby " + id;
        this.id = id;
        totalScore = 0;
        players = new ArrayList<>();
        games = new ArrayList<>();
        this.isFull = false;
        this.isRunning = false;
        room = new ChatRoom(this);
    }

    /**
     * Getter for the isFull boolean
     *
     * @return true if lobby is full or false if not
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     * Getter for the isRunning boolean
     *
     * @return isRunning if the game is in progress
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Getter for the players
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Setters for players
     *
     * @param players list of players to set players to
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Adds a player to the players list
     *
     * @param playerToAdd the player to add
     */
    public synchronized void addPlayer(Player playerToAdd) {
        players.add(playerToAdd);
        if (players.size() == maxPlayers) {
            isFull = true;
            run();
        }
    }

    /**
     * Removes a player from the game
     *
     * @param playerToRemove the player to remove
     */
    public synchronized void removePlayer(Player playerToRemove) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(playerToRemove)) {
                players.remove(i);
            }
        }
    }

    /**
     * Gets the player/players with the highest score
     *
     * @return a list of the players with the highest scores
     */
    public synchronized List<Player> getWinningPlayers() {
        int maxScore = players.stream().mapToInt(player -> (player.getScore())).sum();
        return players.stream().filter(player -> (player.getScore() == maxScore)).collect(Collectors.toList());
    }

    /**
     * Runs a new GameLobby on a new thread
     */
    @Override
    public void run() {

    }

}
