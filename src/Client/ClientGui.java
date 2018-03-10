package Client;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {

    public static ClientGui gui = null;
    public LogInFrame login = new LogInFrame();
    public LobbyFrame lobby = new LobbyFrame();
    public RegisterFrame register = new RegisterFrame();
    public ChatDisplay chat = new ChatDisplay();
    public Client client = new Client();

    public ClientGui() {
        setTitle("Log In");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        add(login);
        add(lobby);
        add(register);
//        add(chat);
        setContentPane(login);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    gui = new ClientGui();
                    gui.setVisible(true);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
    }
}
