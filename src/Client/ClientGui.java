package Client;

import Server.User;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {

    public ClientGui gui;
    public LogInFrame login = new LogInFrame(this);
    public LobbyFrame lobby = new LobbyFrame(this);
    public RegisterFrame register = new RegisterFrame(this);
    public ChatDisplay chat = new ChatDisplay(this);
    public Client client = new Client(this);
    public InstructionPanel instructions = new InstructionPanel(this);
    public QuestionMasterPanel qm = new QuestionMasterPanel(this);
    public QuestionMasterAnswerPanel questionMasterAnswerPanel = new QuestionMasterAnswerPanel(this);
    public QuestionMasterQuestionPanel questionMasterQuestionPanel = new QuestionMasterQuestionPanel(this);
    public QuestionPanel questionPanel = new QuestionPanel(this);
    public User user;

    private ClientGui() {
        setTitle("Log In");
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 450, 278);
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
