package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerProtocol implements Serializable {

    private String type;
    private List<String> information;

    public ServerProtocol(String type, String... args) {
        this.type = type;
        information = new ArrayList<>();
        Collections.addAll(information, args);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getInformation() {
        return information;
    }

    public void setInformation(ArrayList<String> information) {
        this.information = information;
    }
}
