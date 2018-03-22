package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GameLobby class for the game. Players enter the lobby and play rounds of minigames. The GameLobby class keeps track
 * of the scores and moderates user input.
 * Has the fields: maxPlayers, id, totalScore, gameNumber of type int; lobbyName of type String; players and games of type List;
 * chatRoom of type ChatRoom; isFull and isRunning of type boolean; answers of type HashMap.
 *
 * @author Florence
 * @version 21/3/2018
 */
public class GameLobby implements Runnable {

    private final int maxPlayers = 3;
    private String lobbyName;
    private int id;
    private int totalScore;
    private int gameNumber;
    private List<Player> players;
    private List<Minigame> games;
    private ChatRoom chatRoom;
    private boolean isFull;
    private boolean isRunning;
    private HashMap<Player, String> answers;
    public boolean gameIsOver = false;

    /**
     * Constructor for the GameLobby class, reuses the field id as an argument.
     *
     * @param id The id of the GameLobby
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
        answers = new HashMap<>();
    }

    /**
     * Getter for the isFull boolean
     *
     * @return true if lobby is full and false if not
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     * Getter for the isRunning boolean
     *
     * @return true if the game is in progress and false if not
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Getter for the players
     *
     * @return the players in the game lobby
     */
    public List<Player> getPlayers() {
        return players;
    }

    
     /**
     * Getter for the chatRoom
     *
     * @return the chatroom in use
     */
    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    /**
     * Getter for the Id
     *
     * @return the id of the chatRoom
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the Id
     *
     * @return the new id of the chatRoom
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * toString method
     *
     * @return String interpretation of the class
     */
    public String toString() {
        return lobbyName;
    }

    /**
     * Method to remove a player from the game lobby
     *
     * @param playerToRemove the player to be removed
     */
    public synchronized void removePlayer(Player playerToRemove) {
        players.remove(playerToRemove);
        chatRoom.removePlayer(playerToRemove);
        if (isFull) {
            isFull = false;
        }
    }

    /**
     * Method to add a player to the players list
     *
     * @param playerToAdd the player to be added
     */
    public synchronized void addPlayer(Player playerToAdd) {
        players.add(playerToAdd);
        chatRoom.addPlayer(playerToAdd);
        if (players.size() == maxPlayers) {
            isFull = true;
            new Thread(this).start();
        }
        answers.put(playerToAdd, null);
    }


    /**
     * Method to get the current game being played in the lobby
     *
     * @return current game being played
     */
    public synchronized Minigame getCurrentGame() {
        return games.get(gameNumber - 1);
    }

    /**
     * Method to get the scores of the players in the game
     *
     * @return the players' scores as an array of Strings
     */
    public synchronized String[] getScores(String roundWinner) {
        List<String> toReturn = new ArrayList<>();
        toReturn.add("get-scores");
        toReturn.add(roundWinner);
        for (Player player: players) {
            toReturn.add(player.getUser().getUsername() + ": " + player.getScore());
        }
        return toReturn.toArray(new String[toReturn.size()]);
    }

    /**
     * method to get the player/players with the highest score
     *
     * @return a list of the players with the highest scores
     */
    public synchronized List<Player> getWinningPlayers() {
        int maxScore = players.stream().mapToInt(player -> (player.getScore())).sum();
        return players.stream().filter(player -> (player.getScore() == maxScore)).collect(Collectors.toList());
    }

    /**
     * Method to run a new GameLobby on a new thread
     */
    @Override
    public void run() {
        isRunning = true;
        HeadlineGame game = new HeadlineGame(this, players);
        games.add(game);
        while (game.running) {}
        gameIsOver = true;
    }

}
