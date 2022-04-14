

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LocationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LocationTest
{
    private Location location1;

    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
        location1 = new Location(33, 33);
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
        Location location2 = new Location(38, 38);
    }

    @Test
    public void equality_test()
    {
        Location location2 = new Location(80, 80);
        assertEquals(true, location1.equals(location1));
        assertEquals(false, location1.equals(location2));
    }

    @Test
    public void getters_test()
    {
        assertEquals(33, location1.getCol());
        assertEquals(33, location1.getRow());
    }
    
    @Test
    public void toString_test()
    {
        assertEquals("33,33",location1.toString());
    }
}



