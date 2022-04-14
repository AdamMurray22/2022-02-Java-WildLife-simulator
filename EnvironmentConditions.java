
/**
 * Holds and increments the current environment conditions such as weather and time.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class EnvironmentConditions
{
    // instance variables - replace the example below with your own
    private Time time;
    private Weather weather;
    private GenerateWeather generateWeather;
    private boolean isDay;

    /**
     * Constructor for objects of class EnviromentConditions
     */
    public EnvironmentConditions()
    {
        time = new Time();
        generateWeather = new GenerateWeather();
        isDay = time.incrementTime();
        weather = generateWeather.determineWeather();
    }
    
    /**
     * Increment / update the weather, time and day/night cycle.
     */
    public void incrementConditions()
    {
        isDay = time.incrementTime();
        weather = generateWeather.determineWeather();
    }
    
    /**
     * Returns if it's currently day or night.
     * @return Whether if it's day or not
     */
    public boolean isDay()
    {
        return isDay;
    }
    
    /**
     * Returns the current weather.
     * @return The current weather
     */
    public Weather getWeather()
    {
        return weather;
    }
}
