package Testing;

import Server.User;

import java.sql.*;

public class DatabaseInsert extends DatabaseAccess {

    // Inserts a user into the local database
    public void insertUser(User user) {

        // http://www.postgresqltutorial.com/postgresql-jdbc/insert/

        String SQL = "INSERT INTO users(username, password, email) VALUES(?,?,?)";

        try (Connection connection = super.connect();
             PreparedStatement psmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {


            int affectedRows = psmt.executeUpdate();

            if (affectedRows > 0) {

                try (ResultSet rs = psmt.getGeneratedKeys()) {
                    if (rs.next()) {
//                        user.setUserID(rs.getInt(1));
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
