package Part3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Inventory {

    public static final String DATABASE_FILE = "guitars_database.txt" ;

    public void addGuitar(String serialNumber, double price, String builder, String model, String type, String backWood, String topWood) {
        Guitar newGuitar = new Guitar(serialNumber, price);
        newGuitar.builder = builder;
        newGuitar.model = model;
        newGuitar.type = type;
        newGuitar.backWood = backWood;
        newGuitar.topWood = topWood;

        // Append the guitar information to the "guitars_database.txt" file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
            // Format the guitar information and write to the file
            String guitarInfo = String.format("%s,%.2f,%s,%s,%s,%s,%s%n",
                    serialNumber, price, builder, model, type, backWood, topWood);
            writer.write(guitarInfo);
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    public Guitar getGuitar(String serialNumber) {
        // Implement the logic to get a guitar by serial number
        return null;
    }

    public Guitar search(Guitar searchGuitar) {
        // Implement the logic to search for a guitar
        return null;
    }
}
