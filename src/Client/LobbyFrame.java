package Client;

import Server.ServerProtocol;

import javax.swing.*;

public class LobbyFrame extends JPanel {

    private ClientGui gui;

    public LobbyFrame(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);

        JButton btnLobby1 = new JButton("Lobby 1");
        btnLobby1.addActionListener(e -> {
            ServerProtocol response = gui.client.serverRequest("join-lobby", "1");
            if (response.type.equals("true")) {
                gui.setTitle("Lobby 1");
                gui.chat.setRunning(true);
                new Thread(gui.chat.chatThread).start();
                gui.chat.displayFrame.setVisible(true);
            } else if (response.type.equals("false")) {
                JOptionPane.showMessageDialog(gui,
                        response.message[0],
                        "Lobby join failure", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnLobby1.setBounds(175, 200, 100, 29);
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
        btnLobby2.setBounds(175, 230, 100, 29);
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
        btnLobby3.setBounds(175, 270, 100, 29);
        add(btnLobby3);
    }

}