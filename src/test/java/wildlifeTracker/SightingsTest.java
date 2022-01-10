package wildlifeTracker;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.Timestamp;

public class SightingsTest {
    @Rule
    public DatabaseRules database = new DatabaseRules();
    @Test
    public void sigtingsInstantiates(){
            Sightings testSighting = new Sightings("ngong", "Joe",1);
        assertEquals(testSighting instanceof Sightings, true);
    }
    @Test
    public void getLocationtring(){
        Sightings testSighting = new Sightings("karura", "joe",1);
        assertEquals("karura", testSighting.getLocation());
    }

    @Test
    public void getAnimalIdLLocation_int() {
        Sightings testSighting = new Sightings("karura", "joe", 1);
        assertEquals(1, testSighting.getAnimalId());
    }
    @Test
    public void equals_returnsTrueIfSightingsObjectsAreTrue_true(){
        Sightings firstSighting = new Sightings("karura", "joe", 1);
        Sightings secondSighting = new Sightings("karura", "joe", 1);
        assertTrue(firstSighting.equals(secondSighting));
    }
    @Test
    public void save_savesSightingsObjectsIntoDatabase(){
        Sightings testSighting = new Sightings("karura", "joe", 1);
        testSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(testSighting));
    }

    @Test
    public void save_assignsIdToSavedObject(){
        Sightings testSighting = new Sightings("karura", "joe", 1);
        testSighting.save();
        Sightings savedSighting = Sightings.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }


}