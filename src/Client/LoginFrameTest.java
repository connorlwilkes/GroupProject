package Client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


@SuppressWarnings("serial")
public class LoginFrameTest extends JPanel {
    private JTextField txUsername;
    private JTextField txPassword;
    private String username;
    private String password;

    /**
     * Create the panel.
     */
    public LoginFrameTest() {
        setLayout(null);
        this.setBounds(0, 0, 450, 278);

        JLabel lblSignUp = new JLabel("Sign In");
        lblSignUp.setBounds(34, 25, 61, 16);
        add(lblSignUp);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(34, 73, 79, 16);
        add(lblUsername);

        txUsername = new JTextField();
        txUsername.setBounds(118, 68, 130, 26);
        add(txUsername);
        txUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(34, 114, 79, 16);
        add(lblPassword);

        txPassword = new JTextField();
        txPassword.setBounds(118, 109, 130, 26);
        add(txPassword);
        txPassword.setColumns(10);

        JButton btnNewButton = new JButton("Sign In");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username = txUsername.getText();
                password = txPassword.getText();
                ClientGui.gui.client.connect();
                if (username.length() < 1 || password.length() < 1) {
                    JOptionPane.showMessageDialog(ClientGui.gui, "Don't mess with me!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    ClientGui.gui.client.login(username, password);
                    JOptionPane.showMessageDialog(ClientGui.gui, "Logged in as: " + username);
//                    ClientGui.gui.setBounds(ClientGui.gui.getX(), ClientGui.gui.getY(), 300, 200);
//                    ClientGui.gui.setContentPane(ClientGui.gui.lobby);
//                    ClientGui.gui.setTitle("Lobby");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(34, 172, 91, 29);
        add(btnNewButton);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSignUp.setBounds(157, 172, 91, 29);
        add(btnSignUp);
    }

}

