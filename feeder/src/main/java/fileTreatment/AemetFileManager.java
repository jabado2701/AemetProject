package fileTreatment;

import com.google.gson.Gson;
import objects.Weather;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AemetFileManager implements FileManager {

    private final Gson gson;

    public AemetFileManager() {
        gson = new Gson();
    }

    public void filecreator() {
        String dir = "datalake/";
        File directory = new File(dir);

        if (directory.mkdir()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Directory not created");
        }
    }

    public void txtCreator(List<Weather> weatherList) throws IOException {


        LocalDate todayDate = LocalDate.now();
        writeNewInFile(bufferedWriter(todayDate), get(todayDate), weatherList, todayDate);

        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        writeNewInFile(bufferedWriter(yesterdayDate), get(yesterdayDate), weatherList, yesterdayDate);

    }


    public void writeNewInFile(BufferedWriter bufferedWriter, Set<Weather> weathers, List<Weather> weatherList, LocalDate localDate) throws IOException {

        weatherList.stream()
                .filter(weather -> weather.date().replaceAll("T(.*)", "").contains(localDate.toString()))
                .filter(weather -> !weathers.contains(weather))
                .forEach(weather -> {
                    try {
                        save(bufferedWriter, weather);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void save(BufferedWriter bufferedWriter, Weather weather) throws IOException {
        Gson gson = new Gson();
        String string = gson.toJson(weather) + "\n";
        bufferedWriter.append(string);
    }


    public BufferedWriter bufferedWriter(LocalDate localDate) throws IOException {

        return new BufferedWriter(new FileWriter("datalake/" + localDate.format(DateTimeFormatter.BASIC_ISO_DATE) + ".events", true));

    }

    public Set<Weather> get(LocalDate localDate) throws IOException {

        String date = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("datalake/" + date + ".events"));
        Set<Weather> set = new HashSet<>();


        String line;
        while ((line = bufferedReader.readLine()) != null) {

            set.add(gson.fromJson(line, Weather.class));

        }
        return set;
    }
}