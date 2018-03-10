package Server;

import java.io.Serializable;

public class ServerProtocol implements Serializable {

    public String type;
    public String[] message;

    public ServerProtocol(String... args) {
        message = new String[args.length-1];
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                type = args[i];
            } else {
                message[i-1] = args[i];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Type: " + type + "\nMessage: ");
        for (String message: message) {
            string.append(message + "\n");
        }
        return string.toString();
    }
}
