package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private int roundNumber;
    private List<Player> players;
    private final int maxPlayers = 4;
    private List<Minigame> games;
    private ChatRoom chatRoom;
    private Player questionMaster;
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
        roundNumber = 1;
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
        } else if (players.size() == 1) {
            new Thread(chatRoom).start();
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
     * Chooses the next question master randomly from the list of players in the game. Will ensure that a new player is
     * picked each round until the number of rounds exceed the number of players in which case each player will be
     * eligible to be question master again
     */
    public synchronized void chooseQuestionMaster() {
        questionMaster.setHasBeenQuestionMaster(true);
        if (roundNumber == players.size()) {
            for (Player player : players) {
                player.setHasBeenQuestionMaster(false);
            }
        }
        Random random = new Random();
        int index = random.nextInt(players.size() - 1);
        Player player = players.get(index);
        while (player.isHasBeenQuestionMaster()) {
            index = random.nextInt(players.size() - 1);
            player = players.get(index);
        }
        questionMaster.setQuestionMaster(false);
        player.setQuestionMaster(true);
        questionMaster = player;
    }

    /**
     * Runs a new GameLobby on a new thread
     */
    @Override
    public void run() {
        isRunning = true;

    }

}
