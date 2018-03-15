package Client;



import javax.swing.*;

import Server.ServerProtocol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("Duplicates")
public class ChatDisplay extends JPanel {

	private JTextField messageBox;
	private JTextArea chatBox;
	
	public ChatDisplay() {
	
	setLayout(null);
    	
		
    chatBox = new JTextArea(10, 20);
    chatBox.setEditable(false);
    chatBox.setBounds(20, 20, 590, 370);
    add(chatBox);
    
    messageBox = new JTextField();
	messageBox.setEditable(true);
	messageBox.setBounds(20, 400, 500, 30);
	messageBox.setColumns(80);
	add(messageBox);
	
	JButton btnSend = new JButton("Send");
    btnSend.addActionListener(e -> {
    		String chatMessage = messageBox.getText();
    		if (chatMessage.length() != 0) {
    			ClientGui.gui.client.sendMessage(chatMessage);
    		}
     
    });
    btnSend.setBounds(530, 400, 90, 30);
    add(btnSend);
	
		
}
	
    /**
     * chatDisplay is the game chat display that appears once the user has chosen a lobby in lobbyDisplay.
     * <p>
     * JFrame_______________________________
     * | ___________________________________|       ______________________________________________________________
     * | |								   ||
     * | |								   ||        <user1> some message......
     * | |								   ||        <user2> blah blah
     * | |	                          	   || ===>   <user3> blah blah blah
     * | |								   ||
     * | |								   ||
     * | |	      JTextArea = chatbox      ||
     * | |                                 ||
     * | |                                 ||
     * | |								   ||
     * | |								   ||
     * | |_________________________________||        ______________________________________________________________
     * | |								   ||        ____________________________________________________________
     * | |	      JPanel = southPanel      || ===>  |                                         |       SEND       |
     * | |								   ||		|     [type message to send here]         |__________________|
     * | ___________________________________|       |_________________________________________|
     * ______________________________________
     */
/*    public void chatDisplay() {
        newFrame.setVisible(true);
        displayFrame = new JFrame("CHAT");                            // creates a new JFrame
        JPanel southPanel = new JPanel();                            // creates a new JPanel (southPanel)
        newFrame.add(BorderLayout.SOUTH, southPanel);                    // adds southPanel to the bottom of JFrame
        southPanel.setBackground(Color.BLACK);                        // sets background of southPanel to black
        southPanel.setLayout(new GridBagLayout());                    // sets the layout of southPanel as GridBagLayout

        messageBox = new JTextField(30);                                // creates a new JTextField with character limit of 30
        sendMessage = new JButton("Send");                            // creates new JButton 'Send' to send messages
        chatBox = new JTextArea();                                    // creates a JTextArea 'chatBox' where messages will appear
        chatBox.setEditable(false);                                    // makes chatBox uneditable by the user
        newFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);    // adds a scroll function to the chatBox through JScrollPane

        chatBox.setLineWrap(true);

        GridBagConstraints left = new GridBagConstraints();            // creates constraints for 'left' of the southPanel
        left.anchor = GridBagConstraints.WEST;                        // anchors whatever object applies 'left' constraint to the left of the panel
        GridBagConstraints right = new GridBagConstraints();            // creates constraints for 'right' of the southPanel
        right.anchor = GridBagConstraints.EAST;                        // anchors whatever object applies 'right' constraint to the left of the panel
        right.weightx = 2.0;

        southPanel.add(messageBox, left);                            // adds the 'messageBox' JTextField to the southPanel with the 'left' constraint
        southPanel.add(sendMessage, right);                            // adds the 'sendMessage' JButton to the southPanel with the 'right' constraint

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));                // sets the font for messages displayed in the chatBox to 'Serif', size 15
        sendMessage.addActionListener(new sendMessageButtonListener());    // adds an actionListener to the 'sendMessage' JButton, calling method sendMessageButtonListener
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes the JFrame when the display is exitted
        newFrame.setSize(470, 300);                                        // sets the size of the chat box to 470px x 300px (x, y)
    }

    
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                return;
            } else {
                Message messageToSend = new Message(messageBox.getText(), ClientGui.gui.user);
            }
        }
    }
 */
}
