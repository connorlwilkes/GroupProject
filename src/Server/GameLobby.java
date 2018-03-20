package Server;

import java.io.IOException;
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

    private final int maxPlayers = 4;
    private String lobbyName;
    private int id;
    private int totalScore;
    private int gameNumber;
    private List<Player> players;
    private List<Minigame> games;
    private ChatRoom chatRoom;
    private boolean isFull;
    private boolean isRunning;

    /**
     * Constructor for the GameLobby class
     *
     * @param id id of the GameLobby
     */
    public GameLobby(int id) {
        this.id = id;
        lobbyName = "Lobby " + id;
        totalScore = 0;
        players = new ArrayList<>();
        games = new ArrayList<>();
        isFull = false;
        isRunning = false;
        chatRoom = new ChatRoom(this);
        gameNumber = 1;
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

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public String toString() {
        return lobbyName;
    }

    /**
     * Removes a player from the game
     *
     * @param playerToRemove the player to remove
     */
    public synchronized void removePlayer(Player playerToRemove) {
        players.remove(playerToRemove);
        chatRoom.removePlayer(playerToRemove);
    }

    /**
     * Adds a player to the players list
     *
     * @param playerToAdd the player to add
     */
    public synchronized void addPlayer(Player playerToAdd) {
        players.add(playerToAdd);
        chatRoom.addPlayer(playerToAdd);
        if (players.size() == maxPlayers) {
            isFull = true;
            new Thread(this).start();
        }
    }

    /**
     * Gets the current game being played in the lobby
     *
     * @return current game being played
     */
    public synchronized Minigame getCurrentGame() {
        return games.get(gameNumber - 1);
    }

    public synchronized String[] getScores() {
        String[] toReturn = new String[players.size()];
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            string.append(players.get(i).getUser().getUsername() + " " + players.get(i).getScore());
            toReturn[i] = string.toString();
            string.setLength(0);
        }
        return toReturn;
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
        isRunning = true;
        HeadlineGame game = new HeadlineGame(players);
        for (Player player : players) {
            ServerProtocol start = new ServerProtocol("start", "game is starting");
            try {
                player.getOut().writeObject(start);
                player.getOut().flush();
                player.processGameRequests();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
