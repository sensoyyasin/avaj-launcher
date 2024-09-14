package TransportPackages;

import CoordinatesPackage.Coordinates;
import TowerPackage.WeatherTower;

public class JetPlane extends Aircraft {

    public JetPlane(Long id, String name, Coordinates coordinates) {
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
                System.out.println(this + " The sun is shining bright!");
                break;
            case "RAIN":
                getCoordinates().setLongtitude(getCoordinates().getLongitude() - 5);
                System.out.println(this + " It's raining. Visibility is low.");
                break;
            case "FOG":
                getCoordinates().setLongtitude(getCoordinates().getLongitude() - 3);
                System.out.println(this + " Foggy weather. Slow descent.");
                break;
            case "SNOW":
                getCoordinates().setHeight(getCoordinates().getHeight() - 7);
                System.out.println(this + " Snowstorm! Reducing altitude.");
                break;
        }
        if (getCoordinates().getHeight() <= 0) {
            land();
        }
    }
}
