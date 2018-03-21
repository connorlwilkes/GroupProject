package Client;

import Server.ServerProtocol;

import javax.swing.*;

/**
 * LogInFrame is the class for the log in page GUI where the user enters their account details to log in. 
 * It extends JPanel and is added to a JFrame in ClientGui
 * @author Florence
 * @version 14/03/18
 *
 */

public class Waiting extends JPanel{

	private ClientGui gui;
	
	public Waiting(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);
        this.setBounds(0, 0, 400, 500);
        
        JLabel waiting = new JLabel("Waiting for other players...");
        waiting.setBounds(110, 160, 200, 16);
        add(waiting);
	}
}
