package Server;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Message class for the game. Can be sent across a network as a serialized object
 *
 * @author Florence
 * @version 9/3/2018
 */
public class Message implements Serializable {

    private String message;
    private String time;
    private String sender;

    /**
     * Constructor for the message class
     *
     * @param message content of the message
     * @param sender  the user who is sending the message
     */
    public Message(String message, User sender) {
        this.message = message;
        setUpTime();
        this.sender = sender.getUsername();
    }

    /**
     * Sets up the time the message was sent
     */
    private void setUpTime() {
        Calendar now = Calendar.getInstance();
        time = (String.valueOf(now.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(now.get(Calendar.MINUTE)));
    }

    /**
     * toString method for the message class
     *
     * @return
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(time + " <" + sender + ">: " + message);
        return string.toString();
    }
}

