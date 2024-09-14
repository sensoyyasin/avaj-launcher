package WeatherTypes;

import CoordinatesPackage.Coordinates;

public class WeatherProvider {
    private static String[] weather = new String[]{"SUN, RAIN, FOG, SNOW"};

    //Singleton instance
    private static WeatherProvider instance;

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(int coordinates){
        //return weather[coordinates]; // degistirilecek.
        return weather[coordinates];
    }
}
