package Server;

import Server.Login.DatabaseQueries;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for the HeadlineGame. Sends the players in the lobby a headline with a missing word, Players submit what they
 * think is the funniest word to complete the headline. The question master of that particular round then chooses the
 * best answer. The winner then scores a point.
 *
 * @author Florence
 * @version 9/3/2018
 */
public class HeadlineGame extends Minigame {

    private Player questionMaster;
    private List<String> questions;
    private List<String> answers;

    public HeadlineGame(List<Player> players) {
        super(players);
        chooseQuestionMaster();
        this.questions = questions;
        answers = new ArrayList<>();
        questions = new ArrayList<>();
        try {
            setQuestions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Player getQuestionMaster() {
        return questionMaster;
    }

    public String getCurrentQuestion() {
        return questions.get(getRoundNumber() - 1);
    }

    public synchronized void addAnswer(String answer) {
        answers.add(answer);
    }

    /**
     * Chooses the next question master randomly from the list of players in the game. Will ensure that a new player is
     * picked each round until the number of rounds exceed the number of players in which case each player will be
     * eligible to be question master again
     */
    public synchronized void chooseQuestionMaster() {
        questionMaster.setHasBeenQuestionMaster(true);
        if (getRoundNumber() == getPlayers().size()) {
            for (Player player : getPlayers()) {
                player.setHasBeenQuestionMaster(false);
            }
        }
        Random random = new Random();
        int index = random.nextInt(getPlayers().size() - 1);
        Player player = getPlayers().get(index);
        while (player.isHasBeenQuestionMaster()) {
            index = random.nextInt(getPlayers().size() - 1);
            player = getPlayers().get(index);
        }
        questionMaster.setQuestionMaster(false);
        player.setQuestionMaster(true);
        questionMaster = player;
    }
    /**
     * Sets the questions for the round
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
