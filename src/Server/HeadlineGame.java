package Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for the HeadlineGame. Sends the players in the lobby a headline with a missing word, Players submit what they
 * think is the funniest word to complete the headline. The question master of that particular round then chooses the
 * best answer. The winner then scores a point.
 * Has the fields questionMaster of type Player; questions of type List<String>; answerMap of type Map<String,Player>; 
 * and nextCounter of type int
 * @author Florence
 * @version 9/3/2018
 */
public class HeadlineGame extends Minigame {

    private Player questionMaster;
    private List<String> questions;
    private Map<String, Player> answerMap;
    private List<String> answers;
    public int nextCounter;

    /**
     * Constructor for HeadlineGame, reuses fields lobby and players as arguments
     * @param lobby The lobby which the game resides in
     * @param players The players in the game
     */
    public HeadlineGame(GameLobby lobby, List<Player> players) {
        super(lobby, players);
        chooseQuestionMaster();
        this.questions = questions;
        answerMap = new HashMap<>();
        answers = new ArrayList<>();
        questions = new ArrayList<>();
        nextCounter = 0;
        try {
            setQuestions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Player player : getPlayers()) {
            try {
                ServerProtocol start = new ServerProtocol("start", "game is starting", String.valueOf(getRoundNumber()));
                player.getOut().writeObject(start);
                player.getOut().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to increase the counter
     */
    public synchronized void addToNextCounter() {
        nextCounter++;
        if (nextCounter == getPlayers().size()) {
            if (getRoundNumber() == 4) {
                endGame();
            }
            nextRound();
        }
    }

    
    /**
     * Method to cyle through the game rounds
     */
    public void nextRound() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getRoundNumber() == 3) {
            endGame();
            return;
        }
        nextCounter = 0;
        setRoundNumber(getRoundNumber() + 1);
        answerMap = new HashMap<>();
        answers = new ArrayList<>();
        chooseQuestionMaster();
        try {
            for (Player player : getPlayers()) {
                ServerProtocol start = new ServerProtocol("start", "game is starting", String.valueOf(getRoundNumber()));
                player.getOut().writeObject(start);
                player.getOut().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to end the game
     */
    public void endGame() {
        try {
            List<String> toReturn = new ArrayList<>();
            toReturn.add("end");
            toReturn.add("game is over");
            toReturn.addAll(winner());
            for (Player player : getPlayers()) {
                ServerProtocol end = new ServerProtocol(toReturn.toArray(new String[toReturn.size()]));
                player.getOut().writeObject(end);
                player.getOut().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the QuestionMaster
     * @return true if a player is the QuestionMaster for the round, false is not
     */
    public Player getQuestionMaster() {
        return questionMaster;
    }

    /**
     * Getter for the CurrentQuestion
     * @return the question for the current round of the game
     */
    public String getCurrentQuestion() {
        return questions.get(getRoundNumber() - 1);
    }
 
    /**
     * Getter for the AnswerMap
     * @return a hashMap containing all players in the game and their answers for the current question. 
     */
    public Map<String, Player> getAnswerMap() {
        return answerMap;
    }

    /**
     * Method to add an answer to the answerMap hashmap and list of answers
     *
     * @param player key
     * @param answer value
     */
    public synchronized void addAnswer(Player player, String answer) {
        answerMap.put(answer, player);
        answers.add(answer);
    }

    /**
     * Getter for the Answers
     * @return the answers provided by players to the current question
     */
    public synchronized List<String> getAnswers() {
        return answers;
    }

    /**
     * Chooses the next question master randomly from the list of players in the game. Will ensure that a new player is
     * picked each round until the number of rounds exceed the number of players in which case each player will be
     * eligible to be question master again
     */
    public synchronized void chooseQuestionMaster() {
        questionMaster = getPlayers().get(getRoundNumber() - 1);
        getPlayers().get(getRoundNumber() - 1).setQuestionMaster(true);
        for (Player player: getPlayers()) {
            if(!(player.equals(questionMaster))) {
                player.setQuestionMaster(false);
            }
        }
    }

    /**
     * Seter for the question for each round
     *
     * @throws SQLException
     */
    private synchronized void setQuestions() throws SQLException {
        int[] result = ThreadLocalRandom.current().ints(1, 20).distinct().limit(5).toArray();
        Connection connection = DatabaseQueries.connect();
        Statement stmt;
        ResultSet rs;
        for (int i = 0; i < 5; i++) {
            int questionId = result[i];
            stmt = connection.createStatement();
            String query = "SELECT question FROM questions_table WHERE id = " + questionId;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("question");
                questions.add(s);
            }
        }
    }

}
