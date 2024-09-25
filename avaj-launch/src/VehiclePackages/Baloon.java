package VehiclePackages;

import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;
import LogPackages.Log;

public class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions(){
        String weather = weatherTower.getWeather(coordinates);
        switch(weather){
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                Log.write(this + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                Log.write(this + ": Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                Log.write(this + ": It's foggy. I can't see anything.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                Log.write(this + ": It's snowing. We're gonna crash.");
                break;
        }
        if (coordinates.getHeight() <= 0) {
            Log.write(this + ": landing.");
            unregisterTower();
        }
        else if (coordinates.getHeight() >= 100) {
            Log.write("Height must be 0-100");
            unregisterTower();
        }
    }
}