package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Server.DatabaseQueries.checkUsername;
import static Server.DatabaseQueries.connect;



public class LoginUser {

    public static ServerProtocol CheckLogin (User user) {

        String username = user.getUsername();
        String password = user.getPassword();

        ServerProtocol a = new ServerProtocol("true", "Successfully logged in");
        ServerProtocol b = new ServerProtocol("false", "Invalid Input");
        ServerProtocol c = new ServerProtocol("false", "User does not exist");
        ServerProtocol d = new ServerProtocol("false", "Failure");





        String query = "SELECT * FROM userdb WHERE username = ? and password= ? ";

        try (Connection connection = connect();
             PreparedStatement pmst = connection.prepareStatement(query)) {

            pmst.setString(1, username);
            pmst.setString(2, password);

            ResultSet rs = pmst.executeQuery();
            while(rs.next()) {
                if (rs.getString(2).equals(password)) {

                    return a;
                }
            }
                rs.close();                           // Close ResultSet
                pmst.close();

            if (username.equals("") || password.equals("")) {

                return b;
            }

            if (checkUsername(user)) {

                return c;
            }

        } catch(SQLException ex){
                System.err.println(ex);

            }


            return d;
        }


        //check if user exists
        //if user exists then check that the passwords match
        //if they dont match return saying wrong password
        //else success


    }