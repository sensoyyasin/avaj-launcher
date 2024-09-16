package WeatherPackages;

import CoordinatesPackages.Coordinates;

public class WeatherProvider {
    private static WeatherProvider instance;
    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        return determineWeather(p_coordinates.getHeight(), p_coordinates.getLatitude());
    }

    private String determineWeather(int height, int latitude) {
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
