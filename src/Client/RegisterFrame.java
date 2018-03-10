package Client;

import Server.ServerProtocol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JPanel {

    private JTextField enterUser;
    private JTextField enterPass;
    private JTextField reEnterPass;

    public RegisterFrame() {
        setLayout(null);
        this.setBounds(0, 0, 450, 278);

        JLabel lblUser = new JLabel("Username: ");
        lblUser.setBounds(120, 113, 79, 16);
        add(lblUser);

        enterUser = new JTextField();
        enterUser.setBounds(200, 108, 130, 26);
        enterUser.setColumns(12);
        add(enterUser);

        JLabel lblPass = new JLabel("Password: ");
        lblPass.setBounds(120, 154, 79, 16);
        add(lblPass);

        enterPass = new JTextField();
        enterPass.setBounds(200, 149, 130, 26);
        enterPass.setColumns(12);
        add(enterPass);

        JLabel lblReEnterPass = new JLabel("Re-enter Password: ");
        lblReEnterPass.setBounds(80, 195, 130, 16);
        add(lblReEnterPass);

        reEnterPass = new JTextField();
        reEnterPass.setBounds(200, 190, 130, 26);
        reEnterPass.setColumns(12);
        add(reEnterPass);

        JButton btnSignIn = new JButton("Sign Up");
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = enterUser.getText();
                String password = enterPass.getText();
                if ("".equals(username) || "".equals(password)) {
                    JOptionPane.showMessageDialog(ClientGui.gui,
                            "Username or password field cannot be empty",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!(password.equals(reEnterPass.getText()))) {
                    JOptionPane.showMessageDialog(ClientGui.gui,
                            "Passwords do not match",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    ClientGui.gui.client.connect();
                    ServerProtocol response = ClientGui.gui.client.createAccount(username, password);
                    if (response.type.startsWith("true")) {
                        JOptionPane.showMessageDialog(ClientGui.gui,
                                "Created account with username: " + username,
                                "Account Created", JOptionPane.INFORMATION_MESSAGE);
                        ClientGui.gui.setContentPane(ClientGui.gui.login);
                        ClientGui.gui.setTitle("Log in");
                    } else if (response.type.startsWith("false")) {
                        JOptionPane.showMessageDialog(ClientGui.gui,
                                response.message[0],
                                "Account Creation Failure", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        btnSignIn.setBounds(215, 240, 91, 29);
        add(btnSignIn);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientGui.gui.setContentPane(
                        ClientGui.gui.login);
                ClientGui.gui.setTitle("Log In");
            }
        });
        back.setBounds(125, 240, 91, 29);
        add(back);


    }

}


    