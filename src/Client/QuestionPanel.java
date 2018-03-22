package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

/**
 * QuestionPanel displays the question for the round
 * @author Florence
 * @version 18/03/18
 *
 */
public class QuestionPanel extends JPanel {

    public JTextArea txtrQuestion = new JTextArea();
    public String playerAnswer;
    private JLabel timerOutput;
    private ClientGui gui;
    public JLabel roundNumber;
    public Timer timer;
    private long startTime = -1;
    private long timeLeft = 5000;


    /**
     * QuestionPanel is a constructor that creates the panel.
     * @param guiConstructor
     */
    public QuestionPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(154, 205, 50));
        setLayout(null);

        roundNumber = new JLabel("Round ");
        roundNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
        roundNumber.setBounds(10, 11, 80, 28);
        add(roundNumber);

        txtrQuestion = new JTextArea();
        txtrQuestion.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        txtrQuestion.setText("question");
        txtrQuestion.setBounds(20, 50, 450, 79);
        txtrQuestion.setLineWrap(true);
        txtrQuestion.setWrapStyleWord(true);
        add(txtrQuestion);

        JTextArea txtrAnswer = new JTextArea();
        txtrAnswer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtrAnswer.setText("answer");
        txtrAnswer.setBounds(20, 140, 450, 103);
        txtrAnswer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtrAnswer.setText("");
            }
        });
        add(txtrAnswer);

        JButton btnNewButton = new JButton("Send Answer!");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton.setBounds(165, 254, 152, 35);
        btnNewButton.addActionListener(e -> {
            playerAnswer = txtrAnswer.getText();
            txtrAnswer.setEditable(false);
            txtrAnswer.setText("Answer Sent!");
        });
        add(btnNewButton);

//        timerOutput = new JLabel();
//        timerOutput.setBounds(20, 254, 44, 35);
//        add(timerOutput);
//
//        timer = new Timer(10, new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                timeLeft -= 100;
//                SimpleDateFormat df = new SimpleDateFormat("mm:ss:S");
//                timerOutput.setText(df.format(timeLeft));
//                if (timeLeft <= 0) {
//                    timer.stop();
//                }
//            }
//        });
        //timer.start();
    }

//        Timer timer = new Timer(1000, new ActionListener() {                            //creates a timer
//            private int count = 5;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {                                //after each second the timer will reduce the counter  by one until it is one and then displays end
//                if (count <= 0) {
//                    textField.setText("end");
//                    ((Timer) e.getSource()).stop();
//                    count = 5;
//                } else {
//                    textField.setText(Integer.toString(count));
//                    count--;
//                }
//            }
//        });
//
//        timer.start();
}
