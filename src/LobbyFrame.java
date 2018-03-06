import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyFrame extends JFrame {

    private JFrame lobbyFrame;

    /**
     * lobbyDisplay is the display where the lobby to play in is chosen. It is the display that appears
     * once the user has logged-in successfully.
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
    public void lobbyFrame() {
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
     * 'Lobby 1/2/3' button takes the user into the lobby they clicked
     */
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //	username = enterUsername.getText();
            lobbyFrame.setVisible(false);
            new ChatDisplay();

        }
    }
}
