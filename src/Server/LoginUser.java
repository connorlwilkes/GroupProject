package Server;

import static Server.DatabaseQueries.checkUsername;

public class LoginUser {

    public static ServerProtocol CheckLogin (User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        ServerProtocol a= new ServerProtocol("true","Successfully logged in");
        ServerProtocol b= new ServerProtocol("false","Invalid Input");
        ServerProtocol c= new ServerProtocol("false","User does not exist");
        ServerProtocol d= new ServerProtocol("false","Failure");

        if (username.equals("") || password.equals("")) {

            return b;
        } else if (!checkUsername(user)) {

            return c;
        } else if (password.equals(user.getPassword())) {

            return a;
        } else {
            return d;

            //check if user exists
            //if user exists then check that the passwords match
            //if they dont match return saying wrong password
            //else success

        }
    }
}
