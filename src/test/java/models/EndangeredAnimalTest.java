package models;

import org.junit.jupiter.api.*;

import java.util.List;
import dao.*;
import org.sql2o.*;
import org.sql2o.Connection;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredAnimalTest {
    private static sql2oEndangeredAnimalsDao sql2oEndangeredAnimalsDao;
    private static Connection conn;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oEndangeredAnimalsDao = new sql2oEndangeredAnimalsDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void afterEach() {
        sql2oEndangeredAnimalsDao.clearAllAnimals();
    }

    @AfterAll
    public static void tearDown() {
        conn.createQuery("DELETE FROM animals");
        conn.close();
    }

    @Test
    void newAnimalIsInitialized() {
        EndangeredAnimal testAnimal = setUpAnimal();
        assertTrue(testAnimal instanceof EndangeredAnimal);
    }

    @Test
    void getMethodsWork() {
        EndangeredAnimal testAnimal = setUpAnimal();
        assertEquals("Elephant", testAnimal.getName());
    }

    @Test
    void uniqueVariablesEqualTheConstantsCreated() {
        EndangeredAnimal testAnimal = setUpAnimal();
        assertEquals(testAnimal.getHealth(), EndangeredAnimal.AV_HEALTH);
    }

    @Test
    void normalAnimalIsSavedOnDB() {
        EndangeredAnimal testAnimal1 = setUpAnimal();
        sql2oEndangeredAnimalsDao.save(testAnimal1);
        EndangeredAnimal savedAnimal = sql2oEndangeredAnimalsDao.findById(testAnimal1.getId());
        assertEquals(savedAnimal.getName(), testAnimal1.getName());
    }

    @Test
    void allNormalAnimalsAreReturnedFrom() {
        EndangeredAnimal testAnimal1 = setUpAnimal();
        sql2oEndangeredAnimalsDao.save(testAnimal1);
        EndangeredAnimal testAnimal2 = new EndangeredAnimal("Vulture", "healthy", "adult");
        sql2oEndangeredAnimalsDao.save(testAnimal2);
        List<EndangeredAnimal> savedAnimals = sql2oEndangeredAnimalsDao.returnAll();
        assertTrue(savedAnimals.contains(testAnimal1));
        assertTrue(savedAnimals.contains(testAnimal2));
    }

    @Test
    void exceptionIsThrownFromInvalidInput() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EndangeredAnimal testAnimal = new EndangeredAnimal("Vulture", "medium", "adult");
        });
        assertEquals("Input a valid option for the animals health!", thrown.getMessage());
    }

    @Test
    void exceptionIsThrownFromInvalidInput2() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EndangeredAnimal testAnimal = new EndangeredAnimal("Vulture", "okay", "old");
        });
        assertEquals("Input a valid option for the animals age!", thrown.getMessage());
    }

    public EndangeredAnimal setUpAnimal() {
        EndangeredAnimal testAnimal = new EndangeredAnimal("Elephant", "okay", "young");
        return testAnimal;
    }

}
