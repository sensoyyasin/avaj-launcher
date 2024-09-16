package FlyablePackages;

import TowerPackages.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();
    public abstract String getType();
    public abstract String getName();
    public abstract long getId();

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        weatherTower.register(this);
    }

    public void unregisterTower() {
        if (weatherTower != null) {
            weatherTower.unregister(this);
        }
    }
}
