import java.sql.SQLException;

import javax.swing.JFrame;

public class StartGame {
	
	public static void startGame() throws InterruptedException {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 900, 360);
		instructionPanel instructionPanel = new instructionPanel(); 
		instructionPanel.setVisible(true);
		frame.setVisible(true);
		frame.add(instructionPanel);
		frame.revalidate();
		frame.repaint();
		
		
		Thread.sleep(1000);
		
		instructionPanel.setVisible(false);
		frame.remove(instructionPanel);
		frame.revalidate();
		frame.repaint();
		
		for (int i = 0; i < 4; i++) {
	
			
			QuestionMasterPanel QuestionMasterPanel = new QuestionMasterPanel(i);
			QuestionMasterPanel.setVisible(true);
			frame.add(QuestionMasterPanel);
			frame.revalidate();
			frame.repaint();
			
			Thread.sleep(2000);
			
			frame.getContentPane().remove(QuestionMasterPanel);
			frame.revalidate();
			frame.repaint();
			
			
			/*if (player == questionMaster) {
				QuestionMasterQuestionPanel QuestionMasterQuestionPanel = new QuestionMasterQuestionPanel(i); 
				QuestionMasterQuestionPanel.setVisible(true);
				fram3.add(QuestionMasterQuestionPanel);
			}*/
			//else {
				QuestionPanel QuestionPanel = new QuestionPanel(i);
				QuestionPanel.setVisible(true);
				frame.getContentPane().add(QuestionPanel);
				frame.revalidate();
				frame.repaint();
			//}
			
			Thread t3 = new Thread();											//creating a thread to sleep so that it delays the loop from happening until its time
			t3.start();
			Thread.sleep(2000);
			
			frame.getContentPane().remove(QuestionPanel);
			frame.revalidate();
			frame.repaint();
			
			//if (player == questionMaster) {
				QuestionMasterAnswerPanel QuestionMasterAnswerPanel = new QuestionMasterAnswerPanel(i);
				QuestionMasterAnswerPanel.setVisible(true);
				frame.add(QuestionMasterAnswerPanel);
				frame.revalidate();
				frame.repaint();
				
				
			//}
			
			/*else {
				AnswerPanel AnswerPanel = new AnswerPanel(i);
				AnswerPanel.setVisible(true);
				frame.add(AnswerPanel);
				frame.revalidate();
				frame.repaint();
			}*/
			
			while (QuestionMasterAnswerPanel.voteCast == false) {
				Thread.sleep(200);
			}
			
			//if (player  == QuestionMaster) {
			frame.remove(QuestionMasterAnswerPanel);
			frame.revalidate();
			frame.repaint();
			//}
			
			/*else {
				frame.remove(AnswerPanel);
				frame.revalidate();
				frame.repaint();
			}*/
			
			
			
			ScorePanel ScorePanel = new ScorePanel(i);
			ScorePanel.setVisible(true);
			frame.add(ScorePanel);
			frame.revalidate();
			frame.repaint();
			
			Thread.sleep(2000);
			
			frame.remove(ScorePanel);
			frame.revalidate();
			frame.repaint();
		}
		
	}
	
	public static void main(String[] args)throws ClassNotFoundException, InterruptedException, SQLException {
		StartGame.startGame();
	}

}
