package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUpdate extends DatabaseAccess {
    
    // Updates the particular column in a user's row
    public void update(String columnToUpdate, User user) {

        if (checkColumn(columnToUpdate) == 0) {
            throw new IllegalArgumentException("Cannot change that column or column does not exist");
        }

        String SQL = buildString(checkColumn(columnToUpdate));

        try (Connection connection = super.connect();
             PreparedStatement psmt = connection.prepareStatement(SQL)) {

            psmt.setString(1, columnToUpdate);
            psmt.setInt(2, user.getUserID());

            psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Checks if a column is allowed to be changed
    private static int checkColumn(String field) {

        if (field.equals("password")) {
            return 1;
        } else if (field.equals("username")) {
            return 2;
        } else if (field.equals("email")) {
            return 3;
        } else {
            return 0;
        }
    }

    // Builds SQL query String
    private static String buildString(int type) {

        StringBuilder toReturn = new StringBuilder();

        toReturn.append("UPDATE users SET ");

        if (type == 1) {
            toReturn.append("password = ? WHERE userid = ?");
        } else if (type == 2) {
            toReturn.append("username = ? WHERE userid = ?");
        } else if (type == 3) {
            toReturn.append("email = ? WHERE userid = ?");
        } else {
            throw new IllegalArgumentException("Invalid argument");
        }

        return toReturn.toString();
    }
}
