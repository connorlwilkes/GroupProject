package Client;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class QuestionMasterPanel extends JPanel {

    /**
     * Create the panel.
     */
    public QuestionMasterPanel() {
        setBackground(new Color(220, 20, 60));
        setLayout(null);

        //ClientGui.gui.client.connect();
        //ClientGui.gui.client.serverRequest();

        JLabel lblNewLabel = new JLabel("The question master in this round will be! ");
        lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        lblNewLabel.setBounds(270, 68, 351, 51);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("player 1");   //make a call to get player username who is question master.
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(410, 130, 85, 37);
        add(lblNewLabel_1);

        JLabel lblRound = new JLabel("Round " + getRound());
        lblRound.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRound.setBounds(20, 11, 78, 23);
        add(lblRound);


    }

    public String getRound() {
        return null;
    }

}