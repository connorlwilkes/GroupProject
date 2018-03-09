package Client;

import Client.ClientGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JPanel {
	
	 	private JTextField enterUser;
	    private JTextField enterPass;
	    private JTextField reEnterPass;

	    public RegisterFrame() {
	    		setLayout(null);
	    		this.setBounds(0, 0, 450, 278);
	    		
	    		JLabel lblUser = new JLabel("Username: ");
	    		lblUser.setBounds(120, 113, 79, 16);
	    		add(lblUser);
	    		
	    		enterUser = new JTextField();
	    		enterUser.setBounds(200, 108, 130, 26);
	    		enterUser.setColumns(12);
	    		add(enterUser);
	    		
	    		JLabel lblPass = new JLabel("Password: ");
	    		lblPass.setBounds(120, 154, 79, 16);
	    		add(lblPass);
	    		
	    		enterPass = new JTextField();
	    		enterPass.setBounds(200, 149, 130, 26);
	    		enterPass.setColumns(12);
	    		add(enterPass);
	    		
	    		JLabel lblReEnterPass = new JLabel("ReEnter Password: ");
	    		lblReEnterPass.setBounds(80, 195, 130, 16);
	    		add(lblReEnterPass);
	    		
	    		reEnterPass = new JTextField();
	    		reEnterPass.setBounds(200, 190, 130, 26);
	    		reEnterPass.setColumns(12);
	    		add(reEnterPass);
	    		
	    		JButton btnSignIn = new JButton("Sign In");
	    		btnSignIn.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				//ClientGui.gui.setContentPane(
	    				//		ClientGui.gui.login);
	    				ClientGui.gui.setTitle("Sign in");
	    			}
	    		});
	    		btnSignIn.setBounds(215, 240, 91, 29);
	    		add(btnSignIn);
	    		
	    		JButton back = new JButton("Back");
	    		back.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				// ClientGui.gui.setContentPane(
	    				//		ClientGui.gui.guiSignUp);
	    				ClientGui.gui.setTitle("Back");
	    			}
	    		});
	    		back.setBounds(125, 240, 91, 29);
	    		add(back);
	    		
	    		
	    }
	
}


    