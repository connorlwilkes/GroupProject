import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

public class QuestionMasterPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public QuestionMasterPanel(int i) {
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
		
		JLabel lblRound = new JLabel("Round " + (i+1));
		lblRound.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRound.setBounds(20, 11, 78, 23);
		add(lblRound);
		
		

	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		JFrame frame = new  JFrame();
		frame.setBounds(100, 100, 900, 360); 
		QuestionMasterPanel panel = new QuestionMasterPanel(0);
		panel.setVisible(true);
		frame.setVisible(true);
		frame.add(panel);
	}

}
