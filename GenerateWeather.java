import java.util.Random;

/**
 * Generates a weather condition.
 *
 * @author Adam Murray and Ryan Yan.
 * @version 2022.02.28
 */
public class GenerateWeather
{
    private double rainingProbability = 0.3;
    private double stormProbability = 0.02;
    private double snowingProbability = 0.05;
    private double cloudyProbability = 0.35;
    private static final Random rand = Randomizer.getRandom();

    
    /**
     * Constructor for objects of class Weather
     */
    public GenerateWeather()
    {
        
    }

    /**
     * Determines a weather randomly, if none was randomly chosen then defaults to sunny.
     * @return The determined weather.
     */
    public Weather determineWeather()
    {
        double randNum = rand.nextDouble();
        double current = rainingProbability;
        if (randNum < current)
        {
            return new Weather(WeatherEnum.Rainy);
        }
        current += stormProbability;
        if (randNum < current)
        {
            return new Weather(WeatherEnum.Stormy);
        }
        current += snowingProbability;
        if (randNum < current)
        {
            return new Weather(WeatherEnum.Snowy);
        }
        current += cloudyProbability;
        if (randNum < current)
        {
            return new Weather(WeatherEnum.Cloudy);
        }
        return new Weather(WeatherEnum.Sunny);
    }
}
