package TowerPackages;

import CoordinatesPackages.Coordinates;
import WeatherPackages.WeatherProvider;

public class WeatherTower extends Tower {
    private final WeatherProvider weatherProvider;

    public WeatherTower() {
        this.weatherProvider = WeatherProvider.getInstance();
    }

    public String getWeather(Coordinates p_coordinates) {
        return weatherProvider.getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        conditionChanged(); // tüm gözlemcilere güncelleme
    };
}