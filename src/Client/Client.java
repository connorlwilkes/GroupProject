package Client;

import Server.Message;
import Server.ServerProtocol;
import Server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {

    final private int port = 5000;
    private InetAddress host;
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
            connection = new Socket(InetAddress.getLocalHost(), port);
            host = InetAddress.getLocalHost();
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

    public void run() {
        while (true) {
            try {
                Object o = inputStream.readObject();
            if (o instanceof ServerProtocol) {
            	ServerProtocol message = (ServerProtocol)o;
            	if (message.type.startsWith("start")) {
            		gui.setBounds(0, 0, 900, 600);
            		gui.setContentPane(gui.instructions);
            		gui.revalidate();
                    gui.repaint();
                    gui.setTitle("instructions");
            		Thread.sleep(5000);
            		ServerProtocol getQuestionMaster = new ServerProtocol("get-qm");
            		outputStream.writeObject(getQuestionMaster);
                    outputStream.flush();
                    
            	}
            	if (message.type.startsWith("get-qm")) {
            		gui.remove(gui.instructions);
         
            		String questionMaster = message.message[0];
            		gui.qm.lblNewLabel.setText(questionMaster);
            		gui.setContentPane(gui.qm);
            		gui.revalidate();
                    gui.repaint();
                    gui.setTitle("qm");
                    
            		Thread.sleep(5000);
            		
            		ServerProtocol getQuestion = new ServerProtocol("question");
            		outputStream.writeObject(getQuestion);
                    outputStream.flush();
            	}
            	if (message.type.startsWith("quesiton")) {
            		gui.remove(gui.qm);
            		if (message.message[0].startsWith("qm")) {
            			String question = message.message[1];
            			gui.questionMasterQuestionPanel.txtrQuestion.setText(question);
            			gui.setContentPane(gui.questionMasterQuestionPanel);
                		gui.revalidate();
                        gui.repaint();
                        gui.setTitle("questionmasterQuestionPanel");
                        
            			Thread.sleep(5000);
            			
            			Thread.sleep(500);
            			ServerProtocol getAnswer = new ServerProtocol("get-answers");
            			outputStream.writeObject(getAnswer);
                        outputStream.flush();
            		}
            		if (message.message[0].startsWith("notqm")) {
            			String question = message.message[1];
            			gui.questionPanel.txtrQuestion.setText(question);
            			gui.setContentPane(gui.questionPanel);
                		gui.revalidate();
                        gui.repaint();
                        gui.setTitle("questionPanel");
            	
            			Thread.sleep(5000);
            			
            			ServerProtocol sendAnswer = new ServerProtocol("answer", gui.questionPanel.playerAnswer);
            			outputStream.writeObject(sendAnswer);
                        outputStream.flush();
                        
            			Thread.sleep(500);
            			ServerProtocol getAnswer = new ServerProtocol("get-answers");
            			outputStream.writeObject(getAnswer);
                        outputStream.flush();
            		}
            	}
            	if (message.type.startsWith("get-answer")) {
            		
            		if (message.message[0].startsWith("qm")) {
            			gui.remove(gui.questionMasterQuestionPanel);
            			
            			String player1Answer = message.message[1];
            			String player2Answer = message.message[2];
            			gui.questionMasterAnswerPanel.txtrQuestion.setText(gui.questionPanel.txtrQuestion.getText());
            			gui.questionMasterAnswerPanel.txtrPlayerAnswer.setText(player1Answer);
            			gui.questionMasterAnswerPanel.txtrPlayerAnswer_1.setText(player2Answer);
            			gui.setContentPane(gui.questionMasterAnswerPanel);
                		gui.revalidate();
                        gui.repaint();
                        gui.setTitle("questionMasteranswerpanel");
                     
            			while (gui.questionMasterAnswerPanel.voteCast = false) {
            				Thread.sleep(100);
            			}
            			ServerProtocol getScore = new ServerProtocol("getScore");
            			outputStream.writeObject(getScore);
                        outputStream.flush();
            			
            		}
            		if (message.message[0].startsWith("notqm")) {
            			gui.remove(gui.questionPanel);
            			String playerAnswer = message.message[1];
            			String player3Answer = message.message[2];
            			gui.answerPanel.txtrQuestion.setText(gui.questionPanel.txtrQuestion.getText());
            			gui.answerPanel.txtrPlayerAnswer.setText(playerAnswer);
            			gui.answerPanel.txtrPlayerAnswer_1.setText(player3Answer);
            			
            			gui.setContentPane(gui.answerPanel);
                		gui.revalidate();
                        gui.repaint();
                        gui.setTitle("answerPanel");
            			
            			while (gui.questionMasterAnswerPanel.voteCast = false) {
            				Thread.sleep(100);
            			}
            			ServerProtocol getScore = new ServerProtocol("getScore");
            			outputStream.writeObject(getScore);
                        outputStream.flush();
            		}
            	}
            	if (message.type.startsWith("getscores")) {
            		gui.remove(gui.questionMasterAnswerPanel);
            		gui.remove(gui.answerPanel);
            		
            		String player1Score = message.message[1];
            		String player2Score = message.message[2];
            		String player3Score = message.message[3];
            		gui.scorePanel.textArea.setText(player1Score + "\n" + player2Score + "\n" + player3Score);
            		
            		gui.setContentPane(gui.scorePanel);
            		gui.revalidate();
                    gui.repaint();
                    gui.setTitle("scorePanel");
            	}
            	

            

                } else if (o instanceof Message) {
                    Message message = (Message) o;
                    gui.chat.chatBox.append(message.toString() + "\n");
                }

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
