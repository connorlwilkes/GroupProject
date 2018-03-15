import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class instructionPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public instructionPanel() {
		
		setBackground(Color.PINK);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the game! ");
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		lblNewLabel.setBounds(330, 40, 185, 19);
		add(lblNewLabel);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtArea.setText("In the game there will be 4 rounds. In each round there will be"
				+ " a question for you to answer. You can type your answer to the question"
				+ "and once the time is up you will be able to see all the answers. The "
				+ "player who is question master that round will then get a chance to vote"
				+ "for which ever answer they like the most and that player will win that "
				+ "round. In the first round player 1 will be question master, in the "
				+ "second player two will be and so on.");
		txtArea.setBounds(250, 96, 407, 136);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		add(txtArea);

	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		JFrame frame = new  JFrame();
		frame.setBounds(100, 100, 900, 360); 
		instructionPanel panel = new instructionPanel();
		panel.setVisible(true);
		frame.setVisible(true);
		frame.add(panel);
	}
}
