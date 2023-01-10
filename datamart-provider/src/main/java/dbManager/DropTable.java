package dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {
    public DropTable() {
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            dropMaxTable(statement);
            dropMinTable(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropMaxTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS maxWeather;");
    }

    public void dropMinTable(Statement statement) throws SQLException {
        statement.execute("DROP TABLE IF EXISTS minWeather;");
    }

    public Connection connect() {
        Connection conn;
        try {
            String url = "jdbc:sqlite:datamart.db/";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}