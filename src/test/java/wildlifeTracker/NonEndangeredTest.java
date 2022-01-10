
package wildlifeTracker;
import org.junit.*;
import static org.junit.Assert.*;


public class NonEndangeredTest {
    @Rule
    public DatabaseRules database = new DatabaseRules();
    @Test
    public void NonEndangered_Instantiates(){
        NonEndangered testanimal = new NonEndangered("lama");
        assertEquals(testanimal instanceof NonEndangered, true);
    }
    @Test
    public void NonEndangered_instantiatesWithName_String(){
        NonEndangered testanimal = new NonEndangered("lama");
        assertEquals("lama", testanimal.getName());
    }

    @Test
    public void save_savesNonEndangeredAnimalsIntoDatabase(){
        NonEndangered testanimal = new NonEndangered("lama");
        testanimal.save();
        assertTrue(NonEndangered.all().get(0).equals(testanimal));
    }
    @Test

    @Test
    public void \AllInstancesOfNonEndangeredAnimals(){
        NonEndangered firstAnimal = new NonEndangered("lama");
        firstAnimal.save();
        NonEndangered secondAnimal = new NonEndangered("girrafe");
        secondAnimal.save();
        assertEquals(true, NonEndangered.all().get(0).equals(firstAnimal));
        assertEquals(true, NonEndangered.all().get(1).equals(secondAnimal));
    }
    @Test
    public void returnsNonEndangeredAnimalsWithSameId_firstAnimal(){
        NonEndangered firstAnimal = new NonEndangered("lama");
        firstAnimal.save();
        NonEndangered secondAnimal = new NonEndangered("girraffe");
        secondAnimal.save();
        assertEquals(NonEndangered.find(firstAnimal.getId()), firstAnimal);
    }
}
