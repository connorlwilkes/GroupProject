package Server;

import java.io.Serializable;

/**
 * Server Protocol class to establish the protocols for the server. Has the field variables type and message of types String and String[]
 * respectively
 *
 * @author Florence
 * @version 21/03/2018
 */
public class ServerProtocol implements Serializable {

    public String type;
    public String[] message;

    /**
     * Constructor for ServerProtocol with the parameter args.
     *
     * @param args String of arguments to be passed
     */
    public ServerProtocol(String... args) {
        message = new String[args.length - 1];
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                type = args[i];
            } else {
                message[i - 1] = args[i];
            }
        }
    }

    /**
     * String toString method
     *
     * @return server protocol in sensible format
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Type: " + type + "\nMessage: ");
        for (String message : message) {
            string.append(message + "\n");
        }
        return string.toString();
    }
}
