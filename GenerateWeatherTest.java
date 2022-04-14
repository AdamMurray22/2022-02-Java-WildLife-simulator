

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GenerateWeatherTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GenerateWeatherTest
{
    /**
     * Default constructor for test class GenerateWeatherTest
     */
    public GenerateWeatherTest()
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
        GenerateWeather generate2 = new GenerateWeather();
    }

    

    @Test
    public void generation_test()
    {
        GenerateWeather generate1 = new GenerateWeather();
        assertNotNull(generate1.determineWeather());
    }
}



