package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

    private GameGUI chatRoomGUI;
    private JFrame newFrame = new JFrame("NO EYE DEAR");
    private JButton sendMessage;            //send message button
    private JTextField messageBox;        //box to type message
    private JTextArea chatBox;
    private JTextField enterUsername;    //box to type username
    private JTextField enterPassword;    //box to type password
    private JTextField reEnterPassword;    //box to re-type password
    private JFrame preFrame;                //the first frame that is for user/pass entry
    private JFrame registerFrame;
    private JFrame lobbyFrame;            //the second frame that is for lobby choice
    private JFrame displayFrame;            //the third frame that is where the game is played

    public static void main(String[] args) {
        //set cross-platform Java Look & Feel ("Metal")
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameGUI chatRoomGUI = new GameGUI();
        chatRoomGUI.logInDisplay();        // open the logInDisplay (the username and password display)
    }

    /**
     * logInDisplay is the username and password entry display, the first dispay that appears when the programme runs.
     * <p>
     * JFrame_______________________________
     * | ___________________________________|       preLeft         preRight
     * | |								   ||
     * | |								   ||
     * | |								   ||
     * | |		  JPanel = prePanel1		   || ===>  Enter username:  _____________________________________________________
     * | |								   ||       Enter password:  _____________________________________________________
     * | |								   ||
     * | |								   ||
     * | |                                 |	|
     * | |_________________________________||
     * | |								   ||
     * | |								   ||                                                ____________  ________________
     * | |		  JPanel = prePanel2		   || ===>  Don't have an account? Click 'Register'  | Register |  | Find a lobby |
     * | |								   ||                                                |__________|  |______________|
     * | |								   ||
     * | |								   ||
     * | ___________________________________|
     * ______________________________________
     */
    public void logInDisplay() {
        //newFrame.setVisible(false);		
        preFrame = new JFrame("LOG-IN");                                // creates a new frame

        JLabel enterUsernameLabel = new JLabel("Enter username:");    // the 'Enter Username' label
        JLabel enterPasswordLabel = new JLabel("Enter password:");    //the 'Enter password' label
        enterUsername = new JTextField();                            // text box to enter username
        enterPassword = new JTextField();                            // text box to enter password

        JLabel dontHaveAccount = new JLabel("Don't have an account? Click 'Register'");
        JButton enterLobby = new JButton("Find a lobby");                //'Find a lobby' button - takes user to the lobby
        JButton registerNewUser = new JButton("Register");            // 'Register' button - takes user to a registration page
        JPanel prePanel1 = new JPanel(new GridBagLayout());            // creates a panel and uses GridBagLayout, contains preLeft and preRight
        JPanel prePanel2 = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();        // creates the preRight of prePanel1 used for the text fields
        preRight.anchor = GridBagConstraints.EAST;                    // anchors the preRight position to the right (used on enterUsername and enterPassword panels)
        preRight.weightx = 2;                                        // weighting for preRight
        preRight.fill = GridBagConstraints.HORIZONTAL;                // makes the text fields span to fit the width of the frame
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        GridBagConstraints preLeft = new GridBagConstraints();        // creates the preLeft of prePanel1 used for the labels
        preLeft.anchor = GridBagConstraints.WEST;                        // anchors the preLeft position to the left (used on enterUsernameLabel and enterPasswordLabel)

        prePanel1.add(enterUsernameLabel, preLeft);                    // adds the enterUsernameLabel to prePanel1
        prePanel1.add(enterUsername, preRight);                        // adds the text field for username to prePanel1
        prePanel1.add(enterPasswordLabel, preLeft);                    // adds the enterPasswordLabel to prePanel1
        prePanel1.add(enterPassword, preRight);                        // adds the text field for password to prePanel1

        prePanel2.add(dontHaveAccount);                                // adds the dontHaveAccountLabel to prePanel2
        prePanel2.add(registerNewUser);                                // adds the 'Register' button to prePanel2
        prePanel2.add(enterLobby);                                    // adds the 'Find a lobby' button to prePanel2

        preFrame.add(BorderLayout.CENTER, prePanel1);                    // positions prePanel1 in the center of the frame
        preFrame.add(BorderLayout.SOUTH, prePanel2);                    // positions prePanel2 at the bottom of the frame

        preFrame.setVisible(true);                                    // makes logInDisplay() visible
        preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // closes the JFrame when the display is exitted
        preFrame.setSize(500, 300);                                    // sets the size of the frame to 500px x 300px (x, y)

        enterLobby.addActionListener(new enterLobbyButtonListener());    // adds action listener on the enterLobby button
        registerNewUser.addActionListener(new registerNewUserButtonListener());    // adds action listener on the register button
    }

    /**
     * registerDisplay is the registration display, this allows the user to create a new account
     * JFrame_______________________________
     * | ___________________________________|       registerLeft         registerRight
     * | |								   ||
     * | |								   ||
     * | |								   ||       Enter username:  _____________________________________________________
     * | |	   JPanel = registerPanel1	   || ===>  Enter password:  _____________________________________________________
     * | |								   ||       Re-enter password:  __________________________________________________
     * | |								   ||
     * | |								   ||
     * | |                                 |	|
     * | |_________________________________||
     * | |								   ||
     * | |								   ||             ____________
     * | |     JPanel = registerPanel2	   || ===>        | Register |
     * | |								   ||             |__________|
     * | |								   ||
     * | |								   ||
     * | ___________________________________|
     * ______________________________________
     */
    public void registerDisplay() {

        registerFrame = new JFrame("REGISTER");                            // creates a new frame

        JLabel enterUsernameLabel = new JLabel("Enter username:");        // the 'Enter Username' label
        JLabel enterPasswordLabel = new JLabel("Enter password:");        //the 'Enter password' label
        JLabel reEnterPasswordLabel = new JLabel("Re-enter password:");    //the 'Re-enter password' label
        enterUsername = new JTextField();                                // text box to enter username
        enterPassword = new JTextField();                                // text box to enter password
        reEnterPassword = new JTextField();                                // text box to re-enter password

        JButton register = new JButton("Register");                        // 'Register' button - takes user to a registration page
        JPanel registerPanel1 = new JPanel(new GridBagLayout());            // creates a panel and uses GridBagLayout, contains preLeft and preRight
        JPanel registerPanel2 = new JPanel(new GridBagLayout());

        GridBagConstraints registerRight = new GridBagConstraints();        // creates the registerRight of registerPanel1 used for the text fields
        registerRight.anchor = GridBagConstraints.EAST;                    // anchors the registerRight position to the right (used on enterUsername and enterPassword panels)
        registerRight.weightx = 2;                                        // weighting for registerRight
        registerRight.fill = GridBagConstraints.HORIZONTAL;                // makes the text fields span to fit the width of the frame
        registerRight.gridwidth = GridBagConstraints.REMAINDER;

        GridBagConstraints registerLeft = new GridBagConstraints();        // creates the regsiterLeft of registerPanel1 used for the labels
        registerLeft.anchor = GridBagConstraints.WEST;                    // anchors the regsiterLeft position to the left (used on enterUsernameLabel and enterPasswordLabel)

        registerPanel1.add(enterUsernameLabel, registerLeft);                // adds the enterUsernameLabel to registerPanel1
        registerPanel1.add(enterUsername, registerRight);                    // adds the text field for username to registerprePanel1
        registerPanel1.add(enterPasswordLabel, registerLeft);                // adds the enterPasswordLabel to registerprePanel1
        registerPanel1.add(enterPassword, registerRight);                    // adds the text field for password to registerprePanel1
        registerPanel1.add(reEnterPasswordLabel, registerLeft);            // adds the reEnterPassword label to registerprePanel1
        registerPanel1.add(reEnterPassword, registerRight);

        registerPanel2.add(register);                                    // adds the 'Register' button to registerPanel2

        registerFrame.add(BorderLayout.CENTER, registerPanel1);
        registerFrame.add(BorderLayout.SOUTH, registerPanel2);

        registerFrame.setVisible(true);                                    // makes logInDisplay() visible
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes the JFrame when the display is exitted
        registerFrame.setSize(500, 300);                                    // sets the size of the frame to 500px x 300px (x, y)

        register.addActionListener(new registerPageButtonListener());        // adds action listener on the Register button
    }

    /**
     * lobbyDisplay is the display where the lobby to play in is chosen. It is the display that appears
     * once the user has logged-in succesfully.
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
    public void lobbyDisplay() {

        lobbyFrame = new JFrame("CHOOSE A LOBBY");                    // create new frame
        JPanel lobbyPanel = new JPanel(new GridBagLayout());            // create a panel with GridBagLayout
        GridBagConstraints c = new GridBagConstraints();                // create 'c' as a GribBagConstraint

        JButton button1 = new JButton("Lobby 1");                    // create 'Lobby 1' button
        c.fill = GridBagConstraints.HORIZONTAL;                        // makes the button span to fit the width of the frame
        c.weightx = 0;                                                // gives the button 0 weight so it doesn't fill the frame width
        c.gridx = 3;                                                    // set x position of the button so all buttons in line
        c.gridy = 0;                                                    // set y position of the button so buttons go down vertically
        lobbyPanel.add(button1, c);                                    // adds button1 to the panel

        JButton button2 = new JButton("Lobby 2");                    // create 'Lobby 2' button
        c.fill = GridBagConstraints.HORIZONTAL;                        // makes the button span to fit the width of the frame
        c.weightx = 0;                                                // gives the button 0 weight so it doesn't fill the frame width
        c.gridx = 3;                                                    // set x position of the button so all buttons in line
        c.gridy = 1;                                                    // set y position of the button so buttons go down vertically
        lobbyPanel.add(button2, c);                                    // adds button2 to the panel

        JButton button3 = new JButton("Lobby 3");                    // create 'Lobby 1' button
        c.fill = GridBagConstraints.HORIZONTAL;                        // makes the button span to fit the width of the frame
        c.weightx = 0;                                                // gives the button 0 weight so it doesn't fill the frame width
        c.gridx = 3;                                                    // set x position of the button so all buttons in line
        c.gridy = 2;                                                    // set y position of the button so buttons go down vertically
        lobbyPanel.add(button3, c);                                    // adds button3 to the panel

        lobbyFrame.add(BorderLayout.CENTER, lobbyPanel);                // adds the panel to the centre of the frame
        lobbyFrame.setVisible(true);                                    // makes the frame visible
        lobbyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // closes the JFrame when the display is exitted
        lobbyFrame.setSize(300, 200);                                // sets the size of the frame to 300px x 200px (x, y)

        button1.addActionListener(new enterServerButtonListener());    // adds action listener to open lobby 1 chatDisplay when clicked
        button2.addActionListener(new enterServerButtonListener());    // adds action listener to open lobby 2 chatDisplay when clicked
        button3.addActionListener(new enterServerButtonListener());    // adds action listener to open lobby 3 chatDisplay when clicked

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
     * | |	      JTextArea = chatbox       ||
     * | |                                 |	|
     * | |                                 ||
     * | |								   ||
     * | |								   ||
     * | |_________________________________||        ______________________________________________________________
     * | |								   ||        ____________________________________________________________
     * | |	      JPanel = southPanel       || ===>  |                                         |       SEND       |
     * | |								   ||		|     [type message to send here]         |__________________|
     * | ___________________________________|       |_________________________________________|
     * ______________________________________
     */
    public void chatDisplay() {
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

    // BUTTON LISTENERS ---------------------------------------------------------------------------------------------------

    private String username;

    /**
     * 'Register' button that takes users from the log-in page to the registration page
     */
    class registerNewUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            preFrame.setVisible(false);
            registerDisplay();
        }
    }

    /**
     * 'Register' button that stores the new account details in the database and then takes
     * the user back to the log-in page
     */
    class registerPageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // check if username in use already
            // check if 2 passwords match
            // if above hold then store new account details in the database
            registerFrame.setVisible(false);
            logInDisplay();
        }
    }

    /**
     * 'Send' button checks if the message exists (1 or more characters), cehcks if the text has a functionality,
     * and then sends the message with the users username preceding it
     */
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing 
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else if (messageBox.getText().equals(".leave")) {
                displayFrame.setVisible(false);    // close the current game
                lobbyDisplay();    // open the lobby
            } else if (messageBox.getText().equals(".score")) {
                // show scoreboard
            } else {
                chatBox.append("<" + username + ">:  " + messageBox.getText() + "\n");
                messageBox.setText("");
            }
        }
    }

    /**
     * 'Enter Lobby' button saves the users username as a variable and takes the user to the lobby page
     */
    class enterLobbyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = enterUsername.getText();
            if (username.length() < 1) {
                System.out.println("Don't mess with me.");
            } else {
                preFrame.setVisible(false);
                lobbyDisplay();
            }
        }
    }

    /**
     * 'Lobby 1/2/3' button takes the user into the lobby they clicked
     */
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //	username = enterUsername.getText();
            lobbyFrame.setVisible(false);
            chatDisplay();

        }
    }

}