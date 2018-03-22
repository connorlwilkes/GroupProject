package Client;

import Server.ServerProtocol;

import javax.swing.*;

/**
 * RegisterFrame is the class for the register page GUI where the user can create a new account.
 * It extends JPanel and is added to a JFrame in ClientGui.
 *
 * @author Florence
 * @version 14/03/18
 */
public class RegisterFrame extends JPanel {

    private JTextField enterUser;
    private JPasswordField enterPass;
    private JPasswordField reEnterPass;
    private ClientGui gui;
    
    /**
     * RegisterFrame is a constructor that creates the panel.
     * @param guiConstructor
     */
    public RegisterFrame(ClientGui guiConstructor) {
        this.gui = guiConstructor;
        setLayout(null);
        this.setBounds(0, 0, 400, 500);

        JLabel lblUser = new JLabel("Username: ");
        lblUser.setBounds(100, 113, 100, 16);
        add(lblUser);

        enterUser = new JTextField();
        enterUser.setBounds(200, 108, 130, 26);
        enterUser.setColumns(12);
        add(enterUser);

        JLabel lblPass = new JLabel("Password: ");
        lblPass.setBounds(100, 154, 100, 16);
        add(lblPass);

        enterPass = new JPasswordField();
        enterPass.setBounds(200, 149, 130, 26);
        enterPass.setColumns(12);
        add(enterPass);

        JLabel lblReEnterPass = new JLabel("Re-enter Password: ");
        lblReEnterPass.setBounds(60, 195, 150, 16);
        add(lblReEnterPass);

        reEnterPass = new JPasswordField();
        reEnterPass.setBounds(200, 190, 130, 26);
        reEnterPass.setColumns(12);
        add(reEnterPass);

        JButton btnSignIn = new JButton("Sign Up");
        btnSignIn.addActionListener(e -> {
            String username = enterUser.getText();
            String password = enterPass.getText();
            if ("".equals(username) || "".equals(password)) {
                JOptionPane.showMessageDialog(gui,
                        "Username or password field cannot be empty",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!(password.equals(reEnterPass.getText()))) {
                JOptionPane.showMessageDialog(gui,
                        "Passwords do not match",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                gui.client.connect();
                ServerProtocol response = gui.client.serverRequest("create-account", username, password);
                if (response.type.startsWith("true")) {
                    JOptionPane.showMessageDialog(gui,
                            "Created account with username: " + username,
                            "Account Created", JOptionPane.INFORMATION_MESSAGE);
                    gui.setContentPane(gui.login);
                    gui.setTitle("Log in");
                } else if (response.type.startsWith("false")) {
                    JOptionPane.showMessageDialog(gui,
                            response.message[0],
                            "Account Creation Failure", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnSignIn.setBounds(215, 240, 91, 29);
        add(btnSignIn);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
        		enterUser.setText("");
        		enterPass.setText("");
        		reEnterPass.setText("");
            gui.setContentPane(
                    gui.login);
            gui.setTitle("Log In");
        });
        back.setBounds(125, 240, 91, 29);
        add(back);


    }

}


    