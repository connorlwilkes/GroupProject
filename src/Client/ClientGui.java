package Client;

import Server.User;

import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame {

    public LogInFrame login;
    public LobbyFrame lobby;
    public RegisterFrame register;
    public ChatDisplay chat;
    public Client client;
    public InstructionPanel instructions;
    public QuestionMasterPanel qm;
    public QuestionMasterAnswerPanel questionMasterAnswerPanel;
    public QuestionMasterQuestionPanel questionMasterQuestionPanel;
    public QuestionPanel questionPanel;
    public User user;

    private ClientGui() {
        setTitle("Log In");
        setUp();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 400, 400);
        add(login);
        add(lobby);
        add(register);
        add(instructions);
        add(qm);
        add(questionMasterAnswerPanel);
        add(questionMasterQuestionPanel);
        add(questionPanel);
        setContentPane(login);
        setVisible(true);
    }

    public void setUp() {
        login = new LogInFrame(this);
        lobby = new LobbyFrame(this);
        register = new RegisterFrame(this);
        client = new Client(this);
        chat = new ChatDisplay(client);
        instructions = new InstructionPanel(this);
        qm = new QuestionMasterPanel(this);
        questionMasterAnswerPanel = new QuestionMasterAnswerPanel(this);
        questionMasterQuestionPanel = new QuestionMasterQuestionPanel(this);
        questionPanel = new QuestionPanel(this);
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
