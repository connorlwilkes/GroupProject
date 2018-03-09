package Server.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess  {

    private final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/florence";
    private final String user = "florence";
    private final String password = "kx7t40vm7v";

    public static void main(String[] args) {
        DatabaseAccess a = new DatabaseAccess();
        try {
            Connection b = a.connect();
            b.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sets up a connection to the local SQL server
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
