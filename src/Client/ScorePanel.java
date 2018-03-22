package Client;

import javax.swing.*;
import java.awt.*;

/**
 * ScorePanel displays the game scoreboard once three rounds have been played
 * @author Florence
 * @version 21/03/18
 */
public class ScorePanel extends JPanel {

    public JTextField txtPlayerName;
    public JTextArea textArea;
    private ClientGui gui;
    private JLabel txtAndTheWinner;
    public JLabel lblRound;

    /**
     * ScorePanel is a constructor that creates the panel.
     * @param guiConstructor
     */
    public ScorePanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(0, 255, 127));
        setLayout(null);

        lblRound = new JLabel("Round ");
        lblRound.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRound.setBounds(20, 11, 78, 23);
        add(lblRound);

        txtAndTheWinner = new JLabel();
        txtAndTheWinner.setBackground(new Color(255, 255, 255));
        txtAndTheWinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtAndTheWinner.setText("And the winner of this round! ");
        txtAndTheWinner.setBounds(20, 49, 202, 38);
        add(txtAndTheWinner);


        txtPlayerName = new JTextField();
        txtPlayerName.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        txtPlayerName.setText("Player name");
        txtPlayerName.setBounds(20, 98, 172, 38);
        add(txtPlayerName);
        txtPlayerName.setColumns(10);

        JLabel lblNewLabel = new JLabel("The overall score board looks like this: ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(20, 147, 245, 30);
        add(lblNewLabel);

        textArea = new JTextArea();
        textArea.setBounds(20, 188, 232, 88);
        add(textArea);

    }
}
