package sensor;

import com.google.gson.*;
import objects.Area;
import objects.Weather;
import sensor.APIKey;
import sensor.WeatherSensor;

import java.io.IOException;
import java.util.List;

public class AemetWeatherSensor implements WeatherSensor {
    final static String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
    Area gcArea = new Area(27.5, 28.4, -16, -15);

    public List<Weather> read() throws IOException {
        Gson gson = new Gson();
        String response = APIKey.request(url);
        JsonObject getArtist = JsonParser.parseString(response).getAsJsonObject();
        String getFollowers = getArtist.get("datos").getAsString();
        String response1 = APIKey.request(getFollowers);
        JsonArray jsonElements = gson.fromJson(response1, JsonArray.class);
        return jsonElements.asList().stream()
                .filter(this::hasTemperature)
                .filter(prueba -> gcArea.getLatmin() < prueba.getAsJsonObject().get("lat").getAsDouble() && prueba.getAsJsonObject().get("lat").getAsDouble() < gcArea.getLatmax())
                .filter(prueba -> gcArea.getLonmin() < prueba.getAsJsonObject().get("lon").getAsDouble() && prueba.getAsJsonObject().get("lon").getAsDouble() < gcArea.getLonmax())
                .map(this::toWeather)
                .toList();
    }

    public Weather toWeather(JsonElement jsonElement) {
        String id = jsonElement.getAsJsonObject().get("idema").getAsString();
        String ubi = jsonElement.getAsJsonObject().get("ubi").getAsString();
        String date = jsonElement.getAsJsonObject().get("fint").getAsString();
        double ta = Double.parseDouble(String.valueOf(jsonElement.getAsJsonObject().get("ta")));
        double tmax = Double.parseDouble(String.valueOf(jsonElement.getAsJsonObject().get("tamax")));
        double tmin = Double.parseDouble(String.valueOf(jsonElement.getAsJsonObject().get("tamin")));
        return new Weather(id, ubi, date, ta, tmax, tmin);
    }

    public boolean hasTemperature(JsonElement jsonElement) {
        return jsonElement.getAsJsonObject().get("ta") != null;
    }
}