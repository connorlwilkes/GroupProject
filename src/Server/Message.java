package Server;

import java.util.Calendar;

public class Message {


    private String message;
    private String time;
    private String sender;

    public Message(String message, User sender) {
        this.message = message;
        setUpTime();
        this.sender = sender.getUsername();
    }

    private void setUpTime() {
        Calendar now = Calendar.getInstance();
        time = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

