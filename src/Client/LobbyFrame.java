package Client;

import javax.swing.*;

public class LobbyFrame extends JPanel {

    private ClientGui gui;

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
    public LobbyFrame(ClientGui gui) {
        this.gui = gui;
        setLayout(null);

        JButton btnLobby1 = new JButton("Lobby 1");
        btnLobby1.addActionListener(e -> {
            gui.client.serverRequest("join-lobby", "1");
            gui.setTitle("Lobby 1");
            gui.setContentPane(gui.chat);
        });
        btnLobby1.setBounds(175, 190, 100, 29);
        add(btnLobby1);

        JButton btnLobby2 = new JButton("Lobby 2");
        btnLobby2.addActionListener(e -> {
            gui.client.serverRequest("join-lobby", "1");
            gui.setContentPane(gui.chat);
        });
        btnLobby2.setBounds(175, 230, 100, 29);
        add(btnLobby2);

        JButton btnLobby3 = new JButton("Lobby 3");
        btnLobby3.addActionListener(e -> {
            gui.client.serverRequest("join-lobby", "1");
            gui.setContentPane(gui.chat);
        });
        btnLobby3.setBounds(175, 270, 100, 29);
        add(btnLobby3);
    }

}