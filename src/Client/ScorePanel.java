package Client;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private JLabel txtAndTheWinner;
    private JTextField txtPlayerName;

    /**
     * Create the panel.
     */
    public ScorePanel() {
        setBackground(new Color(0, 255, 127));
        setLayout(null);

        JLabel lblRound = new JLabel("Round ");
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

        JTextArea textArea = new JTextArea("player 1: + getPlayer1Score\nplayer 2: + getPlayer2Score\nplayer 3: + getPlayer3Score");
        textArea.setBounds(20, 188, 232, 88);
        add(textArea);

    }
}
