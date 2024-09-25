package VehiclePackages;

import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;
import LogPackages.Log;

public class Helicopter extends Aircraft {

    public Helicopter(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                Log.write(this + ": This is hot.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                Log.write(this + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                Log.write(this + ": It's foggy. I can't see anything.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                Log.write(this + ": My rotor is going to freeze!");
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
