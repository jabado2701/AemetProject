package temperatureFunctions;

import com.google.gson.Gson;
import dbManager.CreateTable;
import objects.Weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class TemperatureFilter implements Temperatures {
    public TemperatureFilter() {
    }

    public void MaxTemperature() throws IOException {
        File file = new File("datalake/");
        String[] files = file.list();
        for (String string : Objects.requireNonNull(files)) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("datalake/" + string));
            Gson gson = new Gson();
            String line;
            Weather prueba = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (prueba == null){
                    prueba = gson.fromJson(line, Weather.class);
                }
                else {
                    Weather aspirante = gson.fromJson(line, Weather.class);
                    if (aspirante.tmax() > prueba.tmax()){
                        prueba = aspirante;
                    }

                }

            }
            new CreateTable().insertInMaxTable(Objects.requireNonNull(prueba));
        }
    }


    public void MinTemperature() throws IOException {
        File file = new File("datalake/");
        String[] files = file.list();
        for (String string : Objects.requireNonNull(files)) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("datalake/" + string));
            Gson gson = new Gson();
            String line;
            Weather prueba = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (prueba == null){
                    prueba = gson.fromJson(line, Weather.class);
                }
                else {
                    Weather aspirante = gson.fromJson(line, Weather.class);
                    if (aspirante.tmin() < prueba.tmin()){
                        prueba = aspirante;
                    }

                }


            }
            new CreateTable().insertInMinTable(Objects.requireNonNull(prueba));
        }
    }




}