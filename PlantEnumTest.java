

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class GrassTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlantEnumTest
{

    
    

    /**
     * Default constructor for test class GrassTest
     */
    public PlantEnumTest()
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
    public void testGetGrowthRate()
    {
        assertEquals(4, PlantEnum.Grass.getGrowthRate());
    }
    

    @Test
    public void testGetIllnessResistance()
    {
        assertEquals(0.1, PlantEnum.Grass.getIllnessResistance(), 0.1);
    }

    @Test
    public void testGetMaxFoodValue()
    {
        assertEquals(20, PlantEnum.Grass.getMaxFoodValue());
    }

    @Test
    public void testGetMaxAge()
    {
        assertEquals(25, PlantEnum.Grass.getMaxAge());
    }

    @Test
    public void testGetSpreadingProbability()
    {
        assertEquals(0.7, PlantEnum.Grass.getSpreadingProbability(), 0.1);
    }

    @Test
    public void testGetMaxSeedCount()
    {
        assertEquals(5, PlantEnum.Grass.getMaxSeedCount());
    }

    @Test
    public void testGetSpreadRange()
    {
        assertEquals(7, PlantEnum.Grass.getSpreadRange());
    }

    @Test
    public void testCanGrowDuringDay()
    {
        assertEquals(true, PlantEnum.Grass.canGrowDuringDay());
    }

    @Test
    public void testGetMinReproduceAge()
    {
        assertEquals(10, PlantEnum.Grass.getMinReproduceAge());
    }

    @Test
    public void testGetMaxSpreadingCooldown()
    {
        assertEquals(2, PlantEnum.Grass.getMaxSpreadingCooldown());
    }
}
