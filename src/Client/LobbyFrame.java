package Client;

import Server.ServerProtocol;

import javax.swing.*;

/**
 * LobbyFrame is the class for the lobby page GUI where the user can choose which lobby to enter.
 * It extends JPanel and is added to a JFrame in ClientGui.
 *
 * @author Florence
 * @version 14/03/18
 */
public class LobbyFrame extends JPanel {

    private ClientGui gui;

    public LobbyFrame(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);
        this.setBounds(0, 0, 400, 500);

        JLabel chooseLobby = new JLabel("Choose a lobby");
        chooseLobby.setBounds(160, 120, 130, 16);
        add(chooseLobby);

        JButton btnLobby1 = new JButton("Lobby 1");
        btnLobby1.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "1");
            if (response.type.equals("true")) {
            		gui.remove(this);
                gui.setContentPane(gui.wait);
                gui.revalidate();
                gui.repaint();
                gui.setTitle("Waiting for players");
                gui.setTitle("Lobby 1");
                gui.chat.setRunning(true);
                // new Thread(gui.chat.chatThread).start();
                gui.chat.displayFrame.setVisible(true);
                new Thread(gui.client).start();
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                        response.message[0],
                        "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnLobby1.setBounds(160, 160, 100, 29);
        add(btnLobby1);

        JButton btnLobby2 = new JButton("Lobby 2");
        btnLobby2.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "2");
            if (response.type.equals("true")) {
                gui.setTitle("Lobby 2");
                gui.chat.displayFrame.setVisible(true);
                gui.chat.setRunning(true);
                new Thread(gui.chat.chatThread).start();
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                        response.message[0],
                        "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnLobby2.setBounds(160, 210, 100, 29);
        add(btnLobby2);

        JButton btnLobby3 = new JButton("Lobby 3");
        btnLobby3.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "3");
            if (response.type.equals("true")) {
                gui.setTitle("Lobby 3");
                gui.chat.setRunning(true);
                new Thread(gui.chat.chatThread).start();
                gui.chat.displayFrame.setVisible(true);
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                        response.message[0],
                        "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnLobby3.setBounds(160, 260, 100, 29);
        add(btnLobby3);
    
        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.addActionListener(e -> {
        	// insert method here
        });
        btnLogOut.setBounds(160, 340, 100, 29);
        add(btnLogOut);
    
    }
        		
}