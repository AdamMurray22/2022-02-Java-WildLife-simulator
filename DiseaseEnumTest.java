

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * The test class ChickenPoxTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiseaseEnumTest
{

    /**
     * Default constructor for test class ChickenPoxTest
     */
    public DiseaseEnumTest()
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
    public void testGetRandomInfectionProbability()
    {
        assertEquals(0.01, DiseaseEnum.ChickenPox.getRandomInfectionProbability(), 0.1);
    }

    @Test
    public void testGetKillAfter()
    {
        assertEquals(10, DiseaseEnum.ChickenPox.getKillAfter());
    }

    @Test
    public void testGetKillProbability()
    {
        assertEquals(0.3, DiseaseEnum.ChickenPox.getKillProbability(), 0.1);
    }

    @Test
    public void testGetInfectRange()
    {
        assertEquals(2, DiseaseEnum.ChickenPox.getInfectRange());
    }

    @Test
    public void testGetInfectionProbability()
    {
        assertEquals(0.1, DiseaseEnum.ChickenPox.getInfectionProbability(), 0.1);
    }
}

