package Server.Login;

import Server.User;


import static Server.Login.DatabaseQueries.getPassword;
import static Server.Login.DatabaseQueries.checkUsername;

public class LoginUser {

    public static int CheckLogin (User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        if (username.equals("") || password.equals("")) {

            return LoginUserResponses.invalidInput;
        } else if (checkUsername(user) == false) {

            return LoginUserResponses.userDoesNotExist;
        } else if (password.equals(getPassword(user))) {

            return LoginUserResponses.success;
        } else {
            return LoginUserResponses.failure;

            //check if user exists
            //if user exists then check that the passwords match
            //if they dont match return saying wrong password
            //else success

        }
    }
}
