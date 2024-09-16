import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;
import TowerPackages.WeatherTower;
import VehiclePackages.Baloon;
import VehiclePackages.Helicopter;
import VehiclePackages.JetPlane;

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

    public Aircraft createVehicle(String vehicleType, Long id, String name, int longitude, int latitude, int height){
        ft_controller(longitude, latitude, height);
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (vehicleType.toLowerCase()) {
            case "baloon":
                return new Baloon(id, name, coordinates);
            case "jetplane":
                return new JetPlane(id, name, coordinates);
            case "helicopter":
                return new Helicopter(id, name, coordinates);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
    }

    private void ft_controller(int longitude, int latitude, int height) {
        if (height < 0 || height > 100)
            System.out.println("Height must be between (0 and 100) : " + height);
            //throw new IllegalArgumentException("Height must be between 0 and 100." + height);
        else if (longitude < 0 || latitude < 0)
            System.out.println("Longitude must be between 0 and 100");
            //throw new IllegalArgumentException("Longitude and latitude must be positive.");
    }
}

class ShellColorCodes {
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;92m";
    public static final String YELLOW = "\033[0;93m";
    public static final String RED = "\033[0;91m";
}

public class Main {
    public static void main(String[] args) {
        //Singleton Instance
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();

        //WeatherTower instance
        WeatherTower weatherTower = new WeatherTower();

        //Create Vehicles
        System.out.println(ShellColorCodes.GREEN + "Creating vehicles..." + ShellColorCodes.RESET);
        Aircraft b1 = aircraftFactory.createVehicle("baloon", 1L, "B1", 2, 3, 20);
        Aircraft b2 = aircraftFactory.createVehicle("baloon", 2L, "B2", 1, 8, 66);
        Aircraft j1 = aircraftFactory.createVehicle("jetplane", 3L, "J1", 23, 44, 32);
        Aircraft h1 = aircraftFactory.createVehicle("helicopter", 4L, "H1", 654, 33, 20);
        Aircraft h2 = aircraftFactory.createVehicle("helicopter", 5L, "H2", 22, 33, 44);
        Aircraft h3 = aircraftFactory.createVehicle("helicopter", 6L, "H3", 98, 68, 99);
        Aircraft b3 = aircraftFactory.createVehicle("baloon", 7L, "B3", 102, 22, 34);
        Aircraft j2 = aircraftFactory.createVehicle("jetplane", 8L, "J2", 11, 99, 768);
        Aircraft h4 = aircraftFactory.createVehicle("helicopter", 9L, "H4", 223, 23, 54);


        // Register vehicles with the WeatherTower
        System.out.println(ShellColorCodes.GREEN + "Registering vehicles with WeatherTower..." + ShellColorCodes.RESET);
        b1.registerTower(weatherTower);
        b2.registerTower(weatherTower);
        j1.registerTower(weatherTower);
        h1.registerTower(weatherTower);
        h2.registerTower(weatherTower);
        h3.registerTower(weatherTower);
        b3.registerTower(weatherTower);
        j2.registerTower(weatherTower);
        h4.registerTower(weatherTower);

        // Simulate weather conditions change
        System.out.println(ShellColorCodes.YELLOW + "Simulating weather conditions change..." + ShellColorCodes.RESET);
        weatherTower.conditionChanged();

    }
}