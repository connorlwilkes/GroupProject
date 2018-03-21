package Client;


import Server.Message;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ChatDisplay is the class for the chat room GUI where the user can write messages to other users. 
 * It contains aditional functions to clear the chat and to take a screen shot of the chat.
 * It extends JFrame so it opens as a new frame when a lobby is chosen in LobbyFrame.
 * @author Florence
 * @version 14/03/18
 *
 */
@SuppressWarnings("Duplicates")
public class ChatDisplay extends JFrame {

    public JTextArea chatBox;
    private JTextField messageBox;
    private Client client;
    public Runnable chatThread;
    private boolean isRunning;
    public JFrame displayFrame;
    private JButton btnSend;

    /**
     * Constructor for ChatDisplay
     * @param clientConstructor
     */
    public ChatDisplay(Client clientConstructor) {
        this.client = clientConstructor;
        displayFrame = new JFrame("Chat");                            // creates a new JFrame
        JPanel southPanel = new JPanel();                            // creates a new JPanel (southPanel)
        displayFrame.add(BorderLayout.SOUTH, southPanel);                    // adds southPanel to the bottom of JFrame
        southPanel.setBackground(Color.DARK_GRAY);                        // sets background of southPanel to dark grey
        southPanel.setLayout(new GridBagLayout());                    // sets the layout of southPanel as GridBagLayout

        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes the JFrame when the display is exitted
        displayFrame.setSize(470, 300);                                        // sets the size of the chat box to 470px x 300px (x, y)

        messageBox = new JTextField(30);                                // creates a new JTextField with character limit of 30
        btnSend = new JButton("Send");                            // creates new JButton 'Send' to send messages
        chatBox = new JTextArea();                                    // creates a JTextArea 'chatBox' where messages will appear
        //chatBox.setEditable(false);                                    // makes chatBox uneditable by the user
        displayFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);    // adds a scroll function to the chatBox through JScrollPane

        chatBox.setLineWrap(true);

        GridBagConstraints left = new GridBagConstraints();            // creates constraints for 'left' of the southPanel
        left.anchor = GridBagConstraints.WEST;                        // anchors whatever object applies 'left' constraint to the left of the panel
        GridBagConstraints right = new GridBagConstraints();            // creates constraints for 'right' of the southPanel
        right.anchor = GridBagConstraints.EAST;                        // anchors whatever object applies 'right' constraint to the left of the panel
        right.weightx = 2.0;

        southPanel.add(messageBox, left);                            // adds the 'messageBox' JTextField to the southPanel with the 'left' constraint
        southPanel.add(btnSend, right);                            // adds the 'sendMessage' JButton to the southPanel with the 'right' constraint

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));                // sets the font for messages displayed in the chatBox to 'Serif', size 15
        chatBox.setEditable(false);
        btnSend.addActionListener(e -> {
            String chatMessage = messageBox.getText();
            if (chatMessage.length() != 0) {
                if (chatMessage.equals(".clear")) {
                    chatBox.setText("");
                    messageBox.setText("");
                }
                if (chatMessage.equals(".shot")) {
                    messageBox.setText("");
                    BufferedImage img = new BufferedImage(displayFrame.getWidth(), displayFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
                    displayFrame.paint(img.getGraphics());
                    File outputfile = new File("chatroom.png");
                    try {
                        ImageIO.write(img, "png", outputfile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    client.sendMessage(chatMessage);
                    messageBox.setText("");
                }
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
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * setRunning method
     * @param running
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }


}
