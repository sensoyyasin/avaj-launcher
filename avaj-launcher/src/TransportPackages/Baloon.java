package TransportPackages;

import CoordinatesPackage.Coordinates;
import TowerPackage.WeatherTower;

public class Baloon extends Aircraft {

    public Baloon(Long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        WeatherTower weatherTower = new WeatherTower();
        String weather = weatherTower.getWeather(getCoordinates());
        switch (weather) {
            case "SUN":
                getCoordinates().setLongtitude(getCoordinates().getLongitude() + 2);
                getCoordinates().setHeight(getCoordinates().getHeight() + 4);
                System.out.println(this + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                getCoordinates().setHeight(getCoordinates().getHeight() - 5);
                System.out.println(this + ": Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                getCoordinates().setHeight(getCoordinates().getHeight() - 3);
                System.out.println(this + ": It's foggy. I can't see anything!");
                break;
            case "SNOW":
                getCoordinates().setHeight(getCoordinates().getHeight() - 15);
                System.out.println(this + ": It's snowing. We're gonna crash.");
                break;
        }
        if (getCoordinates().getHeight() <= 0) {
            land();
        }
    }
}
