package Server;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static Server.DatabaseQueries.checkUsername;
import static Server.DatabaseQueries.connect;
import static Server.PasswordSecure.EncryptPassword;
import static Server.PasswordSecure.createSalt;

/**
 * RegisterUser class for the registration process of the minigame
 *
 * @author Florence
 * @version 22/3/2018
 */
public class RegisterUser {
    
    /**
     * Method to check within the Register User class whether a
     * new user is valid and therefore can be added to the user database
     *
     * @param user the new user to be created from the input from the GUI
     * @return Server protocol to the server in order to alert the user whether
     * their registration has been successful and if not what error occurred.
     */
    public static ServerProtocol checkUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {

        System.out.println("her: start");

        String username = user.getUsername();
        String password = user.getPassword();


        ServerProtocol a = new ServerProtocol("true", "Successfully registered user");
        ServerProtocol b = new ServerProtocol("false", "Invalid Input");
        ServerProtocol c = new ServerProtocol("false", "User already exists");
        ServerProtocol d = new ServerProtocol("false", "Failure");

        /**
         * this if rule calls the checkUsername method from the DatabaseQueries class and checks whether the
         * user record already exists. Username is the primary key meaning that a non unique username will not
         * be able to be inputted into the database, however this extra check will prevent an SQL error being thrown
         * as well as the user being able to see why their request has failed
         * */
        if (!checkUsername(user)) {
            return c;

        }
        /**
         * this if rule checks whether the user has accidentally not inputted any data for username or password
         */
        else if (username.equals("") || password.equals("")) {

            return b;

        }  else {
            /**
             * this string is used in the next method to create a prepared statement and to query the database userdb
             */
            String query = "INSERT INTO userdbtest (username, password, salt) VALUES (?, ?, ?)";
            byte[] salt = createSalt();
            /**
             * here the connection method is called in order to connect to the database
             */
            byte[] encryptedPassword = EncryptPassword(password, salt);
            try (Connection connection = connect()) {
                PreparedStatement pmst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                /**
                 * creation of a prepared statement and the specification of the two parameters username and password
                 * which have been inputted into the gui. These are then added to the database userdb.
                 */
                pmst.setString(1, username);
                pmst.setBytes(2, encryptedPassword);
                pmst.setBytes(3, salt);
                pmst.execute();
                pmst.close();
                System.out.println("here: a");
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
}



