package Server;

import java.sql.*;

/**
 * DatabaseQueries class with methods used by both the LoginUser and RegisterUser classes
 *
 * @author Florence
 * @version 9/3/2018
 */

public class DatabaseQueries {
    /**
     * Connection class used to connect to and query databases
     *
     * @return a connection
     * @throws SQLException if connection fails it throws SQLException
     */
    public static Connection connect() throws SQLException {
        /**
         * specifies the location of the database as well as the login credentials
         */
        String url = "db_link";
        String user = "test";
        String password = "pass";

        return DriverManager.getConnection(url, user, password);

    }

    /**
     * checkUsername method used by both the LoginUser and RegisterUser classes
     *
     * @param user a new user to be checked
     * @return boolean, returns true if the user doesn't exist and returns false if the user does exist
     */

    public static boolean checkUsername(User user) {

        String username = user.getUsername();
        boolean x = false;
        /**
         * this string sets up the prepared statement which queries the database to return records
         * where the username equals the one specified
         */
        String query = "SELECT * FROM userdbtest WHERE username = ?";

        try (Connection connection = connect();
             PreparedStatement pmst = connection.prepareStatement(query)) {

            pmst.setString(1, username);

            ResultSet rs = pmst.executeQuery();

            /**
             * if the results set is empty then the user doesn't exist therefore true is returned
             */
            if (!rs.next()) {
                x = true;//returns true if the user doesn't exist
            } else {
                x = false;//returns false if the user exists already
            }
            rs.close();
            pmst.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return x;

    }


}



