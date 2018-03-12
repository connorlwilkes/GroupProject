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

    public Minigame(List<Player> players) {
        this.players = players;
        this.roundNumber = 1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Adds score to a player in the game
     *
     * @param scoreToAdd the score to add
     * @param username     the username of the player to add the score to
     */
    public void addScore(int scoreToAdd, String username) {
        for (Player gamePlayer : players) {
            if (gamePlayer.getUser().getUsername().equals(username)) {
                gamePlayer.setScore(gamePlayer.getScore() + scoreToAdd);
            }
        }
    }


}
