import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;
import FlyablePackages.Flyable;
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

    public Flyable newAircraft(String vehicleType, String name, int longitude, int latitude, int height) {
        ft_controller(longitude, latitude, height);

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        int id = Main.getNextId();

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
        if (longitude < 0 || latitude < 0 || height < 0)
            throw new IllegalArgumentException("Longitude, Latitude and height must be non-negative");
        if (height > 100)
            height = 100;
    }
}

public class Main {
    private static int idCounter = 1;

    public static synchronized int getNextId() {
        return idCounter++;
    }

    public static String getColor(String color) {
        switch (color.toLowerCase()) {
            case "red":
                return "\033[0;91m";
            case "yellow":
                return "\033[0;93m";
            case "green":
                return "\033[0;92m";
            case "blue":
                return "\033[0;94m";
            case "reset":
                return "\033[0m";
            default:
                return "";
        }
    }

    public static void main(String[] args) throws IOException {
        // Singleton Instance
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();

        // WeatherTower instance
        WeatherTower weatherTower = new WeatherTower();

        File file = new File("src/scenario.txt");
        if (!file.exists())
            file.createNewFile();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // First Line
            String line = br.readLine();
            int simulationCount = Integer.parseInt(line.trim());

            // Other lines
            List<Flyable> flyables = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");

                System.out.println(getColor("green") + "Creating vehicles..." + getColor("reset"));

                if (parts.length == 5) {
                    Flyable flyable = aircraftFactory.newAircraft(parts[0], parts[1],
                            Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                    flyables.add(flyable);
                } else {
                    System.out.println("Invalid Format");
                }
            }

            // Register aircrafts with WeatherTower
            for (Flyable flyable : flyables) {
                flyable.registerTower(weatherTower);
            }

            // Simulate weather changes and write
            File outputFile = new File("simulation.txt");
            if (!outputFile.exists())
                outputFile.createNewFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                for (int i = 0; i < simulationCount; i++) {
                    weatherTower.changeWeather();
                    for (Flyable flyable : flyables) {
                        // Write the aircraft status to simulation.txt
                        bw.write(flyable.toString() + "\n");
                    }
                    if (i + 1 < simulationCount)
                        bw.write("-------------------------------------\n");
                }
            }

            System.out.println(getColor("red") + "Simulation finished!" + getColor("reset"));
        }
    }
}
