package Server;

import Server.User;

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
     * @return a connection
     * @throws SQLException if connection fails it throws SQLException
     * */
    public static Connection connect() throws SQLException {
        /**
         * specifies the location of the database as well as the login credentials
         */
        String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/florence";
        String user = "florence";
        String password = "kx7t40vm7v";

        return DriverManager.getConnection(url, user, password);

    }

    /**
     * checkUsername method used by both the LoginUser and RegisterUser classes
     * @param user a new user to be checked
     * @return boolean, returns true if the user doesn't exist and returns false if the user does exist
     */

    public static boolean checkUsername(User user) {

        String username = user.getUsername();
        boolean x = false;

        String query = "SELECT * FROM userdb WHERE username = ?";

        try (Connection connection = connect();
             PreparedStatement pmst = connection.prepareStatement(query)) {

            pmst.setString(1, username);

            ResultSet rs = pmst.executeQuery();


            if (!rs.next()) {
                x = true;//returns true if the user doesn't exist
            } else {
                x = false;//returns false if the user exists already
            }
            rs.close();                           // Close ResultSet
            pmst.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return x;

    }


}



