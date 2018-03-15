package Server.Login;

import Server.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static Server.Login.DatabaseQueries.connect;
import static Server.Login.DatabaseQueries.checkUsername;

public class RegisterUser {

    public static int checkUser(User user) {
        //the two inputs for this method are the username and password which have been inputted by the user in the GUI,
        //the server then calls this method to register the new user

        String username = user.getUsername();
        String password = user.getPassword();

        if (username.equals("") || password.equals("")) {

            return RegisterUserResponses.invalidInput;
            //JOptionPane.showMessageDialog(null,"invalid name or password","Error",JOptionPane.ERROR_MESSAGE);
            //this creates a pop up if both the password or usernames' are left empty, prevents the database from storing invalid
            //entries by a user and allows the user to rectify their mistake
        } else if (checkUsername(user) == true) {
            return RegisterUserResponses.userAlreadyExists;
        }

        String query = "INSERT INTO userdb (username, password) VALUES (?, ?)";


        try (Connection connection = connect()) {
            PreparedStatement pmst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pmst.setString(1, username);
            pmst.setString(2, password);

            return RegisterUserResponses.success;

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return RegisterUserResponses.failure;
    }

}


//open connection to database and then
// if above hold then store new account details in the database
