

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AnimalWeatherModifiersTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AnimalWeatherModifiersTest
{
    /**
     * Default constructor for test class AnimalWeatherModifiersTest
     */
    public AnimalWeatherModifiersTest()
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
        AnimalWeatherModifiers animalWe1 = new AnimalWeatherModifiers();
    }

    @Test
    public void setters_test()
    {
        AnimalWeatherModifiers animalWe1 = new AnimalWeatherModifiers();
        animalWe1.setBreedingModifier(2);
        animalWe1.setHuntingModifier(2);
        animalWe1.setMovementModifier(3);
    }

    @Test
    public void getters_test()
    {
        AnimalWeatherModifiers animalWe1 = new AnimalWeatherModifiers();
        assertEquals(0, animalWe1.getBreedingModifier());
        assertEquals(0, animalWe1.getHuntingModifier());
        assertEquals(0, animalWe1.getMovementModifier());
        animalWe1.setBreedingModifier(1);
        assertEquals(1, animalWe1.getBreedingModifier());
        animalWe1.setHuntingModifier(3);
        assertEquals(3, animalWe1.getHuntingModifier());
        animalWe1.setMovementModifier(5);
        assertEquals(5, animalWe1.getMovementModifier());
    }
}



