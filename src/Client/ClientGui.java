package Client;

import Server.User;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {

    public static ClientGui gui = null;
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
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        add(login);
        add(lobby);
        add(register);
        add(chat);
        add(instructions);
        add(qm);
        add(questionMasterAnswerPanel);
        add(questionMasterQuestionPanel);
        add(questionPanel);
        setContentPane(qm);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                gui = new ClientGui();
                gui.setVisible(true);
            } catch (Exception e) {
                System.err.println(e);
            }
        });
    }
}
