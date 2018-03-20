package Client;

import Server.ServerProtocol;

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
    public LobbyFrame(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);

        JButton btnLobby1 = new JButton("Lobby 1");
        btnLobby1.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "1");
            if (response.type.equals("true")) {
                gui.setTitle("Lobby 1");
                gui.setContentPane(gui.chat);
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                           response.message[0],
                            "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
                    gui.setContentPane(gui.lobby);
                    gui.setTitle("Lobby Room");
            }
        });
        btnLobby1.setBounds(175, 190, 100, 29);
        add(btnLobby1);

        JButton btnLobby2 = new JButton("Lobby 2");
        btnLobby2.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "2");
            if (response.type.equals("true")) {
                gui.setTitle("Lobby 2");
                gui.setContentPane(gui.chat);
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                        response.message[0],
                        "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
                gui.setContentPane(gui.lobby);
                gui.setTitle("Lobby Room");
            }
            gui.setContentPane(gui.chat);
        });
        btnLobby2.setBounds(175, 230, 100, 29);
        add(btnLobby2);

        JButton btnLobby3 = new JButton("Lobby 3");
        btnLobby3.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "3");
            if (response.type.equals("true")) {
                gui.setTitle("Lobby 3");
                gui.setContentPane(gui.chat);
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                        response.message[0],
                        "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
                gui.setContentPane(gui.lobby);
                gui.setTitle("Lobby Room");
            }
            gui.setContentPane(gui.chat);
        });
        btnLobby3.setBounds(175, 270, 100, 29);
        add(btnLobby3);
    }

}