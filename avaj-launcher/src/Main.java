import CoordinatesPackage.Coordinates;
import TransportPackages.Aircraft;
import TransportPackages.Baloon;
import TransportPackages.Helicopter;
import TransportPackages.JetPlane;

import java.io.*;

// Factory Method Pattern
class AircraftFactory {

    //Singleton instance
    private static AircraftFactory instance;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }

    public Aircraft createVehicle(String vehicleType, Long id, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (vehicleType.toLowerCase()) {
            case "baloon":
                return new Baloon(id, name, coordinates);
            case "jetplane":
                return new JetPlane(id, name, coordinates);
            case "helicopter":
                return new Helicopter(id ,name, coordinates);
            default:
                throw new IllegalArgumentException("Invalid Vehicle Type: " + vehicleType);
        }
    }
}

class ShellColorCodes {
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;92m";
    public static final String YELLOW = "\033[0;93m";
    public static final String RED = "\033[0;91m";
}

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(ShellColorCodes.YELLOW + "Creating objects..." + ShellColorCodes.RESET);
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();

        Aircraft baloon1 = aircraftFactory.createVehicle("Baloon", 2L, "B1", 10, 20, 30);
        Aircraft baloon2 = aircraftFactory.createVehicle("Baloon", 1L, "B2", 50, 60, 70);
        Aircraft jetPlane = aircraftFactory.createVehicle("JetPlane", 23L, "J1", 100, 200, 300);
        Aircraft helicopter = aircraftFactory.createVehicle("Helicopter", 30L, "H1", 40, 50, 60);

        // Print the details of each aircraft
        System.out.println(baloon1);
        System.out.println(baloon2);
        System.out.println(jetPlane);
        System.out.println(helicopter);

        System.out.println("------------------------");

        // Test updateConditions
        baloon1.updateConditions(); // Update conditions to see the weather
        baloon2.updateConditions();
        jetPlane.updateConditions();
        helicopter.updateConditions();

        System.out.println(ShellColorCodes.RED + "End of the Main program..." + ShellColorCodes.RESET);
    }
}