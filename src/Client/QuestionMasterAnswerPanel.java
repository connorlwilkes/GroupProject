package Client;

import Server.ServerProtocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * QuestionMasterAnswerPanel displays the other players answers to the Question Master so they
 * can choose the winning answer
 * @author Florence
 * @version 16/03/18
 *
 */
public class QuestionMasterAnswerPanel extends JPanel {

    public JTextArea txtrQuestion = new JTextArea();
    public JTextArea txtrPlayerAnswer = new JTextArea();
    public JTextArea txtrPlayerAnswer_1 = new JTextArea();
    public boolean voteCast = false;
    private ClientGui gui;
    public String answer;

    /**
     * Create the panel.
     */
    public QuestionMasterAnswerPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(85, 107, 47));
        setLayout(null);


        JLabel lblNewLabel = new JLabel("Round");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(20, 11, 80, 28);
        add(lblNewLabel);

        txtrQuestion = new JTextArea();
        txtrQuestion.setFont(new Font("Showcard Gothic", Font.PLAIN, 13));
        txtrQuestion.setBounds(20, 41, 450, 80);
        txtrQuestion.setLineWrap(true);
        txtrQuestion.setWrapStyleWord(true);
        add(txtrQuestion);

        txtrPlayerAnswer = new JTextArea();
        txtrPlayerAnswer.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtrPlayerAnswer.setText("player 1 answer");
        txtrPlayerAnswer.setBounds(20, 132, 450, 80);
        txtrPlayerAnswer.setLineWrap(true);
        txtrPlayerAnswer.setWrapStyleWord(true);
        add(txtrPlayerAnswer);

        txtrPlayerAnswer_1 = new JTextArea();
        txtrPlayerAnswer_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtrPlayerAnswer_1.setText("player 2 answer");
        txtrPlayerAnswer_1.setBounds(20, 224, 450, 80);
        txtrPlayerAnswer_1.setLineWrap(true);
        txtrPlayerAnswer_1.setWrapStyleWord(true);
        add(txtrPlayerAnswer_1);


        JButton btnNewButton = new JButton("Choose Answer");
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                voteCast = true;
                answer = txtrPlayerAnswer.getText();
            }
        });

        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton.setBounds(480, 145, 125, 50);
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Choose Answer");
        btnNewButton_1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                voteCast = true;
                answer = txtrPlayerAnswer_1.getText();
            }
        });

        btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_1.setBounds(480, 242, 125, 50);
        add(btnNewButton_1);

    }

    public static void main(String[] args) {

    }

    public boolean getVoteCast() {
        return voteCast;
    }

}


