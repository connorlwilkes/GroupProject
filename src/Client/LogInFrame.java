package Client;

import Client.ClientGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class LogInFrame extends JPanel {

	    private JTextField enterUsername;
	    private JTextField enterPassword;
	    // private String username;
	    // private String password;

	    public LogInFrame() {
	    		setLayout(null);
	    		this.setBounds(0, 0, 450, 278);
	    		
	    		JLabel lblUser = new JLabel("Username: ");
	    		lblUser.setBounds(110, 113, 79, 16);
	    		add(lblUser);
	    		
	    		enterUsername = new JTextField();
	    		enterUsername.setBounds(190, 108, 130, 26);
	    		enterUsername.setColumns(12);
	    		add(enterUsername);
	    		
	    		JLabel lblPass = new JLabel("Password: ");
	    		lblPass.setBounds(110, 154, 79, 16);
	    		add(lblPass);
	    		
	    		enterPassword = new JTextField();
	    		enterPassword.setBounds(190, 149, 130, 26);
	    		enterPassword.setColumns(12);
	    		add(enterPassword);
	    		
	    		JButton btnSignIn = new JButton("Sign In");
	    		btnSignIn.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				//ClientGui.gui.setContentPane(
	    				//		ClientGui.gui.login);
	    				ClientGui.gui.setTitle("Sign in");
	    			}
	    		});
	    		btnSignIn.setBounds(175, 190, 91, 29);
	    		add(btnSignIn);
	    		
	    		JLabel lblNoAccount = new JLabel("Don't have an account?");
	    		lblNoAccount.setBounds(150, 250, 180, 16);
	    		add(lblNoAccount);
	    			
	    		JButton btnSignUp = new JButton("Sign Up");
	    		btnSignUp.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				// ClientGui.gui.setContentPane(
	    				//		ClientGui.gui.guiSignUp);
	    				ClientGui.gui.setTitle("Sign up");
	    			}
	    		});
	    		btnSignUp.setBounds(175, 280, 91, 29);
	    		add(btnSignUp);
	    		
	    		
	    }
	
}
