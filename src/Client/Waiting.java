package Client;

import Server.ServerProtocol;

import javax.swing.*;

/**
 * Waiting displays the waiting page that users are sent to whilst waiting for the lobby to become full
 * @author Florence
 * @version 21/03/18
 */

public class Waiting extends JPanel{

	private ClientGui gui;
	
	/**
	 * Waiting is a constructor that creates the panel.
	 * @param guiConstructor
	 */
	public Waiting(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);
        this.setBounds(0, 0, 400, 500);
        
        JLabel waiting = new JLabel("Waiting for other players...");
        waiting.setBounds(110, 160, 200, 16);
        add(waiting);
        
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            gui.setContentPane(
                    gui.lobby);
            gui.setTitle("Lobby Room");
        });
        back.setBounds(140, 240, 91, 29);
        add(back);
	}
}
