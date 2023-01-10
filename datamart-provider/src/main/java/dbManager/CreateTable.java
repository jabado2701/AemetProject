package dbManager;

import objects.Weather;

import java.sql.*;

public class CreateTable {
    public CreateTable() {
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            createMaxTable(statement);
            createMinTable(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createMaxTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS maxWeather (" +
                "date TEXT NOT NULL, " +
                "time TEXT NOT NULL, " +
                "place TEXT NOT NULL," +
                "station TEXT NOT NULL," +
                "maxValue REAL NOT NULL" +
                ")");
    }

    public void createMinTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS minWeather (" +
                "date TEXT NOT NULL, " +
                "time TEXT NOT NULL, " +
                "place TEXT NOT NULL," +
                "station TEXT NOT NULL," +
                "minValue REAL NOT NULL" +
                ")");
    }

    public void insertInMaxTable(Weather weather) {
        String sql = "INSERT INTO maxWeather(date,time,place,station,maxValue) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, weather.date().replaceAll("T(.*)", ""));
            pstmt.setString(2, weather.date().replaceAll("(.*)T", ""));
            pstmt.setString(3, weather.ubi());
            pstmt.setString(4, weather.id());
            pstmt.setDouble(5, weather.tmax());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertInMinTable(Weather weather) {
        String sql = "INSERT INTO minWeather(date,time,place,station,minValue) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, weather.date().replaceAll("T(.*)", ""));
            pstmt.setString(2, weather.date().replaceAll("(.*)T", ""));
            pstmt.setString(3, weather.ubi());
            pstmt.setString(4, weather.id());
            pstmt.setDouble(5, weather.tmin());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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