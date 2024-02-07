package edu.iu.mbarrant.C322Homework3.repository;

import edu.iu.mbarrant.C322Homework3.model.Duck;
import edu.iu.mbarrant.C322Homework3.model.DuckData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DucksRepositoryTest {

        private DucksRepository ducksRepository;

        @BeforeEach
        public void setUp() throws IOException {
            ducksRepository = new DucksRepository();
            ducksRepository.clearRepository();
        }

        @Test
        public void testAddDuck() throws IOException {
            //DucksRepository ducksRepository = new DucksRepository();

            DuckData duckData = new DuckData(1, Duck.Type.MALLARD);
            boolean result = ducksRepository.add(duckData);

            assertTrue(result);
            // Add additional assertions if needed
        }

        @Test
        public void testGetDuck() throws IOException {
            //DucksRepository ducksRepository = new DucksRepository();

            DuckData duckData = new DuckData(1, Duck.Type.MALLARD);
            ducksRepository.add(duckData);

            DuckData retrievedDuck = ducksRepository.find(1);

            assertNotNull(retrievedDuck);
            assertEquals(duckData.id(), retrievedDuck.id()); // Use id() instead of getId()
            assertEquals(duckData.type(), retrievedDuck.type()); // Use type() instead of getType()
    }

        @Test
        public void testGetAllDucks() throws IOException, IOException {
            //DucksRepository ducksRepository = new DucksRepository();

            DuckData duckData1 = new DuckData(1, Duck.Type.MALLARD);
            DuckData duckData2 = new DuckData(2, Duck.Type.RUBBER_DUCK);

            ducksRepository.add(duckData1);
            ducksRepository.add(duckData2);

            List<DuckData> allDucks = ducksRepository.findAll();

            assertEquals(2, allDucks.size());
            assertTrue(allDucks.contains(duckData1));
            assertTrue(allDucks.contains(duckData2));
        }



        @Test
        public void testSearchDucks() throws IOException {
            //DucksRepository ducksRepository = new DucksRepository();

            DuckData duckData1 = new DuckData(1, Duck.Type.MALLARD);
            DuckData duckData2 = new DuckData(2, Duck.Type.RUBBER_DUCK);
            DuckData duckData3 = new DuckData(3, Duck.Type.MALLARD);

            ducksRepository.add(duckData1);
            ducksRepository.add(duckData2);
            ducksRepository.add(duckData3);

            List<DuckData> mallardDucks = ducksRepository.search("MALLARD");

            assertEquals(2, mallardDucks.size());
            assertTrue(mallardDucks.contains(duckData1));
            assertFalse(mallardDucks.contains(duckData2)); // Ensure only MALLARD ducks are retrieved
            assertTrue(mallardDucks.contains(duckData3));
        }


    }
