package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Server.DatabaseQueries.checkUsername;
import static Server.DatabaseQueries.connect;
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
        String query = "SELECT * FROM userdb WHERE username = ? AND password= ? ";

        try (Connection connection = connect();
             PreparedStatement pmst = connection.prepareStatement(query)) {
            /**
             * sets the two parameters for the prepared statement of username and password
             */
            pmst.setString(1, username);
            pmst.setString(2, password);
            /**
             * produces a results set of the user and their respective password
             */
            ResultSet rs = pmst.executeQuery();
            while (rs.next()) {
                /**
                 * checks whether the passwords match
                 */
                if (rs.getString(2).equals(password)) {
                    return a;
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