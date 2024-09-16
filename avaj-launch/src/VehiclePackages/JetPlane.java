package VehiclePackages;

import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;

public class JetPlane extends Aircraft {

    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, Math.min(coordinates.getHeight() + 2, 100));
                System.out.println("JetPlane#" + name + "(" + id + "): This is a great day for a flight!");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                System.out.println("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                System.out.println("JetPlane#" + name + "(" + id + "): I can barely see through this fog.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), Math.max(coordinates.getHeight() - 7, 0));
                System.out.println("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
                break;
        }
        if (coordinates.getHeight() == 0) {
            System.out.println("JetPlane#" + name + "(" + id + ") landing.");
            unregisterTower();
        }
    }
}
