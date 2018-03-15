package Client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public QuestionPanel() {
		setBackground(new Color(154, 205, 50));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Round ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 80, 28);
		add(lblNewLabel);
		
		JTextArea txtrQuestion = new JTextArea();
		txtrQuestion.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		txtrQuestion.setText("question");
		txtrQuestion.setBounds(20, 50, 450, 79);
		add(txtrQuestion);
		
		JTextArea txtrAnswer = new JTextArea();
		txtrAnswer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrAnswer.setText("answer");
		txtrAnswer.setBounds(20, 140, 450, 103);
		txtrAnswer.addMouseListener(new MouseAdapter(){
	            @Override
	            public void mouseClicked(MouseEvent e){
	                txtrAnswer.setText("");
	            }
	        });
		add(txtrAnswer);
		
		JButton btnNewButton = new JButton("Send Answer!");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(165, 254, 152, 35);
		//btnNewButton.addActionListener(e -> {
			
		//});
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(20, 254, 44, 35);
		add(textField);
		textField.setColumns(10);
		
		Timer timer = new Timer(1000, new ActionListener() {							//creates a timer 
		    private int count = 5;														
		    @Override										
		    public void actionPerformed(ActionEvent e) {								//after each second the timer will reduce the counter  by one until it is one and then displays end 
		        if (count <= 0) {
		            textField.setText("end");
		            ((Timer)e.getSource()).stop();
		            count = 5;
		        } else {
		            textField.setText(Integer.toString(count));
		            count--;
		        }
		    }});
		    
		timer.start();	
	
	}
}