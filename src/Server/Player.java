package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Player class for the game. A player object is created when a User class enters a lobby
 *
 * @author Connor Wilkes
 * @version 9/3/2018
 */
public class Player {

    private ServerThread thread;
    private User user;
    private int score;
    private boolean isQuestionMaster;
    private boolean hasBeenQuestionMaster;
    private GameLobby lobby;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String currentAnswer;

    /**
     * Constructor for the player class
     *
     * @param thread client associated with the player
     * @param lobby  lobby the player is a part of
     */
    public Player(ServerThread thread, GameLobby lobby, User user) {
        this.thread = thread;
        this.lobby = lobby;
        this.user = user;
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
     * Setter for the score variable
     *
     * @param score score to set
     */
    public void setScore(int score) {
        this.score = score;
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

    public User getUser() {
        return user;
    }

    public String getCurrentAnswer() {
        return currentAnswer;
    }

    public ObjectOutputStream getOut() {
        return out;
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
            } else if (o instanceof ServerProtocol && lobby.getCurrentGame() instanceof HeadlineGame) {
                ServerProtocol request = (ServerProtocol) o;
                String type = request.type;
                processHeadlineGameRequests(request, type);
            } else {
                out.writeObject(new ServerProtocol("invalid request"));
                out.flush();
            }
        }
    }

    public void processHeadlineGameRequests(ServerProtocol request, String type) throws IOException {
        HeadlineGame game = (HeadlineGame) lobby.getCurrentGame();
        if (type.startsWith("answer")) {
            game.addAnswer(request.message[0]);
            currentAnswer = request.message[0];
            ServerProtocol message = new ServerProtocol("true", "Answer added");
            out.writeObject(message);
            out.flush();
        } else if (type.startsWith("qm-vote")) {
            if (isQuestionMaster) {
                String username = null;
                for (Player player : game.getPlayers()) {
                    if (player.getCurrentAnswer().startsWith(request.message[1])) {
                        username = player.getUser().getUsername();
                    }
                }
                if (username == null) {
                    ServerProtocol message = new ServerProtocol("false", "process error - pick again");
                    out.writeObject(message);
                    out.flush();
                } else {
                    game.addScore(Integer.valueOf(request.message[0]), username);
                    ServerProtocol message = new ServerProtocol("true", "Vote accepted");
                    out.writeObject(message);
                    out.flush();
                }
            } else {
                ServerProtocol message = new ServerProtocol("false", "invalid request");
                out.writeObject(message);
                out.flush();
            }
        } else if (type.startsWith("get-answers")) {
            if (isQuestionMaster) {
                List<String> list = game.getAnswers();
                list.add("true");
                list.add("qm");
                String[] toReturn = list.toArray(new String[list.size()]);
                ServerProtocol message = new ServerProtocol(toReturn);
            } else {
                List<String> list = game.getAnswers();
                list.add("true");
                list.add("notqm");
                String[] toReturn = list.toArray(new String[list.size()]);
                ServerProtocol message = new ServerProtocol(toReturn);
            }
        } else if (type.startsWith("leave")) {
            lobby.removePlayer(this);
            ServerProtocol message = new ServerProtocol("true", "Left " + lobby.toString() + " successfully");
            out.writeObject(message);
            out.flush();
        } else if (type.startsWith("question")) {
            if (isQuestionMaster) {
                ServerProtocol message = new ServerProtocol("true", "qm", game.getCurrentQuestion());
                out.writeObject(message);
                out.flush();
            } else {
                ServerProtocol message = new ServerProtocol("true", "notqm", game.getCurrentQuestion());
                out.writeObject(message);
                out.flush();
            }
        } else if (type.startsWith("get-qm")) {
            ServerProtocol message = new ServerProtocol("true", game.getQuestionMaster().getUser().getUsername());
            out.writeObject(message);
            out.flush();
        } else if (type.startsWith("getscores")) {
            ServerProtocol message = new ServerProtocol(lobby.getScores());
            out.writeObject(message);
            out.flush();
        } else {
            out.writeObject(new ServerProtocol("false", "invalid request"));
            out.flush();
        }
    }
}
