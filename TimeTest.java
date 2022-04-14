

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TimeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TimeTest
{
    /**
     * Default constructor for test class TimeTest
     */
    public TimeTest()
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
        Time time1 = new Time();
    }

    @Test
    public void increment_test()
    {
        Time time1 = new Time();
        assertEquals(true, time1.incrementTime());
    }
}


