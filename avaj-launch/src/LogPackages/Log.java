package LogPackages;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter("simulation.txt", false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void write(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}