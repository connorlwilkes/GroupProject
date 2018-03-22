package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Player class for the game. A player object is created when an instance of the User class enters a lobby
 * Has the fields thread of type ServerThread; user of type User; score of type int; isQuestionMaster and 
 * hasBeenQuestionMaster of type boolean; out of type ObjectOutputStream; and in of typeObjectInputStream.
 * 
 * @author Florence
 * @version 22/3/2018
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

    /**
     * Constructor for the player class which reuses the fields thread, lobby and user as arguments.
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
     * @return client thread
     */
    public ServerThread getClient() {
        return thread;
    }

    /**
     * Getter for the score variable
     *
     * @return the score of a player
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for the score variable
     *
     * @param score the score to set the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for the questionMaster boolean
     *
     * @return true if a player is the Question Master, false if not
     */
    public boolean getQuestionMaster() {
        return isQuestionMaster;
    }

    /**
     * Setter for the questionMaster boolean
     *
     * @param questionMaster the boolean state to set the questionMaster boolean to
     */
    public void setQuestionMaster(boolean questionMaster) {
        isQuestionMaster = questionMaster;
    }

    /**
     * Getter for the lobby
     *
     * @return the lobby in which the user resides
     */
    public GameLobby getLobby() {
        return lobby;
    }

    /**
     * Getter for the hasBeenQuestionMaster boolean
     *
     * @return true if the user has been the Question Master previously
     */
    public boolean isHasBeenQuestionMaster() {
        return hasBeenQuestionMaster;
    }

    /**
     * Setter for the hasBeenQuestionMaster boolean
     *
     * @param hasBeenQuestionMaster a boolean to represent whether the user has been the Question Master previously
     */
    public void setHasBeenQuestionMaster(boolean hasBeenQuestionMaster) {
        this.hasBeenQuestionMaster = hasBeenQuestionMaster;
    }

    /**
     * Getter for the user
     *
     * @return the user associated with a player
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the player's object output stream
     *
     * @return objectoutputstream
     */
    public ObjectOutputStream getOut() {
        return out;
    }

    /**
     * Method to process requests from the client during the game
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void processGameRequests() throws IOException, ClassNotFoundException {
        while (true) {
            Object o = in.readObject();
            if (o instanceof Message) {
                lobby.getChatRoom().addMessage((Message) o);
                lobby.getChatRoom().broadcastMessage((Message) o);
            } else if (o instanceof ServerProtocol && lobby.getCurrentGame() instanceof HeadlineGame) {
                ServerProtocol request = (ServerProtocol) o;
                String type = request.type;
                processHeadlineGameRequests(request, type);
            } else if (lobby.gameIsOver) {
                return;
            } else {
                out.writeObject(new ServerProtocol("invalid request"));
                out.flush();
            }
        }
    }

    /**
     * Method to process requests for the HeadlineGame
     *
     * @param request request to process
     * @param type    type of the request
     * @throws IOException
     */
    private void processHeadlineGameRequests(ServerProtocol request, String type) throws IOException {
        HeadlineGame game = (HeadlineGame) lobby.getCurrentGame();
        if (type.startsWith("answer")) {
            answer(request, game);
        } else if (type.startsWith("qm-vote")) {
            vote(request, game);
        } else if (type.startsWith("get-answers")) {
            sendAnswers(game);
        } else if (type.startsWith("leave")) {
            leave();
        } else if (type.startsWith("question")) {
            sendQuestion(game);
        } else if (type.startsWith("get-qm")) {
            sendQuestionMaster(game);
        } else if (type.startsWith("get-scores")) {
            sendScores(game);
        } else if (type.startsWith("next-round")) {
            game.addToNextCounter();
        } else {
            out.writeObject(new ServerProtocol("false", "invalid request"));
            out.flush();
        }
    }

    /**
     * Method to get an answer from a client
     *
     * @param request the protocol from the client
     * @param game    the headline game instance
     * @throws IOException
     */
    private void answer(ServerProtocol request, HeadlineGame game) throws IOException {
        game.addAnswer(this, request.message[0]);
    }

    /**
     * Method to get a vote from a client
     *
     * @param request the protocol from the client
     * @param game    the headline game instance
     * @throws IOException
     */
    private void vote(ServerProtocol request, HeadlineGame game) throws IOException {
        if (isQuestionMaster) {
            String sentAnswer = request.message[0];
            String username = game.getAnswerMap().get(sentAnswer).getUser().getUsername();
            if (username == null) {
                ServerProtocol message = new ServerProtocol("false", "invalid request");
                out.writeObject(message);
                out.flush();
            } else {
                game.addScore(Integer.valueOf(request.message[1]), username);
                ServerProtocol message = new ServerProtocol(lobby.getScores(username));
                for (Player player : game.getPlayers()) {
                    player.getOut().writeObject(message);
                    player.getOut().flush();
                }
            }
        } else {
            ServerProtocol message = new ServerProtocol("false", "invalid request");
            out.writeObject(message);
            out.flush();
        }
    }

    /**
     * Method to send answers to the client
     *
     * @param game the instance of the headline game
     * @throws IOException
     */
    private void sendAnswers(HeadlineGame game) throws IOException {
        if (isQuestionMaster) {
            List<String> list = new ArrayList<>();
            list.add("get-answers");
            list.add("qm");
            list.addAll(game.getAnswers());
            String[] toReturn = list.toArray(new String[list.size()]);
            ServerProtocol message = new ServerProtocol(toReturn);
            out.writeObject(message);
            out.flush();
        } else {
            List<String> list = new ArrayList<>();
            list.add("get-answers");
            list.add("notqm");
            list.addAll(game.getAnswers());
            String[] toReturn = list.toArray(new String[list.size()]);
            ServerProtocol message = new ServerProtocol(toReturn);
            out.writeObject(message);
            out.flush();
        }
    }

    /**
     * Method to let the client leave the game
     *
     * @throws IOException
     */
    private void leave() throws IOException {
        lobby.removePlayer(this);
        ServerProtocol message = new ServerProtocol("remove", "Left " + lobby.toString() + " successfully");
        out.writeObject(message);
        out.flush();
    }

    /**
     * Method to send the current question to the client
     *
     * @param game the headline game instance
     * @throws IOException
     */
    private void sendQuestion(HeadlineGame game) throws IOException {
        if (isQuestionMaster) {
            ServerProtocol message = new ServerProtocol("question", "qm", game.getCurrentQuestion());
            out.writeObject(message);
            out.flush();
        } else {
            ServerProtocol message = new ServerProtocol("question", "notqm", game.getCurrentQuestion());
            out.writeObject(message);
            out.flush();
        }
    }

    /**
     * Method to send the current question master to the clients
     *
     * @param game the instance of the headline game
     * @throws IOException
     */
    private void sendQuestionMaster(HeadlineGame game) throws IOException {
        ServerProtocol message = new ServerProtocol("get-qm", game.getQuestionMaster().getUser().getUsername());
        out.writeObject(message);
        out.flush();
    }

    /**
     * Method to send the current scores to the client
     *
     * @throws IOException
     */
    private void sendScores(HeadlineGame game) throws IOException {
        ServerProtocol message = new ServerProtocol(lobby.getScores("lobby-request"));
        System.out.println(message);
        out.writeObject(message);
        out.flush();
    }
}
