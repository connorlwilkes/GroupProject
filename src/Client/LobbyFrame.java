package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyFrame extends JPanel {

    /**
     * lobbyDisplay is the display where the lobby to play in is chosen. It is the display that appears
     * once the user has logged-in successfully.
     * <p>
     * JFrame_______________________________
     * | ___________________________________|
     * | |								   ||
     * | |								   ||
     * | |								   ||           _______________
     * | |			  			           ||           |   Lobby 1   |
     * | |								   ||           |_____________|
     * | |								   ||
     * | |								   ||           _______________
     * | |       JPanel = lobbyPanel       || ===>      |   Lobby 2   |
     * | |                                 ||           |_____________|
     * | |								   ||
     * | |								   ||           _______________
     * | |			           			   ||           |   Lobby 3   |
     * | |								   ||           |_____________|
     * | |								   ||
     * | |								   ||
     * | ___________________________________|
     * ______________________________________
     */
    public LobbyFrame() {
        
    	setLayout(null);
    	
    	 JButton btnLobby1 = new JButton("Lobby 1");
         btnLobby1.addActionListener(e -> {
        	 // insert function
         });
         btnLobby1.setBounds(175, 190, 100, 29);
         add(btnLobby1);
         
     JButton btnLobby2 = new JButton("Lobby 2");
         btnLobby2.addActionListener(e -> {
        	 // insert function
         });
         btnLobby2.setBounds(175, 230, 100, 29);
         add(btnLobby2);
         
     JButton btnLobby3 = new JButton("Lobby 3");
         btnLobby3.addActionListener(e -> {
        	 // insert function
         });
         btnLobby3.setBounds(175, 270, 100, 29);
         add(btnLobby3);
    }
    	
}