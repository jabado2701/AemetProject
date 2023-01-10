package sensor;

import com.google.gson.JsonElement;
import objects.Weather;

import java.io.IOException;
import java.util.List;

public interface WeatherSensor {

    List<Weather> read() throws IOException;

    Weather toWeather(JsonElement jsonElement);

    boolean hasTemperature(JsonElement jsonElement);

}
