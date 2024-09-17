import AircraftPackages.Aircraft;
import CoordinatesPackages.Coordinates;
import TowerPackages.WeatherTower;
import VehiclePackages.Baloon;
import VehiclePackages.Helicopter;
import VehiclePackages.JetPlane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AircraftFactory {

    //Singleton instance
    private static AircraftFactory instance;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null)
            instance = new AircraftFactory();
        return instance;
    }

    public Aircraft newAircraft(String vehicleType, String name, int longitude, int latitude, int height){
        ft_controller(longitude, latitude, height);

        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        int id = IdGenerator.getNextId();

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
            throw new IllegalArgumentException("Longitude and Latitude and height must be non-negative");
        if (height > 100)
            height = 100;
    }
}

class IdGenerator {
    private static int idCounter = 1;

    public synchronized static int getNextId() {
        return idCounter++;
    }
}

class ShellColorCodes {
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;92m";
    public static final String YELLOW = "\033[0;93m";
    public static final String BLUE = "\033[0;94m";
    public static final String RED = "\033[0;91m";
}

public class Main {
    public static void main(String[] args) throws IOException {
        //Singleton Instance
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();

        //WeatherTower instance
        WeatherTower weatherTower = new WeatherTower();


        File file = new File("src/scenario.txt");
        if (!file.exists())
            file.createNewFile();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // First Line
        String line = br.readLine();
        int simulationCount = Integer.parseInt(line.trim());

        // Other lines
        List<Aircraft> aircrafts = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s+");

            System.out.println(ShellColorCodes.GREEN + "Creating vehicles..." + ShellColorCodes.RESET);

            if (parts.length == 5) {
                Aircraft aircraft = aircraftFactory.newAircraft(parts[0], parts[1],
                        Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                aircrafts.add(aircraft);
            } else {
                System.out.println("Invalid Format");
            }
        }
        br.close();

        // Register aircrafts with WeatherTower
        System.out.println(ShellColorCodes.YELLOW + "Registering vehicles with WeatherTower..." + ShellColorCodes.RESET);
        for (int i = 0; i < aircrafts.size(); i++) {
            Aircraft aircraft = aircrafts.get(i);
            aircraft.registerTower(weatherTower);
        }

        // Simulate weather changes and write
        System.out.println(ShellColorCodes.BLUE + "Simulating weather conditions change..." + ShellColorCodes.RESET);
        File outputFile = new File("simulation.txt");
        if (!outputFile.exists())
            outputFile.createNewFile();

        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < simulationCount; i++) {
            weatherTower.changeWeather();
            for (Aircraft aircraft : aircrafts) {
                // Write the aircraft status to scenario.txt
                bw.write(aircraft.toString() + "\n");
            }
            if (i + 1 < simulationCount)
                bw.write("-------------------------------------\n");
            bw.flush();
        }

        bw.close();
        System.out.println(ShellColorCodes.RED + "Simulation finished!" + ShellColorCodes.RESET);

        //Create Vehicles
        /*System.out.println(ShellColorCodes.GREEN + "Creating vehicles..." + ShellColorCodes.RESET);
        Aircraft b1 = aircraftFactory.newAircraft("baloon", 1L, "B1", 2, 3, 20);
        Aircraft b2 = aircraftFactory.newAircraft("baloon", 2L, "B2", 1, 8, 66);
        Aircraft j1 = aircraftFactory.newAircraft("jetplane", 3L, "J1", 23, 44, 32);
        Aircraft h1 = aircraftFactory.newAircraft("helicopter", 4L, "H1", 654, 33, 20);
        Aircraft h2 = aircraftFactory.newAircraft("helicopter", 5L, "H2", 22, 33, 44);
        Aircraft h3 = aircraftFactory.newAircraft("helicopter", 6L, "H3", 98, 68, 99);
        Aircraft b3 = aircraftFactory.newAircraft("baloon", 7L, "B3", 102, 22, 34);
        Aircraft j2 = aircraftFactory.newAircraft("jetplane", 8L, "J2", 11, 99, 768);
        Aircraft h4 = aircraftFactory.newAircraft("helicopter", 9L, "H4", 223, 23, 54);


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
        weatherTower.changeWeather();
        System.out.println(ShellColorCodes.RED + "Simulating is over..." + ShellColorCodes.RESET); */
    }
}