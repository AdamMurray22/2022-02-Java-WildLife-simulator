

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class EarthquakeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EarthquakeTest
{
    private Field field1;
    private Location location1;
    private Earthquake earthquake1;
    
    /**
     * Default constructor for test class EarthquakeTest
     */
    public EarthquakeTest()
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
        earthquake1 = new Earthquake(field1, location1);
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
        Earthquake earthqua1 = new Earthquake(field1, location1);
    }

    @Test
    public void getRandomSpawnProbability_test()
    {
        assertEquals(0.03, Earthquake.getRandomSpawnProbability());
    }
}


