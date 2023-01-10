package fileTreatment;

import objects.Weather;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface FileManager {

     void filecreator();

     void txtCreator(List<Weather> weatherList) throws IOException;

     void writeNewInFile(BufferedWriter bufferedWriter, Set<Weather> weathers, List<Weather> weatherList, LocalDate localDate) throws IOException;

     void save(BufferedWriter bufferedWriter, Weather weather) throws IOException;

    BufferedWriter bufferedWriter(LocalDate localDate) throws IOException;

    Set<Weather> get(LocalDate localDate) throws IOException;

    }
