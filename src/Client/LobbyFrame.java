package Client;

import Server.ServerProtocol;

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
    public void lobbyFrame() {
        //TODO: Create lobby and game gui - Jack
        setLayout(null);
        this.setBounds(0, 0, 450, 278);

        JButton lobby1 = new JButton("Lobby 1");
        lobby1.setBounds(150, 60, 90, 30);
        add(lobby1);

        JButton lobby2 = new JButton("Lobby 2");
        lobby1.setBounds(150, 120, 90, 30);
        add(lobby2);

        JButton lobby3 = new JButton("Lobby 3");
        lobby1.setBounds(150, 180, 90, 30);
        add(lobby3);

        lobby1.addActionListener(new enterLobby1());    // adds action listener to open lobby 1 chatDisplay when clicked
        lobby2.addActionListener(new enterLobby2());    // adds action listener to open lobby 2 chatDisplay when clicked
        lobby3.addActionListener(new enterLobby3());    // adds action listener to open lobby 3 chatDisplay when clicked

    }

    /**
     * 'Lobby 1/2/3' button takes the user into the lobby they clicked
     */
    class enterLobby1 implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //TODO: Logic for lobby joining - Connor
            ServerProtocol response = ClientGui.gui.client.joinLobby(1);
        }
    }

    class enterLobby2 implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ServerProtocol response = ClientGui.gui.client.joinLobby(2);
        }
    }

    class enterLobby3 implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ServerProtocol response = ClientGui.gui.client.joinLobby(3);
        }
    }
}
