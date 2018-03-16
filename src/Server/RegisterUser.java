package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static Server.DatabaseQueries.connect;
import static Server.DatabaseQueries.checkUsername;

/**
 * RegisterUser class for the registration process of the minigame
 *
 * @author Florence
 * @version 9/3/2018
 */

public class RegisterUser {
    /**
     * CheckUser method within the Register User class checks whether a
     * new user is valid and therefore can be added to the user database
     *
     * @param user the new user to be created from the input from the GUI
     * @return Server protocol to the server in order to alert the user whether
     * their registration has been successful and if not what error occurred.
     */
    public static ServerProtocol checkUser(User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        ServerProtocol a = new ServerProtocol("true", "Successfully registered user");
        ServerProtocol b = new ServerProtocol("false", "Invalid Input");
        ServerProtocol c = new ServerProtocol("false", "User already exists");
        ServerProtocol d = new ServerProtocol("false", "Failure");

        /**
         * this if rule checks whether the user has accidentally not inputted any data for username or password
         */
        if (username.equals("") || password.equals("")) {

            return b;
            /**
             * this if rule calls the checkUsername method from the DatabaseQueries class and checks whether the
             * user record already exists. Username is the primary key meaning that a non unique username will not
             * be able to be inputted into the database, however this extra check will prevent an SQL error being thrown
             * as well as the user being able to see why their request has failed
             * */
        } else if (!checkUsername(user)) {
            return c;
        }
        /**
         * this string is used in the next method to create a prepared statement and to query the database userdb
         */
        String query = "INSERT INTO userdb (username, password) VALUES (?, ?)";
        /**
         * here the connection method is called in order to connect to the database
         */
        try (Connection connection = connect()) {
            PreparedStatement pmst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        /**
         * creation of a prepared statement and the specification of the two parameters username and password
         * which have been inputted into the gui. These are then added to the database userdb.
         */
            pmst.setString(1, username);
            pmst.setString(2, password);
            pmst.execute();
            return a;
        /**
         * if the connection fails then server protocol for failure is returned
         */
        } catch (SQLException ex) {
            System.err.println(ex);
            return d;
        }

    }

}



