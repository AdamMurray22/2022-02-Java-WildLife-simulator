

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SimulatorBuilderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SimulatorBuilderTest
{
    private SimulatorBuilder simulato1;

  

    /**
     * Default constructor for test class SimulatorBuilderTest
     */
    public SimulatorBuilderTest()
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
        simulato1 = new SimulatorBuilder(new Simulator());
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
        SimulatorBuilder simulato1 = new SimulatorBuilder(new Simulator());
        SimulatorBuilder simulato2 = new SimulatorBuilder(40, 40, new Simulator());
    }

    @Test
    public void getters_test()
    {
        simulato1.getDisasterList();
        assertNotNull(simulato1.getDiseaseInfectionMap());
        assertNotNull(simulato1.getField());
        assertNotNull(simulato1.getLifeList());
        assertNotNull(simulato1.getView());
    }
    
    

    @Test
    public void reset_test()
    {
        simulato1.reset();
    }
}



