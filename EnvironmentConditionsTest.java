

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class EnvironmentConditionsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EnvironmentConditionsTest
{
    /**
     * Default constructor for test class EnvironmentConditionsTest
     */
    public EnvironmentConditionsTest()
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
        EnvironmentConditions environm2 = new EnvironmentConditions();
    }

    

    @Test
    public void getWeather_test()
    {
        EnvironmentConditions environm1 = new EnvironmentConditions();
        assertNotNull(environm1.getWeather());
    }

    @Test
    public void incrementConditions_test()
    {
        EnvironmentConditions environm1 = new EnvironmentConditions();
        environm1.incrementConditions();
    }

    @Test
    public void isDay_test()
    {
        EnvironmentConditions environm1 = new EnvironmentConditions();
        assertEquals(true, environm1.isDay());
    }
}





