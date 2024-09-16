package VehiclePackages;

import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;

public class Helicopter extends Aircraft {

    public Helicopter(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), Math.min(coordinates.getHeight() + 2, 100));
                System.out.println("Helicopter#" + name + "(" + id + "): This is hot.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                System.out.println("Helicopter#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                System.out.println("Helicopter#" + name + "(" + id + "): It's foggy. I can't see anything.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), Math.max(coordinates.getHeight() - 12, 0));
                System.out.println("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
                break;
        }
        if (coordinates.getHeight() == 0) {
            System.out.println("Helicopter#" + name + "(" + id + ") landing.");
            unregisterTower();
        }
    }
}
