package weatherbot;

import com.google.gson.Gson;
import weatherbot.model.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherService {
    private final OkHttpClient client = new OkHttpClient();
    //this I take from yandex dev api doc
    private static final String URL = "https://api.weather.yandex.ru/v2/forecast?lat=%f&lon=%f&[lang=ru_RU]";
    private final String token;

    public String getToken() {
        return token;
    }

    public WeatherService(String token) {
        this.token = token;
    }

    //entry param is coordinates, look in URL lat and lon
    public Weather getWeather(double lat, double lon) throws IOException {
        Request request = new Request.Builder()
                .url(String.format(URL, lat, lon))
                .header("X-Yandex-API-Key", this.token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //create mapper from json answer to our java class(Weather)
            Gson mapper = new Gson();
            return mapper.fromJson(response.body().string(), Weather.class);
        }
    }

    public static void main(String[] args) throws IOException {
        WeatherService ws=new WeatherService("e8eac20e-0a2e-4b46-9f9f-27cfcf92d83b");
        System.out.println(ws.getWeather(25.195137,55.27));
    }

    @Override
    public String toString() {
        return "WeatherService{" +
                "token='" + token + '\'' +
                '}';
    }
}
