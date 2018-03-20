package Server;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Server.DatabaseQueries.checkUsername;
import static Server.DatabaseQueries.connect;
import static Server.PasswordSecure.passwordCheck;
import static java.lang.System.err;

/**
 * LoginUser class for the Login process of the minigame
 *
 * @author Florence
 * @version 9/3/2018
 */

public class LoginUser {
    /**
     * method CheckLogin which checks whether the user is valid input and if the login credentials are valid
     *
     * @param user the user inputted into the gui
     * @return a ServerProtocol which is dependent upon if the login has failed or succeeded and if a failure
     * on the reason for it failing
     */

    public static ServerProtocol CheckLogin(User user) {

        String username = user.getUsername();
        String password = user.getPassword();


        ServerProtocol a = new ServerProtocol("true", "Successfully logged in");
        ServerProtocol b = new ServerProtocol("false", "Invalid Input");
        ServerProtocol c = new ServerProtocol("false", "User does not exist");
        ServerProtocol d = new ServerProtocol("false", "Failure");
        /**
         * this string is used to construct the Prepared Statement which is used to check whether
         * the login credentials are valid and if the user exists
         */


        String query = "SELECT * FROM userdbtest WHERE username = ? ";

        try (Connection connection = connect();
             PreparedStatement pmst = connection.prepareStatement(query)) {
            /**
             * sets the two parameters for the prepared statement of username and password
             */
            pmst.setString(1, username);

            /**
             * produces a results set of the user and their respective password and salt
             */
            ResultSet rs = pmst.executeQuery();
            while (rs.next()) {
                /**
                 * checks whether the passwords match
                 */
                try {
                    if (passwordCheck(password, rs.getBytes(2), rs.getBytes(3))) {
                        return a;
                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    e.printStackTrace();
                }
            }
            /**
             * closes the results set and the prepared statement
             */
            rs.close();
            pmst.close();
            /**
             * checks whether the inputted username and password are valid
             */
            if (username.equals("") || password.equals("")) {

                return b;
            }
            /**
             * checks whether the user exists
             */
            if (checkUsername(user)) {

                return c;
            }

        } catch (SQLException ex) {
            err.println(ex);
        }
        /**
         * if all else fails then the return is failure
         */
        return d;
    }
}