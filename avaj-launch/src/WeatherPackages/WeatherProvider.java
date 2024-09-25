package WeatherPackages;

import CoordinatesPackages.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int height = p_coordinates.getHeight();
        int latitude = p_coordinates.getLatitude();
        String new_weather;

        if (height < 10) {
            if (latitude < 23)
                new_weather = weather[0]; // SUN
            else if (latitude < 66)
                new_weather = weather[2]; // FOG
            else
                new_weather = weather[1]; // RAIN
        } else if (height < 50) {
            if (latitude < 23)
                new_weather = weather[1]; // RAIN
            else if (latitude < 66)
                new_weather = weather[2]; // FOG
            else
                new_weather = weather[3]; // SNOW
        } else
            new_weather = weather[3]; // SNOW
        return new_weather;
    }
}
