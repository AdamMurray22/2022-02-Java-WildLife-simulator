

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The test class DiseaseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiseaseTest
{
    private Field field1;
    private Location location1;
    private Animal chicken1;
    private Disease chickenP1;
    /**
     * Default constructor for test class DiseaseTest
     */
    public DiseaseTest()
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
        chicken1 = new Animal(field1, location1, new HashSet<LifeEnumInterface>(), AnimalEnum.Chicken);
        chickenP1 = new Disease(field1, new HashSet<LifeEnumInterface>(), chicken1, DiseaseEnum.ChickenPox);
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
        chickenP1.act(new ArrayList<>(), environm1);
    }

    @Test
    public void testInfect()
    {
        chickenP1.infect(new ArrayList<>());
    }
}
