

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PlantWeatherModifiersTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlantWeatherModifiersTest
{
    /**
     * Default constructor for test class PlantWeatherModifiersTest
     */
    public PlantWeatherModifiersTest()
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
        PlantWeatherModifiers plantWea1 = new PlantWeatherModifiers();
    }

    @Test
    public void setters_test()
    {
        PlantWeatherModifiers plantWea1 = new PlantWeatherModifiers();
        plantWea1.setGrowthRateModifier(2);
        plantWea1.setDead(true);
    }

    @Test
    public void getter_test()
    {
        PlantWeatherModifiers plantWea1 = new PlantWeatherModifiers();
        assertEquals(0, plantWea1.getGrowthRateModifier());
        plantWea1.setGrowthRateModifier(5);
        assertEquals(5, plantWea1.getGrowthRateModifier());
    }

    @Test
    public void isDead_test()
    {
        PlantWeatherModifiers plantWea1 = new PlantWeatherModifiers();
        assertEquals(false, plantWea1.isDead());
    }
}




