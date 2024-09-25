package VehiclePackages;

import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;
import LogPackages.Log;

public class JetPlane extends Aircraft{

    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                Log.write(this + ": This is a great day for a flight!");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                Log.write(this + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                Log.write(this + ": I can barely see through this fog.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                Log.write(this + ": OMG! Winter is coming!");
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
