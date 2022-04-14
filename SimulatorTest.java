

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SimulatorTest.
 *
 * @author  Ryan Yan and Adam Murray
 * @version 2022.02.28
 */
public class SimulatorTest
{
    private Simulator simulato1;

    /**
     * Default constructor for test class SimulatorTest
     */
    public SimulatorTest()
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

    
    /**
     * Tests the constructors, and getBuiltSim().
     */
    @Test
    public void constructor_test()
    {
        Simulator simulato2 = new Simulator(40, 40);
        Simulator simulato3 = new Simulator();
    }
    
    /**
     * Tests the simulator's ability to simulate steps.
     */
    @Test
    public void simulate_steps_test()
    {
        simulato1 = new Simulator();
        simulato1.simulateOneStep();
        simulato1.simulate(5);
        simulato1.runLongSimulation();
    }
    
    @Test
    public void randomInfection_test()
    {
        
    }

    @Test
    public void reset_test()
    {
        simulato1 = new Simulator();
        simulato1.reset();
    }
}




