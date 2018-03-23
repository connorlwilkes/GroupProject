package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Minigame class for the minigame game with the fields players, scores and questionMaster of type List<Player>, List<Integer>
 * and int respectively.
 *
 * @author Florence
 * @version 21/3/2018
 */
public class Minigame {

    private List<Player> players;
    private List<Integer> scores;
    private int roundNumber;
    private GameLobby lobby;
    public Boolean running;

    /**
     * Constructor for the Minigame class which resuses the field variable players as an argument.
     *
     * @param players     the players in the game
     */
    public Minigame(GameLobby lobby, List<Player> players) {
        this.lobby = lobby;
        this.players = players;
        this.roundNumber = 1;
        running = true;
    }

    /**
     * Getter for the players
     *
     * @return list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Getter for the round number
     *
     * @return the round number
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Setter for the round number
     *
     * @param roundNumber number to set the round too
     */
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * method to add a score to a player in the game
     *
     * @param scoreToAdd the score to add
     * @param username   the username of the player to whom the score will be added
     */
    public void addScore(int scoreToAdd, String username) {
        for (Player gamePlayer : players) {
            if (gamePlayer.getUser().getUsername().equals(username)) {
                gamePlayer.setScore(gamePlayer.getScore() + scoreToAdd);
            }
        }
    }

    /**
     * Getter for the lobby
     *
     * @return the gameLobby
     */
    public GameLobby getLobby() {
        return lobby;
    }

    /**
     * Setter for the lobby
     *
     * @param the new gameLobby to set the lobby to
     */
    public void setLobby(GameLobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Method to return the winners of a game
     * @return winners The player(s) who won the game
     */
    public List<String> winner() {
        List<String> winners = new ArrayList<>();
        int highScore = 0;
        for (Player player : players) {
            if (highScore == 0) {
                highScore = player.getScore();
                winners.add(player.getUser().getUsername());
            } else if (player.getScore() > highScore) {
                winners = new ArrayList<>();
                winners.add(player.getUser().getUsername());
                highScore = player.getScore();
            } else if (player.getScore() ==  highScore) {
                winners.add(player.getUser().getUsername());
            }
        }
        return winners;
    }


}
