package edu.iu.mbarrant.C322Homework2;


import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class Inventory {

    public static final String DATABASE_FILE = "guitars_database.txt" ;

    public void addGuitar(Guitar guitar) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
            String guitarInfo = String.format("%s,%.2f,%s,%s,%s,%s,%s%n",
                    guitar.getSerialNumber(), guitar.getPrice(), guitar.getBuilder(),
                    guitar.getModel(), guitar.getType(), guitar.getBackWood(), guitar.getTopWood());
            writer.write(guitarInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Guitar getGuitar(String serialNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[0].equals(serialNumber)) {
                    // Found the guitar by serial number
                    return createGuitarFromParts(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Guitar not found
        return null;
    }

    public List<Guitar> search(Guitar searchGuitar) {
        List<Guitar> matchingGuitars = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    Guitar guitar = createGuitarFromParts(parts);
                    // Check if the current guitar matches the search criteria
                    if (matchesSearchCriteria(guitar, searchGuitar)) {
                        matchingGuitars.add(guitar);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingGuitars;
    }

    private Guitar createGuitarFromParts(String[] parts) {
        return new Guitar(
                parts[0],                    // Serial Number
                Double.parseDouble(parts[1]), // Price
                parts[2],                    // Builder
                parts[3],                    // Model
                parts[4],                    // Type
                parts[5],                    // Back Wood
                parts[6]                     // Top Wood
        );
    }

    private boolean matchesSearchCriteria(Guitar guitar, Guitar searchGuitar) {
        // Implement the logic to check if the guitar matches the search criteria
        // You may need to handle cases where the search criteria properties are null or empty
        // For simplicity, let's assume that null or empty values are ignored in the search
        return (searchGuitar.getBuilder() == null || searchGuitar.getBuilder().equals(guitar.getBuilder()))
                && (searchGuitar.getModel() == null || searchGuitar.getModel().equals(guitar.getModel()))
                && (searchGuitar.getType() == null || searchGuitar.getType().equals(guitar.getType()))
                && (searchGuitar.getBackWood() == null || searchGuitar.getBackWood().equals(guitar.getBackWood()))
                && (searchGuitar.getTopWood() == null || searchGuitar.getTopWood().equals(guitar.getTopWood()))
                && (searchGuitar.getPrice() == 0.0 || searchGuitar.getPrice() == guitar.getPrice());
    }
}
