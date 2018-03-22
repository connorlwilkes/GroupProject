package Client;


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
 *
 * @author Florence
 * @version 14/03/18
 */
public class ChatDisplay extends JFrame {

    public JTextArea chatBox;
    public Runnable chatThread;
    public JFrame displayFrame;
    private JTextField messageBox;
    private Client client;
    private boolean isRunning;
    private JButton btnSend;

    /**
     * ChatDisplay is a constructor that creates the panel
     * @param clientConstructor - client associated with this gui
     */
    public ChatDisplay(Client clientConstructor) {
        this.client = clientConstructor;
        displayFrame = new JFrame("Chat");                            
        JPanel southPanel = new JPanel();                            
        displayFrame.add(BorderLayout.SOUTH, southPanel);                   
        southPanel.setBackground(Color.DARK_GRAY);                        
        southPanel.setLayout(new GridBagLayout());                    

        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        displayFrame.setSize(470, 300);                                       
        
        messageBox = new JTextField(30);                                
        btnSend = new JButton("Send");                            
        chatBox = new JTextArea();                                    
        chatBox.setEditable(false);                                    
        displayFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);   

        chatBox.setLineWrap(true);

        GridBagConstraints left = new GridBagConstraints();            
        left.anchor = GridBagConstraints.WEST;                        
        GridBagConstraints right = new GridBagConstraints();            
        right.anchor = GridBagConstraints.EAST;                        
        right.weightx = 2.0;

        southPanel.add(messageBox, left);                            
        southPanel.add(btnSend, right);                            

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));                
        chatBox.setEditable(false);
        btnSend.addActionListener(e -> {
            String chatMessage = messageBox.getText();
            if (chatMessage.length() != 0) {
                if (chatMessage.equals(".clear")) {
                    chatBox.setText("");
                    messageBox.setText("");
                } else if (chatMessage.equals(".shot")) {
                    messageBox.setText("");
                    BufferedImage img = new BufferedImage(displayFrame.getWidth(), displayFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
                    displayFrame.paint(img.getGraphics());
                    File outputfile = new File("chatroom.png");
                    try {
                        ImageIO.write(img, "png", outputfile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (chatMessage.equals(".curecancer")) {
                		chatBox.setText("Well Done! You have cured cancer! \n");
                		messageBox.setText("");
                } else {
                    client.sendMessage(chatMessage);
                    messageBox.setText("");
                }
            }
        });
        southPanel.add(btnSend, right);
    }

    /**
     * setRunning method
     * @param running
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }


}
