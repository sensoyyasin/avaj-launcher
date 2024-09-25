import CoordinatesPackages.Coordinates;
import FlyablePackages.Flyable;
import LogPackages.Log;
import TowerPackages.WeatherTower;
import VehiclePackages.Baloon;
import VehiclePackages.Helicopter;
import VehiclePackages.JetPlane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AircraftFactory {

    // Singleton instance
    private static AircraftFactory instance;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null)
            instance = new AircraftFactory();
        return instance;
    }

    public Flyable newAircraft(String vehicleType, String name, int longitude, int latitude, int height){
        height = ft_controller(longitude, latitude, height);

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        int id = Simulator.getNextId();

        switch (vehicleType.toLowerCase()) {
            case "baloon":
                return new Baloon(id, name, coordinates);
            case "jetplane":
                return new JetPlane(id, name, coordinates);
            case "helicopter":
                return new Helicopter(id, name, coordinates);
            default:
                throw new RuntimeException("Invalid vehicle type: " + vehicleType);
        }
    }

    private int ft_controller(int longitude, int latitude, int height) {
        if (longitude < 0 || latitude < 0 || height < 0)
            throw new IllegalArgumentException("Longitude, Latitude and height must be non-negative");
        if (height > 100)
            height = 100;
        return height;
    }
}

public class Simulator {
    private static int idCounter = 1;

    public static synchronized int getNextId() {
        return idCounter++;
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.exit(1);
        }

        String scenarioFileName = args[0];

        // Singleton Instance
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();

        // WeatherTower instance
        WeatherTower weatherTower = new WeatherTower();

        File file = new File(scenarioFileName);
        if (!file.exists()) {
            System.out.println("Scenario file not found: " + scenarioFileName);
            System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            // First Line : Counter of simulation
            String line = br.readLine();
            int simulationCount = Integer.parseInt(line.trim());

            // Other lines
            List<Flyable> flyables = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");

                if (parts.length == 5) {
                        Flyable flyable = aircraftFactory.newAircraft(parts[0], parts[1],
                                Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                        flyables.add(flyable);
                        flyable.registerTower(weatherTower);
                } else{
                    System.out.println("Invalid Format");
                    System.exit(1);
                }
            }

            // Start the simulation
            for (int i = 0; i < simulationCount; i++)
                weatherTower.changeWeather();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            Log.close();
        }
    }
}
