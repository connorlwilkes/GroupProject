package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * QuestionPanel displays the question for the round
 * @author Florence
 * @version 18/03/18
 *
 */
public class QuestionPanel extends JPanel {

    public JTextArea txtrQuestion = new JTextArea();
    public String playerAnswer;
    private JTextField textField;
    private ClientGui gui;

    /**
     * QuestionPanel is a constructor that creates the panel.
     * @param guiConstructor
     */
    public QuestionPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(154, 205, 50));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Round ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 11, 80, 28);
        add(lblNewLabel);

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
            txtrAnswer.setText("");
        });
        add(btnNewButton);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textField.setBounds(20, 254, 44, 35);
        add(textField);
        textField.setColumns(10);

        Timer timer = new Timer(1000, new ActionListener() {                            //creates a timer
            private int count = 5;

            @Override
            public void actionPerformed(ActionEvent e) {                                //after each second the timer will reduce the counter  by one until it is one and then displays end
                if (count <= 0) {
                    textField.setText("end");
                    ((Timer) e.getSource()).stop();
                    count = 5;
                } else {
                    textField.setText(Integer.toString(count));
                    count--;
                }
            }
        });

        timer.start();

    }
}
