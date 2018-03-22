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

    /**
     * Constructor for the Minigame class which resuses the field variable players as an argument.
     *
     * @param players     the players in the game
     */
    public Minigame(List<Player> players) {
        this.players = players;
        this.roundNumber = 1;
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
     * method to return a list of winners
     *
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
