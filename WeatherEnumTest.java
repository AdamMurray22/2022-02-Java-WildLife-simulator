

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SunnyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WeatherEnumTest
{
    /**
     * Default constructor for test class SunnyTest
     */
    public WeatherEnumTest()
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
    }

    @Test
    public void getters_test()
    {
        assertNotNull(WeatherEnum.Sunny.getBearEnvironmentModifier());
        assertNotNull(WeatherEnum.Sunny.getChickenEnvironmentModifier());
        assertNotNull(WeatherEnum.Sunny.getCornEnvironmentModifier());
        assertNotNull(WeatherEnum.Sunny.getGrassEnvironmentModifier());
        assertNotNull(WeatherEnum.Sunny.getSheepEnvironmentModifier());
        assertNotNull(WeatherEnum.Sunny.getTigerEnvironmentModifier());
        assertNotNull(WeatherEnum.Sunny.getWormEnvironmentModifier());
    }
}


