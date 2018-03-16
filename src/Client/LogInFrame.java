package Client;

import Server.ServerProtocol;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LogInFrame extends JPanel {

    private JTextField enterUsername;
    private JTextField enterPassword;
    private ClientGui gui;

    public LogInFrame() {
        setLayout(null);
        this.setBounds(0, 0, 450, 278);

        JLabel lblUser = new JLabel("Username: ");
        lblUser.setBounds(110, 113, 79, 16);
        add(lblUser);

        enterUsername = new JTextField();
        enterUsername.setBounds(190, 108, 130, 26);
        enterUsername.setColumns(12);
        add(enterUsername);

        JLabel lblPass = new JLabel("Password: ");
        lblPass.setBounds(110, 154, 79, 16);
        add(lblPass);

        enterPassword = new JTextField();
        enterPassword.setBounds(190, 149, 130, 26);
        enterPassword.setColumns(12);
        add(enterPassword);

//        JButton btnSignIn = new JButton("Sign In");
//        btnSignIn.addActionListener(e -> {
//            String username = enterUsername.getText();
//            String password = enterPassword.getText();
//            if (username.isEmpty() || password.isEmpty()) {
//                JOptionPane.showMessageDialog(gui,
//                        "Username or password field cannot be empty",
//                        "Warning", JOptionPane.WARNING_MESSAGE);
//            } else {
//                gui.client.connect();
//                ServerProtocol response = gui.client.serverRequest("login", username, password);
//                if (response.type.startsWith("true")) {
//                    JOptionPane.showMessageDialog(gui,
//                            "Signed in: " + username,
//                            "Sign-in success", JOptionPane.INFORMATION_MESSAGE);
//                    gui.setContentPane(gui.lobby);
//                    gui.setTitle("Lobby Room");
//                } else if (response.type.startsWith("false")) {
//                    JOptionPane.showMessageDialog(gui,
//                            response.message[0],
//                            "Sign-in failure", JOptionPane.WARNING_MESSAGE);
//
//                }
//            }
//        });
//        btnSignIn.setBounds(175, 190, 91, 29);
//        add(btnSignIn);

//        JLabel lblNoAccount = new JLabel("Don't have an account?");
//        lblNoAccount.setBounds(150, 250, 180, 16);
//        add(lblNoAccount);

//        JButton btnSignUp = new JButton("Sign Up");
//        btnSignUp.addActionListener(e -> {
//            gui.setContentPane(
//                    gui.register);
//            gui.setTitle("Sign up");
//        });
//        btnSignUp.setBounds(175, 230, 91, 29);
//        add(btnSignUp);

//        JButton test = new JButton("lobby");
//        test.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                ClientGui.gui.setContentPane(
//                        ClientGui.gui.lobby);
//                ClientGui.gui.setTitle("Lobby");
//            }
//        });
//        test.setBounds(175, 230, 91, 29);
//        add(test);
//    }
    }
}
