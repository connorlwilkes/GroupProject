package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuestionPage {

    JFrame frame;
    private JTextArea txtQuestion;
    private JTextField txtRound;
    private JTextField txtCountdown;

    /**
     * Create the application.
     *
     * @return
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @wbp.parser.entryPoint
     */
    public QuestionPage(String[] question, int i) throws ClassNotFoundException, InterruptedException { // question page constructor
        initialize1(question, i);
    }

    public static void StartGame() throws ClassNotFoundException, InterruptedException, SQLException {
        String[] questions = QuestionClass.QuestionHeader();                    //calls the method for getting the questions and assigns them to an array.
        for (int i = 0; i < 4; i++) {                                            // for  loop to initiate each round
            QuestionPage questionWindow = new QuestionPage(questions, i);        //initially creating a window with the question on it
            questionWindow.frame.setVisible(true);                                //setting the question window to visible
            AnswerPage answerWindow = new AnswerPage(questions, i);                //initially creating the answer page
            answerWindow.frame.setVisible(false);                                //setting the answer page to be not visible

            Timer timer = new Timer(6000, new ActionListener() {                //swing timer that will wait and the do an action
                @Override
                public void actionPerformed(ActionEvent e) {                    // the action the timer will do is to change the visibility of queestion and answer windows

                    answerWindow.frame.setVisible(true);
                    questionWindow.frame.setVisible(false);
                }
            });
            timer.start();                                                        //start the timer
            Thread t1 = new Thread();
            t1.start();
            Thread.sleep(10000);
        }


    }

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException {
        QuestionPage.StartGame();

    }

    /**
     * Initialize the contents of the frame.
     *
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @wbp.parser.entryPoint
     */
    private void initialize1(String[] questions, int i) throws ClassNotFoundException, InterruptedException {


        frame = new JFrame();                                                            //creates a jFrame
        frame.getContentPane().setLayout(null);                                            //sets the lay out to be non so can just put things anywhere
        frame.setBounds(100, 100, 900, 340);                                            //sets the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                            //sets how to close the frame

        txtRound = new JTextField();                                                    //creates a text field
        txtRound.setText("round " + (i + 1));                                            //marks what round number it is based on what iteration of the for loop it is

        txtRound.setBounds(10, 11, 86, 20);                                                //sets the size of the box
        frame.getContentPane().add(txtRound);                                            //adds it to the frame
        txtRound.setColumns(10);

        txtQuestion = new JTextArea();                                                    //creates a new text area
        txtQuestion.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));                //sets the font and text size
        txtQuestion.setBounds(50, 48, 700, 60);                                        //sets the size of the area
        frame.getContentPane().add(txtQuestion);                                        //adds it to the frame
        txtQuestion.setColumns(50);
        txtQuestion.setText(questions[i]);                                            //sets the text to the corresponding string in the array to the iteration of hte for loop
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        txtQuestion.setEditable(false);

        txtCountdown = new JTextField();                                                //creates a text field for the countdown
        txtCountdown.setBounds(700, 230, 50, 50);                                        //sets the size and position
        frame.getContentPane().add(txtCountdown);                                        //adds it to the frame
        txtCountdown.setColumns(10);
        txtCountdown.setFont(new Font("Tahoma", Font.PLAIN, 21));


        Timer timer = new Timer(1000, new ActionListener() {                            //creates a timer
            private int count = 5;

            @Override
            public void actionPerformed(ActionEvent e) {                                //after each second the timer will reduce the counter  by one until it is one and then displays end
                if (count <= 0) {
                    txtCountdown.setText("end");
                    ((Timer) e.getSource()).stop();
                    count = 5;
                } else {
                    txtCountdown.setText(Integer.toString(count));
                    count--;
                }
            }
        });

        timer.start();                                                                    //starting the timer


    }

}


