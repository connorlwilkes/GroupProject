
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AnswerPage {

	JFrame frame;
	private JTextArea txtQuestionAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	public AnswerPage(String [] question, int i) throws ClassNotFoundException {
		initialize(question, i );
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String [] questions, int i) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField txtRound = new JTextField();
		txtRound.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtRound.setText("round " + (i + 1) );
		txtRound.setBounds(10, 11, 86, 20);
		frame.getContentPane().add(txtRound);
		txtRound.setColumns(10);
		txtQuestionAnswer = new JTextArea();
		txtQuestionAnswer.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		txtQuestionAnswer.setBounds(35, 34, 700, 60);
		frame.getContentPane().add(txtQuestionAnswer);
		txtQuestionAnswer.setColumns(50);
		
		txtQuestionAnswer.setText(questions[i]);
		txtQuestionAnswer.setLineWrap(true);
		txtQuestionAnswer.setWrapStyleWord(true);	
		txtQuestionAnswer.setEditable(false);	
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 105, 200, 80);
		frame.getContentPane().add(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);	
		textArea.setEditable(false);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(250, 105, 200, 80);
		frame.getContentPane().add(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);	
		textArea_1.setEditable(false);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(35, 196, 200, 80);
		frame.getContentPane().add(textArea_2);
		textArea_2.setLineWrap(true);
		textArea_2.setWrapStyleWord(true);	
		textArea_2.setEditable(false);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(250, 196,200, 80);
		frame.getContentPane().add(textArea_3);
		textArea_3.setLineWrap(true);
		textArea_3.setWrapStyleWord(true);	
		textArea_3.setEditable(false);
		
		if (i == 0) {
			textArea.setText("player 1 it is your turn to vote for the best answer");
		}
		
		if (i == 1) {
			textArea_1.setText("player 2 it is your turn to vote for the best answer");
		}
		
		if (i == 2) {
			textArea_2.setText("player 3 it is your turn to vote for the best answer");
		}
		
		if (i == 3) {
			textArea_3.setText("player 4 it is your turn to vote for the best answer");
		}
	}}

