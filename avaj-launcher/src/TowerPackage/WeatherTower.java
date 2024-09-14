package TowerPackage;

import CoordinatesPackage.Coordinates;

public class WeatherTower {
    private static final String[] WEATHER_TYPES = {"SUN", "RAIN", "FOG", "SNOW"};

    public String getWeather(Coordinates coordinates) {
        int height = coordinates.getHeight();

        if (height > 100)
            return WEATHER_TYPES[0];
        else if (height > 50)
            return WEATHER_TYPES[1];
        else if (height > 25)
            return WEATHER_TYPES[2];
        else
            return WEATHER_TYPES[3];
    }
}
