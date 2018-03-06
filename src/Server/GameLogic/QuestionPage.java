package Server.GameLogic;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import java.awt.Color;


public class QuestionPage {

	private JFrame frame;
	private JTextArea txtQuestion;
	private JTextField txtRound;
	private JTextField txtCountdown;

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionPage window = new QuestionPage();
					window.frame.setVisible(true);
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
	public QuestionPage() throws ClassNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() throws ClassNotFoundException {
		String [] questions = QuestionClass.getQuestionHeader();
		
		for (int i = 0; i < 3; i++)  {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.MAGENTA);
		frame.getContentPane().setLayout(null);
		
		txtRound = new JTextField();
		int j = 0;
		txtRound.setText("round " + (j+1) );
		j++;
		txtRound.setBounds(10, 11, 86, 20);
		frame.getContentPane().add(txtRound);
		txtRound.setColumns(10);
		
		txtQuestion = new JTextArea();
		txtQuestion.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		txtQuestion.setBounds(50, 48, 370, 60);
		frame.getContentPane().add(txtQuestion);
		txtQuestion.setColumns(50);
		
			txtCountdown = new JTextField();
		txtCountdown.setBounds(326, 230, 86, 20);
		frame.getContentPane().add(txtCountdown);
		txtCountdown.setColumns(10);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (i == 0) {
			txtQuestion.setText(questions[0]);
		}
		if (i == 1) {
			txtQuestion.setText(questions[1]);
		}
		if (i == 2) {
			txtQuestion.setText(questions[2]);
		}
		
		Timer timer = new Timer(1000, new ActionListener() {
		    private int count = 5;
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (count <= 0) {
		            txtCountdown.setText("end");
		            ((Timer)e.getSource()).stop();
		            count = 5;
		        } else {
		            txtCountdown.setText(Integer.toString(count));
		            count--;
		        }
		    }});
		    
		timer.start();
		if (txtCountdown.getText() == "end") {
			timer.stop();
		}
		continue;
	}}
	
}

