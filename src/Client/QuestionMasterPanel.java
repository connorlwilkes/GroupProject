package Client;

import javax.swing.*;
import java.awt.*;

public class QuestionMasterPanel extends JPanel {
    public JLabel lblNewLabel;
    public JLabel questionMaster;
    public JLabel lblRound;

    private ClientGui gui;

    /**
     * Create the panel.
     */
    public QuestionMasterPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(220, 20, 60));
        setLayout(null);

        lblNewLabel = new JLabel("The question master for this round will be! ");
        lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        lblNewLabel.setBounds(270, 68, 351, 51);
        add(lblNewLabel);

        questionMaster = new JLabel();
        questionMaster.setFont(new Font("Tahoma", Font.PLAIN, 15));
        questionMaster.setBounds(410, 130, 85, 37);
        add(questionMaster);

        lblRound = new JLabel("");
        lblRound.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRound.setBounds(20, 11, 78, 23);
        add(lblRound);


    }
}
