

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MeteorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MeteorTest
{
    private Field field1;
    private Location location1;
    private Meteor meteor1;
    
    /**
     * Default constructor for test class MeteorTest
     */
    public MeteorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        field1 = new Field(50, 50, null);
        location1 = new Location(5, 5);
        meteor1 = new Meteor(field1, location1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    

    @Test
    public void constructor_test()
    {
        Meteor meteor2 = new Meteor(field1, location1);
    }

    @Test
    public void getAffectedLocations_test()
    {
        assertNotNull(meteor1.getAffectedLocations());
    }

    @Test
    public void getAftermathTime_test()
    {
        assertEquals(5, meteor1.getAftermathTime());
    }
    
    @Test
    public void getRandomSpawnProbability_test()
    {
        assertEquals(0.02, Meteor.getRandomSpawnProbability());
    }
    
    @Test
    public void getUnspawnableLocations_test()
    {
        assertNotNull(meteor1.getUnspawnableLocations(Plant.class));
        assertNull(meteor1.getUnspawnableLocations(Animal.class));
    }
}





