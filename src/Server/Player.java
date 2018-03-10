package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Player class for the game. A player object is created when a User class enters a lobby
 *
 * @author Connor Wilkes
 * @version 9/3/2018
 */
public class Player {

    private ServerThread thread;
    private int score;
    private boolean isQuestionMaster;
    private boolean hasBeenQuestionMaster;
    private GameLobby lobby;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * Constructor for the player class
     *
     * @param thread client associated with the player
     * @param lobby  lobby the player is a part of
     */
    public Player(ServerThread thread, GameLobby lobby) {
        this.thread = thread;
        this.lobby = lobby;
        score = 0;
        isQuestionMaster = false;
        hasBeenQuestionMaster = false;
        out = thread.getOutputStream();
        in = thread.getInputStream();
    }

    /**
     * Getter for the client variable
     *
     * @return client
     */
    public ServerThread getClient() {
        return thread;
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

    /**
     * Getter for the lobby
     *
     * @return lobby associated with this user
     */
    public GameLobby getLobby() {
        return lobby;
    }

    public boolean isHasBeenQuestionMaster() {
        return hasBeenQuestionMaster;
    }

    public void setHasBeenQuestionMaster(boolean hasBeenQuestionMaster) {
        this.hasBeenQuestionMaster = hasBeenQuestionMaster;
    }

    /**
     * Processes requests from the client during the game
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void processGameRequests() throws IOException, ClassNotFoundException {
        while (true) {
            Object o = in.readObject();
            if (o instanceof Message) {
                lobby.getChatRoom().addMessage((Message) o);
                //TODO: Chat room logic 
            } else {
                ServerProtocol request = (ServerProtocol) o;
                String type = request.type;
                if (type.startsWith("answer")) {
                    //TODO: Add answer to lobby answer list for current game
                } else if (type.startsWith("score")) {
                    //TODO: Give player their score/score list of all users
                } else if (type.startsWith("qm-vote")) {
                    //TODO: Check if user is question master + if so give chosen answer player a score
                } else if (type.startsWith("question")) {
                    //TODO: Give the user a question - this has to be synced with all other users
                } else if (type.startsWith("leave")) {
                    //TODO: Remove the player from the lobby
                } else {
                    out.writeObject(new ServerProtocol("invalid request"));
                    out.flush();
                }
            }
        }
    }
}
