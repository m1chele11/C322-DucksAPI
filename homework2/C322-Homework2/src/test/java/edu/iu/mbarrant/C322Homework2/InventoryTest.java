package edu.iu.mbarrant.C322Homework2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {


    private static final String TEST_DATABASE_FILE = "guitars_database.txt";
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        // Set the database file to the test file
        //Inventory.DATABASE_FILE = guitars_database.txt;
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
        Guitar guitarToAdd = new Guitar("123456", 999.99, "Builder1", "Model1", "Type1", "BackWood1", "TopWood1");
        inventory.addGuitar(guitarToAdd);

        // Verify that the guitar information is correctly added to the file
        assertAll(
                () -> assertTrue(Files.exists(Paths.get(TEST_DATABASE_FILE))),
                () -> assertTrue(Files.isRegularFile(Paths.get(TEST_DATABASE_FILE))),
                () -> assertTrue(Files.isReadable(Paths.get(TEST_DATABASE_FILE))),
                () -> assertTrue(Files.isWritable(Paths.get(TEST_DATABASE_FILE))),
                () -> assertEquals(1, Files.readAllLines(Paths.get(TEST_DATABASE_FILE)).size()),
                () -> assertEquals("123456,999.99,Builder1,Model1,Type1,BackWood1,TopWood1",
                        Files.readAllLines(Paths.get(TEST_DATABASE_FILE)).get(0))
        );
    }

    @Test
    public void testAddGuitar2(){
        // Add a guitar to the test database file
        Guitar guitarToAdd = new Guitar("223344", 69.99, "Builder2", "Model2", "Type2", "BackWood2", "TopWood2");
        inventory.addGuitar(guitarToAdd);
        Guitar guitarToAdd2 = new Guitar("122334", 55.23, "Builder3", "Model3", "Type3", "BackWood3", "TopWood3");
        inventory.addGuitar(guitarToAdd2);

        // Verify that the guitar information is correctly added to the file
        assertAll(
                () -> assertTrue(Files.exists(Paths.get(TEST_DATABASE_FILE))),
                () -> assertTrue(Files.isRegularFile(Paths.get(TEST_DATABASE_FILE))),
                () -> assertTrue(Files.isReadable(Paths.get(TEST_DATABASE_FILE))),
                () -> assertTrue(Files.isWritable(Paths.get(TEST_DATABASE_FILE))),
                () -> assertEquals(2, Files.readAllLines(Paths.get(TEST_DATABASE_FILE)).size()),
                () -> assertEquals("223344,69.99,Builder2,Model2,Type2,BackWood2,TopWood2",
                        Files.readAllLines(Paths.get(TEST_DATABASE_FILE)).get(0)),
                () -> assertEquals("122334,55.23,Builder3,Model3,Type3,BackWood3,TopWood3",
                        Files.readAllLines(Paths.get(TEST_DATABASE_FILE)).get(1))
        );
    }

    @Test
    void testGetGuitar() {
        // Arrange
        Guitar guitarToAdd = new Guitar("123", 1000.0, "Fender", "Stratocaster", "Electric", "Alder", "Maple");
        inventory.addGuitar(guitarToAdd);

        // Act
        Guitar retrievedGuitar = inventory.getGuitar("123");

        // Assert
        assertNotNull(retrievedGuitar);
        assertEquals("123", retrievedGuitar.getSerialNumber());
    }

    @Test
    void testSearch() {
        // Arrange
        Guitar guitar1 = new Guitar("123", 1000.0, "Fender", "Stratocaster", "Electric", "Alder", "Maple");
        Guitar guitar2 = new Guitar("456", 1200.0, "Gibson", "Les Paul", "Electric", "Mahogany", "Maple");
        inventory.addGuitar(guitar1);
        inventory.addGuitar(guitar2);

        Guitar searchCriteria = new Guitar(null, 0.0, "Fender", null, null, null, null);

        // Act
        List<Guitar> searchResult = inventory.search(searchCriteria);

        // Assert
        assertEquals(1, searchResult.size());
        assertEquals("123", searchResult.get(0).getSerialNumber());
    }
}