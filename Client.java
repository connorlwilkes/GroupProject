package Client;

import Server.Message;
import Server.ServerProtocol;
import Server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JTextArea;

public class Client {

    final private int port = 5000;
    final private String host = "localhost";
    public ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    private Socket connection;
    private User user;
    private ClientGui gui;

    public Client(ClientGui gui) {
        this.gui = gui;
    }

    public void connect() {
        try {
            connection = new Socket(host, port);
            outputStream = new ObjectOutputStream(connection.getOutputStream());
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

    public void sendMessage(String content) {
        try {
            Message message = new Message(content, user);
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void listenForInput() throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            Object o = inputStream.readObject();
            if (o instanceof ServerProtocol) {
            	ServerProtocol message = (ServerProtocol)o;
            	if (message.type.startsWith("start")) {
                    String questionMaster = message.message[0];
            		gui.qm.lblNewLabel.setText(questionMaster);
            		gui.qm.setVisible(true);
            		Thread.sleep(5000);
            		ServerProtocol getQuestionMaster = new ServerProtocol("get-qm");
            		outputStream.writeObject(getQuestionMaster);
                    outputStream.flush();
            	}
            	if (message.type.startsWith("get-qm")) {
            		String questionMaster = message.message[0];
            		gui.qm.lblNewLabel.setText(questionMaster);
            		gui.qm.setVisible(true);
            		Thread.sleep(5000);
            		ServerProtocol getQuestion = new ServerProtocol("question");
            		outputStream.writeObject(getQuestion);
                    outputStream.flush();
                    gui.qm.setVisible(false);
            	}
            	if (message.type.startsWith("quesiton")) {
            		if (message.message[0].startsWith("qm")) {
            			String question = message.message[1];
            			gui.questionMasterQuestionPanel.txtrQuestion.setText(question);
            			gui.questionMasterQuestionPanel.setVisible(true);
            			Thread.sleep(5000);
                        gui.questionMasterQuestionPanel.setVisible(false);
            		}
            		if (message.message[0].startsWith("notqm")) {
            			String question = message.message[1];
            			gui.questionPanel.txtrQuestion.setText(question);
            			gui.questionPanel.txtrQuestion.setVisible(true);
            			Thread.sleep(5000);
            			ServerProtocol sendAnswer = new ServerProtocol("answer", gui.questionPanel.playerAnswer);
            			outputStream.writeObject(sendAnswer);
                        outputStream.flush();
            			gui.questionPanel.txtrQuestion.setVisible(false);
            			Thread.sleep(500);
            			ServerProtocol getAnswer = new ServerProtocol("get-answer");
            			outputStream.writeObject(getAnswer);
                        outputStream.flush();
            		}
            	}
            	if (message.type.startsWith("get-answer")) {
            		if (message.message[0].startsWith("qm")) {
            			String player1Answer = message.message[1];
            			String player2Answer = message.message[2];
            			gui.questionMasterAnswerPanel.txtrQuestion.setText(gui.questionPanel.txtrQuestion.getText());
            			gui.questionMasterAnswerPanel.txtrPlayerAnswer.setText(player1Answer);
            			gui.questionMasterAnswerPanel.txtrPlayerAnswer_1.setText(player2Answer);
            			gui.questionMasterAnswerPanel.setVisible(true);
            			while (gui.questionMasterAnswerPanel.voteCast = false) {
            				Thread.sleep(100);
            			}
            			ServerProtocol getScore = new ServerProtocol("getScore");
            			outputStream.writeObject(getScore);
                        outputStream.flush();
            			gui.questionMasterAnswerPanel.setVisible(false);
            		}
            		if (message.message[0].startsWith("notqm")) {
            			String playerAnswer = message.message[1];
            			String player3Answer = message.message[2];
            			gui.answerPanel.txtrQuestion.setText(gui.questionPanel.txtrQuestion.getText());
            			gui.answerPanel.txtrPlayerAnswer.setText(playerAnswer);
            			gui.answerPanel.txtrPlayerAnswer_1.setText(player3Answer);
            			gui.answerPanel.setVisible(true);
            			while (gui.questionMasterAnswerPanel.voteCast = false) {
            				Thread.sleep(100);
            			}
            			gui.answerPanel.setVisible(false);
            		}
            	}
            	if (message.type.startsWith("getScore")) {
            		String player1Score = message.message[1];
            		String player2Score = message.message[2];
            		String player3Score = message.message[3];
            		gui.scorePanel.textArea.setText(player1Score  );
            		// need an array of the user names attached to the get score call 
            		// also what is the protocol for score 
            	}
            	

            }
        }
    }
}
