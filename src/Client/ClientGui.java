package Client;

import Server.User;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {

    public ClientGui gui;
    public LogInFrame login = new LogInFrame();
    public LobbyFrame lobby = new LobbyFrame();
    public RegisterFrame register = new RegisterFrame();
    public ChatDisplay chat = new ChatDisplay();
    public Client client = new Client();
    public InstructionPanel instructions = new InstructionPanel();
    public QuestionMasterPanel qm = new QuestionMasterPanel();
    public QuestionMasterAnswerPanel questionMasterAnswerPanel = new QuestionMasterAnswerPanel();
    public QuestionMasterQuestionPanel questionMasterQuestionPanel = new QuestionMasterQuestionPanel();
    public QuestionPanel questionPanel = new QuestionPanel();
    public User user;

    private ClientGui() {
        setTitle("Log In");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 630, 460);
        add(login);
        add(lobby);
        add(register);
        add(chat);
        add(instructions);
        add(qm);
        add(questionMasterAnswerPanel);
        add(questionMasterQuestionPanel);
        add(questionPanel);
        setContentPane(login);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClientGui gui = new ClientGui();
            } catch (Exception e) {
                System.err.println(e);
            }
        });
    }
}
