import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private JFrame registerFrame;
    private JTextField enterUsername;
    private JTextField enterPassword;
    private JTextField reEnterPassword;

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
     * | |                                 ||
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
     * 'Register' button that stores the new account details in the database and then takes
     * the user back to the log-in page
     */
    class registerPageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // check if username in use already
            // check if 2 passwords match
            // if above hold then store new account details in the database
            registerFrame.setVisible(false);
            new LogInFrame();
        }
    }
}
