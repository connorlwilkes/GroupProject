package Server;

/**
 * Player class for the game. A player object is created when a User class enters a lobby
 *
 * @author Connor Wilkes
 * @version 9/3/2018
 */
public class Player {

    private ServerThread client;
    private int score;
    private boolean isQuestionMaster;
    private GameLobby lobby;

    /**
     * Constructor for the player class
     *
     * @param client client associated with the player
     * @param lobby  lobby the player is a part of
     */
    public Player(ServerThread client, GameLobby lobby) {
        this.client = client;
        this.score = 0;
        this.isQuestionMaster = false;
        this.lobby = lobby;
    }

    /**
     * Getter for the client variable
     *
     * @return client
     */
    public ServerThread getClient() {
        return client;
    }

    /**
     * Getter for the score variable
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for the questionMaster boolean
     *
     * @return the questionMaster boolean
     */
    public boolean getQuestionMaster() {
        return isQuestionMaster;
    }

    /**
     * Setter for the questionMaster boolean
     *
     * @param questionMaster the state to set the questionMaster boolean to
     */
    public void setQuestionMaster(boolean questionMaster) {
        isQuestionMaster = questionMaster;
    }
}
