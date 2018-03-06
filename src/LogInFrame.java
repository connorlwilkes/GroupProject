import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInFrame {

    private JFrame preFrame;
    private JTextField enterUsername;
    private JTextField enterPassword;
    private String username;

    /**
     * logInDisplay is the username and password entry display, the first dispay that appears when the programme runs.
     * <p>
     * JFrame_______________________________
     * | ___________________________________|       preLeft         preRight
     * | |								   ||
     * | |								   ||
     * | |								   ||
     * | |		  JPanel = prePanel1	   || ===>  Enter username:  _____________________________________________________
     * | |								   ||       Enter password:  _____________________________________________________
     * | |								   ||
     * | |								   ||
     * | |                                 ||
     * | |_________________________________||
     * | |								   ||
     * | |								   ||                                                ____________  ________________
     * | |		  JPanel = prePanel2	   || ===>  Don't have an account? Click 'Register'  | Register |  | Find a lobby |
     * | |								   ||                                                |__________|  |______________|
     * | |								   ||
     * | |								   ||
     * | ___________________________________|
     * ______________________________________
     */
    public void logInFrame() {
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
     * 'Enter Lobby' button saves the users username as a variable and takes the user to the lobby page
     */
    class enterLobbyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = enterUsername.getText();
            if (username.length() < 1) {
                System.out.println("Don't mess with me.");
            } else {
                preFrame.setVisible(false);
                new LobbyFrame();
            }
        }
    }

    /**
     * 'Register' button that takes users from the log-in page to the registration page
     */
    class registerNewUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            preFrame.setVisible(false);
            new RegisterFrame();
        }
    }
}
