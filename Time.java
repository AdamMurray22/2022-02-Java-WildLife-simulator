
/**
 * Holds the current time & day and increments it every step.
 *
 * @author Adam Murray & Ryan Yan
 * @version 2022.02.28
 */
public class Time
{
    // instance variables - replace the example below with your own
    private boolean isDay;
    private int time;

    /**
     * Constructor for objects of class Time
     */
    public Time()
    {
        isDay = true;
        time = 12;
    }
    
    /**
     * Increments the current time by 1 hour, if it's over 24 hours then loops back to 0.
     * Afterwards, returns if it's currently day.
     * @return Whether if it's currently day.
     */
    public boolean incrementTime()
    {
        time = (time + 1) % 24;
        updateDay();
        return isDay();
    }
    
    /**
     * Returns if it's currently day.
     * @return Whether if it's currently day.
     */
    private boolean isDay()
    {
        return isDay;
    }
    
    /**
     * Updates day/night depending on the current time.
     * (Day is from 6am - 6pm)
     */
    private void updateDay()
    {
        isDay = (6 <= time && time < 18);
    }
}
