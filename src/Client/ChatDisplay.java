package Client;


import Server.Message;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        this.setBounds(0, 0, 470, 300);
    		
        newFrame = new JFrame();
    		newFrame.setVisible(true);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        newFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);
    		
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        newFrame.add(BorderLayout.NORTH, northPanel);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        southPanel.setBackground(Color.BLACK);
        newFrame.add(BorderLayout.SOUTH, southPanel);                 
                            
        GridBagConstraints left = new GridBagConstraints();            
        left.anchor = GridBagConstraints.WEST;                        
        GridBagConstraints right = new GridBagConstraints();            
        right.anchor = GridBagConstraints.EAST;                        
  
        // chat area
        chatBox = new JTextArea(10, 20);
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);
        chatBox.setEditable(false); 
        northPanel.add(chatBox);

        // message box
        messageBox = new JTextField();
        messageBox.setEditable(true);
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
