package models;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import dao.*;
import org.sql2o.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NormalAnimalTest {
    private static sql2oNormalAnimalDao sql2oNormalAnimalDao;
    private static Connection conn;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oNormalAnimalDao = new sql2oNormalAnimalDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void afterEach() {
        sql2oNormalAnimalDao.clearAllAnimals();
    }

    @AfterAll
    public static void tearDown() {
        conn.createQuery("DELETE FROM animals");
        conn.close();
    }

    @Test
    void newAnimalIsInitialized() {
        NormalAnimal testAnimal = setUpAnimal();
        assertTrue(testAnimal instanceof NormalAnimal);
    }

    @Test
    void getMethodsWork() {
        NormalAnimal testAnimal = setUpAnimal();
        assertEquals("Lion", testAnimal.getName());
    }

    @Test
    void equalsMethodWorks() {
        NormalAnimal testAnimal1 = setUpAnimal();
        NormalAnimal testAnimal2 = setUpAnimal();
        assertTrue(testAnimal1.equals(testAnimal2));

    }

    @Test
    void normalAnimalIsSavedOnDB() {
        NormalAnimal testAnimal1 = setUpAnimal();
        sql2oNormalAnimalDao.save(testAnimal1);
        NormalAnimal savedAnimal = sql2oNormalAnimalDao.findById(testAnimal1.getId());
        assertEquals(savedAnimal.getName(), testAnimal1.getName());
    }

    @Test
    void allNormalAnimalsAreReturnedFrom() {
        NormalAnimal testAnimal1 = setUpAnimal();
        sql2oNormalAnimalDao.save(testAnimal1);
        NormalAnimal testAnimal2 = new NormalAnimal("Zebra");
        sql2oNormalAnimalDao.save(testAnimal2);
        List<NormalAnimal> savedAnimals = sql2oNormalAnimalDao.returnAll();
        assertTrue(savedAnimals.contains(testAnimal1));
        assertTrue(savedAnimals.contains(testAnimal2));
    }

    public NormalAnimal setUpAnimal() {
        NormalAnimal testAnimal = new NormalAnimal("Lion");
        return testAnimal;
    }
}

class NormalAnimalsTest {
    private static sql2oNormalAnimalDao sql2oNormalAnimalDao;
    private static Connection conn;

    @BeforeAll
    public static void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        sql2oNormalAnimalDao = new sql2oNormalAnimalDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    public void afterEach() {
        sql2oNormalAnimalDao.clearAllAnimals();
    }

    @AfterAll
    public static void tearDown() {
        conn.createQuery("DELETE FROM animals");
        conn.close();
    }

    @Test
    void newAnimalIsInitialized() {
        NormalAnimal testAnimal = setUpAnimal();
        assertTrue(testAnimal instanceof NormalAnimal);
    }

    @Test
    void getMethodsWork() {
        NormalAnimal testAnimal = setUpAnimal();
        assertEquals("Lion", testAnimal.getName());
    }

    @Test
    void equalsMethodWorks() {
        NormalAnimal testAnimal1 = setUpAnimal();
        NormalAnimal testAnimal2 = setUpAnimal();
        assertTrue(testAnimal1.equals(testAnimal2));

    }

    @Test
    void normalAnimalIsSavedOnDB() {
        NormalAnimal testAnimal1 = setUpAnimal();
        sql2oNormalAnimalDao.save(testAnimal1);
        NormalAnimal savedAnimal = sql2oNormalAnimalDao.findById(testAnimal1.getId());
        assertEquals(savedAnimal.getName(), testAnimal1.getName());
    }

    @Test
    void allNormalAnimalsAreReturnedFrom() {
        NormalAnimal testAnimal1 = setUpAnimal();
        sql2oNormalAnimalDao.save(testAnimal1);
        NormalAnimal testAnimal2 = new NormalAnimal("Zebra");
        sql2oNormalAnimalDao.save(testAnimal2);
        List<NormalAnimal> savedAnimals = sql2oNormalAnimalDao.returnAll();
        assertTrue(savedAnimals.contains(testAnimal1));
        assertTrue(savedAnimals.contains(testAnimal2));
    }

    public NormalAnimal setUpAnimal() {
        NormalAnimal testAnimal = new NormalAnimal("Lion");
        return testAnimal;
    }
}
