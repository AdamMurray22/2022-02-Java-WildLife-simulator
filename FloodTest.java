
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * The test class FloodTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FloodTest
{
    private Field field1;
    private Location location1;
    private Flood flood1;

    
    /**
     * Default constructor for test class FloodTest
     */
    public FloodTest()
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
        flood1 = new Flood(field1, location1);
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
    public void testInitialisation()
    {
        Flood flood1 = new Flood(field1, location1);
    }

    @Test
    public void testProgress()
    {
        flood1.progress();
    }

    @Test
    public void testGetUnspawnableLocations()
    {
        assertNotNull(flood1.getUnspawnableLocations(Animal.class));
        assertNull(flood1.getUnspawnableLocations(Plant.class));
        assertNull(flood1.getUnspawnableLocations(Weather.class));
    }

    @Test
    public void testGetRandomSpawnProbability()
    {
        assertEquals(0.03, Flood.getRandomSpawnProbability(), 0.1);
    }

    @Test
    public void testGetAftermathTime()
    {
        assertEquals(3, flood1.getAftermathTime());
    }
}



