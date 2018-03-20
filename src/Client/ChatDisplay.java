package Client;


import Server.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@SuppressWarnings("Duplicates")
public class ChatDisplay extends JFrame {

    public JTextArea chatBox;
    private JTextField messageBox;
    private Client client;
    public Runnable chatThread;
    private boolean isRunning;
    public JFrame newFrame;
    private JButton btnSend;


    public ChatDisplay(Client clientConstructor) {
        this.client = clientConstructor;
        setLayout(null);
        this.setBounds(0, 0, 630, 460);

        newFrame = new JFrame();
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        newFrame.add(BorderLayout.NORTH, northPanel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        newFrame.add(BorderLayout.SOUTH, southPanel);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.WEST;
        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.EAST;

        // chat area
        chatBox = new JTextArea(10, 20);
        chatBox.setEditable(false);
        chatBox.setBounds(20, 20, 590, 370);
        northPanel.add(chatBox);

        // message box
        messageBox = new JTextField();
        messageBox.setEditable(true);
        messageBox.setBounds(20, 400, 500, 30);
        messageBox.setColumns(80);
        southPanel.add(messageBox, left);

        // send button
        btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            String chatMessage = messageBox.getText();
            if (chatMessage.length() != 0) {
                client.sendMessage(chatMessage);
                messageBox.setText("");
//                messageBox = null;
            }
        });
        btnSend.setBounds(530, 400, 90, 30);
        southPanel.add(btnSend, right);

        chatThread = () -> {
            while (isRunning) {
                try {
                    Object o = client.inputStream.readObject();
                    if (o instanceof Message) {
                        Message message = (Message) o;
                        chatBox.append(message.toString() + "\n");
//                        messageBox.setText("");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        newFrame.pack();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

}
