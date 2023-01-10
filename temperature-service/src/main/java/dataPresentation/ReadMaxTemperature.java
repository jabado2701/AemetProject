package dataPresentation;

import com.google.gson.Gson;
import objects.NewWeather;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadMaxTemperature  {
    public List<String> readAllData(String from, String to) throws SQLException {
        Gson gson = new Gson();
        Connection con = connect();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT * FROM maxWeather";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        return returnTemperatures(from, to, gson, rs);
    }

    public List<String> returnTemperatures(String from, String to, Gson gson, ResultSet rs) throws SQLException {
        List<String> records = new ArrayList<>();

        while (rs.next()) {
            LocalDate fromDate = LocalDate.parse(from).minusDays(1);
            LocalDate toDate = LocalDate.parse(to).plusDays(1);
            LocalDate lDate = LocalDate.parse(rs.getString("date"));
            if (lDate.isAfter(fromDate) && lDate.isBefore(toDate)) {

                recordToList(gson, rs, records);
            }
        }
        return records;
    }

    public void recordToList(Gson gson, ResultSet rs, List<String> records) throws SQLException {
        String date = rs.getString("date");
        String time = rs.getString("time");
        String place = rs.getString("place");
        String station = rs.getString("station");
        double maxValue = rs.getDouble("maxValue");

        NewWeather maxWeather = new NewWeather(date, time, place, station, maxValue);
        String print = gson.toJson(maxWeather);
        records.add(print);
    }

    public Connection connect() {
        Connection conn;
        try {
            String url = "jdbc:sqlite:datamart.db/";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}