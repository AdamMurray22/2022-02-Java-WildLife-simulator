

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The test class AnimalTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AnimalTest
{
    private Field field1;
    private Location location1;
    private Animal bear1;
    /**
     * Default constructor for test class AnimalTest
     */
    public AnimalTest()
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
        field1 = new Field(100, 100, new ArrayList<>());
        location1 = new Location(50, 50);
        bear1 = new Animal(field1, location1, new HashSet<LifeEnumInterface>(), AnimalEnum.Bear);
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
        bear1.act(new ArrayList<>(), environm1);
    }

    @Test
    public void testIsFemale()
    {
        bear1.isFemale();
    }

    @Test
    public void testAddInfection()
    {
        bear1.addInfection(DiseaseEnum.ChickenPox);
    }

    @Test
    public void testRemoveInfection()
    {
        bear1.removeInfection(DiseaseEnum.ChickenPox);
    }

    @Test
    public void testInfected()
    {
        assertEquals(false, bear1.infected(DiseaseEnum.ChickenPox));
    }
}
