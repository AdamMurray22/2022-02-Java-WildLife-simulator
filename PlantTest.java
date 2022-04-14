

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class PlantTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlantTest
{
    private Field field1;
    private Location location1;
    private Plant grass1;
    
    /**
     * Default constructor for test class PlantTest
     */
    public PlantTest()
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
        field1 = new Field(50, 50, new ArrayList<>());
        location1 = new Location(5, 5);
        grass1 = new Plant(field1, location1, PlantEnum.Grass);
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
    public void testAct()
    {
        EnvironmentConditions environm1 = new EnvironmentConditions();
        grass1.act(new ArrayList<>(), environm1);
    }
    
    @Test
    public void testGrow()
    {
        grass1.grow(2);
    }

    @Test
    public void testGetFoodValue()
    {
        assertEquals(10, grass1.getFoodValue());
    }
    
    @Test
    public void testAddInfection()
    {
        grass1.addInfection(DiseaseEnum.ChickenPox);
    }

    @Test
    public void testRemoveInfection()
    {
        grass1.removeInfection(DiseaseEnum.ChickenPox);
    }

    @Test
    public void testInfected()
    {
        assertEquals(false, grass1.infected(DiseaseEnum.ChickenPox));
    }
}
