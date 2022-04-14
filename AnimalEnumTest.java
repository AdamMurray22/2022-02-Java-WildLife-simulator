

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/**
 * The test class BearTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AnimalEnumTest
{

    
    
    /**
     * Default constructor for test class BearTest
     */
    public AnimalEnumTest()
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
    public void testGetDayBreedingModifier()
    {
        assertEquals(0, AnimalEnum.Bear.getDayBreedingModifier());
    }

    @Test
    public void testGetDayMovementModifier()
    {
        assertEquals(1, AnimalEnum.Bear.getDayMovementModifier());
    }

    @Test
    public void testGetDayHuntingModifier()
    {
        assertEquals(0, AnimalEnum.Bear.getDayHuntingModifier());
    }

    @Test
    public void testGetNightBreedingModifier()
    {
        assertEquals(-1, AnimalEnum.Bear.getNightBreedingModifier());
    }

    @Test
    public void testGetNightMovementModifier()
    {
        assertEquals(-1, AnimalEnum.Bear.getNightMovementModifier());
    }

    @Test
    public void testGetNightHuntingModifier()
    {
        assertEquals(1, AnimalEnum.Bear.getNightHuntingModifier());
    }

    @Test
    public void testGetBreedingRange()
    {
        assertEquals(2, AnimalEnum.Bear.getBreedingRange());
    }

    @Test
    public void testGetMovementRange()
    {
        assertEquals(2, AnimalEnum.Bear.getMovementRange());
    }

    @Test
    public void testGetHuntingRange()
    {
        assertEquals(2, AnimalEnum.Bear.getHuntingRange());
    }

    @Test
    public void testGetMaxBreedingCooldown()
    {
        assertEquals(5, AnimalEnum.Bear.getMaxBreedingCooldown());
    }

    @Test
    public void testGetMinReproduceAge()
    {
        assertEquals(25, AnimalEnum.Bear.getMinReproduceAge());
    }

    @Test
    public void testGetBreedingProbability()
    {
        assertEquals(0.3, AnimalEnum.Bear.getBreedingProbability(), 0.1);
    }

    @Test
    public void testGetMaxLitterSize()
    {
        assertEquals(2, AnimalEnum.Bear.getMaxLitterSize());
    }

    @Test
    public void testGetMaxAge()
    {
        assertEquals(90, AnimalEnum.Bear.getMaxAge());
    }

    @Test
    public void testGetFoodCapacity()
    {
        assertEquals(80, AnimalEnum.Bear.getFoodCapacity());
    }

    @Test
    public void testGetFoodValue()
    {
        assertEquals(20, AnimalEnum.Bear.getFoodValue());
    }

    @Test
    public void testGetIllnessResistance()
    {
        assertEquals(0.1, AnimalEnum.Bear.getIllnessResistance(), 0.1);
    }
}

























