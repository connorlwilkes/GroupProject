package Server.Login;

import Server.User;

import java.sql.*;

public class DatabaseQueries {

    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/florence";
        String user = "florence";
        String password = "kx7t40vm7v";
        return DriverManager.getConnection(url, user, password);

    }

    public static boolean checkUsername(User user) {

        String username = user.getUsername();
        boolean x = false;

        String query = "SELECT * FROM userdb WHERE username = ?";

        try (Connection connection = connect();
            PreparedStatement pmst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            pmst.setString(1, username);

            ResultSet rs = pmst.getGeneratedKeys();


                if (rs == null) {
                    x=  false;
                }
                else {
                    x = true;
                }

            } catch (SQLException ex) {
                System.err.println(ex);
            }

        return x;

        }



 }



