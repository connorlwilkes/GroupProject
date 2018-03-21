package Client;

import Server.ServerProtocol;
import javax.swing.*;

/**
 * LogInFrame is the class for the log in page GUI where the user enters their account details to log in.
 * It extends JPanel and is added to a JFrame in ClientGui
 *
 * @author Florence
 * @version 14/03/18
 */
public class LogInFrame extends JPanel {

    private JTextField enterUsername;
    private JPasswordField enterPassword;
    private ClientGui gui;

    public LogInFrame(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);
        this.setBounds(0, 0, 400, 500);

        JLabel lblUser = new JLabel("Username: ");
        lblUser.setBounds(90, 118, 79, 16);
        add(lblUser);

        enterUsername = new JTextField();
        enterUsername.setBounds(170, 113, 130, 26);
        enterUsername.setColumns(12);
        add(enterUsername);

        JLabel lblPass = new JLabel("Password: ");
        lblPass.setBounds(90, 159, 79, 16);
        add(lblPass);

        enterPassword = new JPasswordField();
        enterPassword.setBounds(170, 154, 130, 26);
        enterPassword.setColumns(12);
        add(enterPassword);

        JButton btnSignIn = new JButton("Sign In");        
        btnSignIn.addActionListener(e -> {
            String username = enterUsername.getText();
            String password = enterPassword.getText();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(gui,
                        "Username or password field cannot be empty",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                gui.client.connect();
                ServerProtocol response = gui.client.serverRequest("login", username, password);
                if (response.type.startsWith("true")) {
                    JOptionPane.showMessageDialog(gui,
                            "Signed in: " + username,
                            "Sign-in success", JOptionPane.INFORMATION_MESSAGE);
                    gui.remove(this);
                    gui.setContentPane(gui.lobby);
                    gui.revalidate();
                    gui.repaint();
                    gui.setTitle("Lobby Room");
                } else if (response.type.startsWith("false")) {
                    JOptionPane.showMessageDialog(gui,
                            response.message[0],
                            "Sign-in failure", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        btnSignIn.setBounds(175, 195, 91, 29);
        add(btnSignIn);

        JLabel lblNoAccount = new JLabel("Don't have an account?");
        lblNoAccount.setBounds(150, 240, 180, 16);
        add(lblNoAccount);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(e -> {
        		enterUsername.setText("");
        		enterPassword.setText("");
            gui.setContentPane(
                    gui.register);
            gui.setTitle("Sign up");
        });
        btnSignUp.setBounds(175, 260, 91, 29);
        add(btnSignUp);
       
    }
}
