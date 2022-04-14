

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class WeatherTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WeatherTest
{
    /**
     * Default constructor for test class WeatherTest
     */
    public WeatherTest()
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
    public void testAnimalWeatherAction()
    {
        Weather weather1 = new Weather(WeatherEnum.Sunny);
        assertNotNull(weather1.animalWeatherAction(AnimalEnum.Bear));
    }
    
    @Test
    public void testPlantWeatherAction()
    {
        Weather weather1 = new Weather(WeatherEnum.Sunny);
        assertNotNull(weather1.plantWeatherAction(PlantEnum.Grass));
    }
}


