package Client;

import javax.swing.*;
import java.awt.*;

/**
 * AnswerPanel displays each users answer to the question
 * @author Florence
 * @version 14/03/18
 *
 */
public class AnswerPanel extends JPanel {
    public JTextArea txtrQuestion = new JTextArea();
    public JTextArea txtrPlayerAnswer = new JTextArea();
    public JTextArea txtrPlayerAnswer_1 = new JTextArea();
    private ClientGui gui;
    private JTextField textField;

    /**
     * Create the panel.
     */
    public AnswerPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(211, 211, 211));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Round ");
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

    }
}
