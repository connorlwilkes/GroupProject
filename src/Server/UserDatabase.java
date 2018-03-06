package Server;

import Server.User;

import java.util.ArrayList;
import java.util.List;

// Local user database for testing purposes
public class UserDatabase {

    private List<User> users;

    public UserDatabase() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

}
