package Client;

import javax.swing.*;
import java.awt.*;

/**
 * QuestionMasterPanel randomly chooses a Question Master and displays which user it is
 * @author Florence
 * @version 17/03/18
 */
public class QuestionMasterPanel extends JPanel {
    
	public JLabel lblNewLabel = new JLabel();
    public JLabel lblNewLabel_1 = new JLabel();
    private ClientGui gui;

    /**
     * QuestionMasterPanel is a constructor that creates the panel.
     * @param guiConstructor
     */
    public QuestionMasterPanel(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setBackground(new Color(220, 20, 60));
        setLayout(null);

        lblNewLabel = new JLabel("The question master in this round will be! ");
        lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        lblNewLabel.setBounds(270, 68, 351, 51);
        add(lblNewLabel);

        lblNewLabel_1 = new JLabel();   //make a call to get player username who is question master.
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(410, 130, 85, 37);
        add(lblNewLabel_1);

        JLabel lblRound = new JLabel("Round ");
        lblRound.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRound.setBounds(20, 11, 78, 23);
        add(lblRound);


    }
}
