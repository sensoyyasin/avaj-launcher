package TransportPackages;

import CoordinatesPackage.Coordinates;
import TowerPackage.WeatherTower;

public class Helicopter extends Aircraft {

    public Helicopter(Long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        WeatherTower weatherTower = new WeatherTower();
        String weather = weatherTower.getWeather(getCoordinates());
        switch (weather) {
            case "SUN":
                getCoordinates().setLongtitude(getCoordinates().getLongitude() + 10);
                getCoordinates().setHeight(getCoordinates().getHeight() + 2);
                System.out.println(this + " It's sunny! We are flying higher.");
                break;
            case "RAIN":
                getCoordinates().setLongtitude(getCoordinates().getLongitude() - 5);
                System.out.println(this + " It's raining! Adjusting altitude.");
                break;
            case "FOG":
                getCoordinates().setLongtitude(getCoordinates().getLongitude() - 1);
                System.out.println(this + " Foggy conditions. Flying cautiously.");
                break;
            case "SNOW":
                getCoordinates().setHeight(getCoordinates().getHeight() - 12);
                System.out.println(this + " Snowy weather. Decreasing altitude.");
                break;
        }
        if (getCoordinates().getHeight() <= 0) {
            land();
        }
    }
}