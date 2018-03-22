package Client;

import Server.Message;
import Server.ServerProtocol;
import Server.User;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class for the minigame game. Takes input from the client gui and sends protocol to the server in addition
 * to processing protocol from the server
 */
public class Client implements Runnable {

    private int port;
    private InetAddress host;
    public ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    private Socket connection;
    private User user;
    private ClientGui gui;

    /**
     * Constructor for the Client class
     *
     * @param gui gui associated with the client
     */
    public Client(ClientGui gui) {
        this.gui = gui;
        this.port = 6000;
        try {
            this.host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * Alternative constructor for the client class, called in normal circumstances o connect to a host other than
     * locahost
     *
     * @param gui  gui associated with the client
     * @param port port to connect to
     * @param host host to connect to
     */
    public Client(ClientGui gui, String port, String host) {
        this.gui = gui;
        try {
            this.host = InetAddress.getByName(host);
            this.port = Integer.parseInt(port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /**
     * Connects the client to the server
     */
    public void connect() {
        try {
            connection = new Socket(host, port);
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(connection.getInputStream());
        } catch (ConnectException ex) {
            System.out.println("Connection failure");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Stops the connection to the server
     */
    public void stop() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a request to the server and waits for a reply
     *
     * @param args list of strings to construct the protocol with
     * @return the response from the server
     */
    public ServerProtocol serverRequest(String... args) {
        try {
            ServerProtocol message = new ServerProtocol(args);
            outputStream.writeObject(message);
            outputStream.flush();
            if (args[0].equals("login")) {
                user = new User(args[1], args[2]);
            }
            return (ServerProtocol) inputStream.readObject();
        } catch (ConnectException ex) {
            System.out.println("Connection failure");
            return new ServerProtocol("false", "Server connection error");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
        return new ServerProtocol("false", "Server error");
    }

    /**
     * Sends a message object to the server
     *
     * @param content content of the message
     */
    public void sendMessage(String content) {
        try {
            Message message = new Message(content, user);
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Run method for the thread, is called when a game starts. The thread processes protocol to and from the server
     */
    public void run() {
        while (true) {
            try {
                Object o = inputStream.readObject();
                if (o instanceof ServerProtocol) {
                    ServerProtocol message = (ServerProtocol) o;
                    processGameRequests(message);
                } else if (o instanceof Message) {
                    Message message = (Message) o;
                    gui.chat.chatBox.append(message.toString() + "\n");
                }
            } catch (EOFException ex) {
                JOptionPane.showMessageDialog(gui, "Server connection lost", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                throw new RuntimeException("Connection broken " + ex);
            } catch (IOException ex) {
                throw new RuntimeException("Connection broken " + ex);
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("Connection broken " + e);
            }
        }
    }

    /**
     * Processes input for the Headline game
     *
     * @param message protocol from the server
     * @throws IOException
     * @throws InterruptedException
     */
    private void processGameRequests(ServerProtocol message) throws IOException, InterruptedException {
        try {
            System.out.println(message);
            if (message.type.startsWith("start")) {
                start(message, 5000);
            } else if (message.type.startsWith("get-qm")) {
                getQm(message, 5000);
            } else if (message.type.startsWith("question")) {
                question(message, 5000);
            } else if (message.type.startsWith("get-answer")) {
                answer(message);
            } else if (message.type.startsWith("get-scores")) {
                scores(message);
            } else if (message.type.startsWith("end")) {
                end(message);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(gui, "Server connection lost", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Processes a start request
     *
     * @param message  protocol
     * @param waitTime time to wait
     * @throws InterruptedException
     * @throws IOException
     */
    private void start(ServerProtocol message, int waitTime) throws InterruptedException, IOException {
        revalidateRepaintRenameResize("Instructions", gui.instructions, 900, 600);
        gui.qm.lblRound.setText("Round: " + message.message[1]);
        gui.scorePanel.lblRound.setText("Round: " + message.message[1]);
        gui.questionPanel.roundNumber.setText("Round: " + message.message[1]);
        Thread.sleep(waitTime);
        ServerProtocol getQuestionMaster = new ServerProtocol("get-qm");
        outputStream.writeObject(getQuestionMaster);
        outputStream.flush();
    }

    /**
     * Gets the question master for the current round of the game
     *
     * @param message  protocol from server
     * @param waitTime time to wait
     * @throws IOException
     * @throws InterruptedException
     */
    private void getQm(ServerProtocol message, int waitTime) throws IOException, InterruptedException {
        gui.remove(gui.instructions);
        revalidateRepaintRenameResize("Question Master", gui.qm, 900, 600);
        String questionMaster = message.message[0];
        gui.qm.questionMaster.setText(questionMaster);
        Thread.sleep(waitTime);
        ServerProtocol getQuestion = new ServerProtocol("question");
        outputStream.writeObject(getQuestion);
        outputStream.flush();
    }

    /**
     * Gets the question from the server
     *
     * @param message  protocol from the server
     * @param waitTime time to wait
     * @throws InterruptedException
     * @throws IOException
     */
    private void question(ServerProtocol message, int waitTime) throws InterruptedException, IOException {
        gui.remove(gui.qm);
        if (message.message[0].startsWith("qm")) {
            String question = message.message[1];
            gui.questionMasterQuestionPanel.txtrQuestion.setText(question);
            gui.questionMasterAnswerPanel.txtrQuestion.setText(question);
            revalidateRepaintRenameResize("Question Master: Question", gui.questionMasterQuestionPanel, 900, 600);
            Thread.sleep(waitTime);
            Thread.sleep(500);
            ServerProtocol getAnswer = new ServerProtocol("get-answers");
            outputStream.writeObject(getAnswer);
            outputStream.flush();
        } else if (message.message[0].startsWith("notqm")) {
            String question = message.message[1];
            gui.questionPanel.txtrQuestion.setText(question);
            revalidateRepaintRenameResize("Question", gui.questionPanel, 900, 600);
            Thread.sleep(waitTime);
            ServerProtocol sendAnswer = new ServerProtocol("answer", gui.questionPanel.playerAnswer);
            outputStream.writeObject(sendAnswer);
            outputStream.flush();
            Thread.sleep(500);
            ServerProtocol getAnswer = new ServerProtocol("get-answers");
            outputStream.writeObject(getAnswer);
            outputStream.flush();
        }
    }

    /***
     * Gets the answers from the server
     *
     * @param message server protocol
     * @throws IOException
     */
    private void answer(ServerProtocol message) throws IOException {
        if (message.message[0].startsWith("qm")) {
            gui.remove(gui.questionMasterQuestionPanel);
            String player1Answer = message.message[1];
            String player2Answer = message.message[2];
            gui.questionMasterAnswerPanel.txtrQuestion.setText(gui.questionMasterAnswerPanel.txtrQuestion.getText());
            gui.questionMasterAnswerPanel.txtrPlayerAnswer.setText(player1Answer);
            gui.questionMasterAnswerPanel.txtrPlayerAnswer_1.setText(player2Answer);
            gui.chat.chatBox.setText("Make your decision on your own!");
            revalidateRepaintRenameResize("Question Master: Answers", gui.questionMasterAnswerPanel, 900, 600);
        } else if (message.message[0].startsWith("notqm")) {
            gui.remove(gui.questionPanel);
            String playerAnswer = message.message[1];
            String player2Answer = message.message[2];
            gui.answerPanel.txtrQuestion.setText(gui.questionPanel.txtrQuestion.getText());
            gui.answerPanel.txtrPlayerAnswer.setText(playerAnswer);
            gui.answerPanel.txtrPlayerAnswer_1.setText(player2Answer);
            revalidateRepaintRenameResize("Answers", gui.answerPanel, 900, 600);
        }
    }

    /**
     * Gets the scores from the server
     *
     * @param message server protocol
     * @throws IOException
     */
    private void scores(ServerProtocol message) throws IOException {
        gui.remove(gui.questionMasterAnswerPanel);
        gui.remove(gui.answerPanel);
        String roundWinner = message.message[0];
        String player1Score = message.message[1];
        String player2Score = message.message[2];
        String player3Score = message.message[3];
        gui.instructions.welcome.setText("Next round!");
        gui.scorePanel.textArea.setText(player1Score + "\n" + player2Score + "\n" + player3Score);
        gui.scorePanel.txtPlayerName.setText(roundWinner);
        gui.questionPanel.txtrAnswer.setText("Write Answer here!");
        gui.questionPanel.txtrAnswer.setEditable(true);
        gui.questionPanel.playerAnswer = null;
        revalidateRepaintRenameResize("Scores on the doors", gui.scorePanel, 900, 600);
        ServerProtocol nextRound = new ServerProtocol("next-round");
        outputStream.writeObject(nextRound);
        outputStream.flush();
    }

    /**
     * Processes an end request for the game
     */
    private void end(ServerProtocol message) {
        StringBuilder string = new StringBuilder();
        for (int i = 1; i < message.message.length; i++) {
            string.append(" " + message.message[i]);
        }
        gui.finalScores.txtPlayerName.setText(string.toString());
        revalidateRepaintRenameResize("Final scores on the doors", gui.finalScores, 900, 600);
    }

    private void revalidateRepaintRenameResize(String title, JPanel toSet, int width, int height) {
        gui.setBounds(0, 0, width, height);
        gui.setContentPane(toSet);
        gui.revalidate();
        gui.repaint();
        gui.setTitle(title);
    }

}
