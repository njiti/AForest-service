package wildlifeTracker;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

public class EndangeredTest{
    @Rule
    public DatabaseRules database = new DatabaseRules();
    @Test
    public void endangered_instantiatesCorrectly_true(){
        Endangered testAnimal = new Endangered("lion","okay", "young");
        assertEquals(testAnimal instanceof Endangered, true);
    }
    @Test
    public void endangered_instantiatesWithAnimalName_String(){
        Endangered testAnimal = new Endangered("lion","okay", "young");
        assertEquals("lion", testAnimal.getName());
    }
    @Test
    public void endangered_instantiatesWIthAnimalHealth_String(){
        Endangered testAnimal = new Endangered("lion","okay", "young");
        assertEquals("okay", testAnimal.getHealth());
    }
    @Test
    public void endangered_instantiatesWithAgeOfAnimal_String(){
        Endangered testAnimal = new Endangered("lion","okay", "young");
        assertEquals("young", testAnimal.getAge());
    }
    @Test
    public void save_EndangeredAnimalObjectsIntoDB(){
        Endangered testAnimal = new Endangered("lama","Very Sick", "Adult");
        testAnimal.save();
        assertTrue(Endangered.all().get(0).equals(testAnimal));
    }
    @Test
    public void instance_of_Endangered_animals(){
        Endangered firstAnimal = new Endangered("Zebra","Very Sick", "Adult");
        firstAnimal.save();
        Endangered secondAnimal = new Endangered("monkey","Very Sick", "Adult");
        secondAnimal.save();
        assertEquals(true, Endangered.all().get(0).equals(firstAnimal));
        assertEquals(true, Endangered.all().get(1).equals(secondAnimal));
    }


}
