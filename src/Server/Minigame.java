package Server;

import java.util.List;

/**
 * Minigame class for the minigame game
 *
 * @author Florence
 * @version 9/3/2018
 */
public class Minigame {

    private List<Player> players;
    private List<Integer> scores;
    private int roundNumber;

    /**
     * Constructor for the minigame class
     *
     * @param players the players in the game
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
    public void setRoundNumber (int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * Adds score to a player in the game
     *
     * @param scoreToAdd the score to add
     * @param username   the username of the player to add the score to
     */
    public void addScore(int scoreToAdd, String username) {
        for (Player gamePlayer : players) {
            if (gamePlayer.getUser().getUsername().equals(username)) {
                gamePlayer.setScore(gamePlayer.getScore() + scoreToAdd);
            }
        }
    }


}
