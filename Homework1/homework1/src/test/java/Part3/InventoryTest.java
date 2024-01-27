package Part3;

import edu.iu.mbarrant.Part3.Inventory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private static final String TEST_DATABASE_FILE = "guitars_database.txt";
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        // Set the database file to the test file
        //Inventory.DATABASE_FILE = TEST_DATABASE_FILE;
    }

    //comment this out to view the txt file, I have it running in the background to be able to run multiple tests
    @AfterEach
    public void tearDown() {
        // Delete the test database file after each test
        try {
            Files.deleteIfExists(Paths.get(TEST_DATABASE_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddGuitar1() {
        // Add a guitar to the test database file
        inventory.addGuitar("123456", 999.99, "Builder1", "Model1", "Type1", "BackWood1", "TopWood1");

        // Verify that the guitar information is correctly added to the file
        assertFileContains("123456,999.99,Builder1,Model1,Type1,BackWood1,TopWood1");
    }
    @Test
    public void testAddGuitar2() {
        // Add a guitar to the test database file
        inventory.addGuitar("12334", 69.69, "Builder2", "Model2", "Type2", "BackWood2", "TopWood2");

        // Verify that the guitar information is correctly added to the file
        assertFileContains("12334,69.69,Builder2,Model2,Type2,BackWood2,TopWood2");
    }
    @Test
    public void testAddGuitar3() {
        // Add a guitar to the test database file
        inventory.addGuitar("123444", 509.99, "Builder3", "Model3", "Type3", "BackWood3", "TopWood3");

        // Verify that the guitar information is correctly added to the file
        assertFileContains("123444,509.99,Builder3,Model3,Type3,BackWood3,TopWood3");
    }

    // Helper method to assert that the file contains a specific line
    private void assertFileContains(String expectedLine) {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_DATABASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(expectedLine)) {
                    return; // Found the expected line in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fail("Expected line not found in the file: " + expectedLine);
    }
}
