package TowerPackages;

import CoordinatesPackages.Coordinates;
import WeatherPackages.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        conditionChanged(); // tüm gözlemcilere güncelleme
    };
}