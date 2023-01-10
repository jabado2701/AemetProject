package dataPresentation;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ReadTemperature {

    List<String> readAllData(String from, String to) throws SQLException;

    List<String> returnTemperatures(String from, String to, Gson gson, ResultSet rs) throws SQLException;

    void recordToList(Gson gson, ResultSet rs, List<String> records) throws SQLException;

    Connection connect();

}
