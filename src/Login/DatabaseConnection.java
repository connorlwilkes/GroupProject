package Login;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

    Connection connect() throws SQLException;
}
