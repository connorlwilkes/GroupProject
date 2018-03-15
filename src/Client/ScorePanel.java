import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ScorePanel extends JPanel {
	private JLabel txtAndTheWinner;
	private JTextField txtPlayerName;

	/**
	 * Create the panel.
	 */
	public ScorePanel(int i) {
		setBackground(new Color(0, 255, 127));
		setLayout(null);
		
		JLabel lblRound = new JLabel("Round " + (i+1));
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
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		JFrame frame = new  JFrame();
		frame.setBounds(100, 100, 900, 360); 
		ScorePanel panel = new ScorePanel(0);
		panel.setVisible(true);
		frame.setVisible(true);
		frame.getContentPane().add(panel);
	}
}
