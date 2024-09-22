package VehiclePackages;

import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;

public class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions(){
        String weather = weatherTower.getWeather(coordinates);
        switch(weather){
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), Math.min(coordinates.getHeight() + 4, 100));
                System.out.println(this + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), Math.max(coordinates.getHeight() - 5, 0));
                System.out.println(this + ": Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), Math.max(coordinates.getHeight() - 3, 0));
                System.out.println(this + ": It's foggy. I can't see anything.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), Math.max(coordinates.getHeight() - 15, 0));
                System.out.println(this + ": It's snowing. We're gonna crash.");
                break;
        }
        if (coordinates.getHeight() == 0) {
            System.out.println(this + ": landing.");
            unregisterTower();
        }
    }
}