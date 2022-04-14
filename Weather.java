import java.util.Random;

/**
 * Class initialising all modifiers related to a variety of weather environments.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Weather
{
    private WeatherEnum weatherType;
    private static final Random rand = Randomizer.getRandom();
    /**
     * Constructor for objects of class Weather
     */
    public Weather(WeatherEnum weatherType)
    {
        this.weatherType = weatherType;
    }
    
    /**
     * Sets the animal's modifiers depending on the current weather.
     * @param animal The class object of the animal whose modifiers are going to be set.
     * @return An AnimalWeatherModifier object with set modifiers to be attached to the animal's 
     *         default ranges.
     */
    public AnimalWeatherModifiers animalWeatherAction(AnimalEnum animal)
    {
        AnimalWeatherModifiers animalWeatherModifiers = new AnimalWeatherModifiers();
        if (animal == AnimalEnum.Bear)
        {
            animalWeatherModifiers.setMovementModifier(weatherType.getBearEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getBearEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getBearEnvironmentModifier());
        }
        else if (animal == AnimalEnum.Chicken)
        {
            animalWeatherModifiers.setMovementModifier(weatherType.getChickenEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getChickenEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getChickenEnvironmentModifier());
        }
        else if (animal == AnimalEnum.Tiger)
        {
            animalWeatherModifiers.setMovementModifier(weatherType.getTigerEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getTigerEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getTigerEnvironmentModifier());
        }
        else if (animal == AnimalEnum.Sheep)
        {
            animalWeatherModifiers.setMovementModifier(weatherType.getSheepEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getSheepEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getSheepEnvironmentModifier());
        }
        else if (animal == AnimalEnum.Worm)
        {
            animalWeatherModifiers.setMovementModifier(weatherType.getWormEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getWormEnvironmentModifier());
            animalWeatherModifiers.setMovementModifier(weatherType.getWormEnvironmentModifier());
        }
        return animalWeatherModifiers;
    }
    
    /**
     * Sets the modifiers for grass and corn.
     * @param plant The class object of plant (corn / grass)
     * @return A PlantWeatherModifiers object with set modifiers to be attached
     *         to the plant's default growth rate.
     */
    public PlantWeatherModifiers plantWeatherAction(PlantEnum plant)
    {
        PlantWeatherModifiers plantWeatherModifiers = new PlantWeatherModifiers();
        if (plant == PlantEnum.Grass)
        {
            plantWeatherModifiers.setGrowthRateModifier(weatherType.getGrassEnvironmentModifier());
            if (rand.nextDouble() < weatherType.getGrassDeathProbability())
            {
                plantWeatherModifiers.setDead(true);
            }
        }
        else if (plant == PlantEnum.Corn)
        {
            plantWeatherModifiers.setGrowthRateModifier(weatherType.getCornEnvironmentModifier());
            if (rand.nextDouble() < weatherType.getCornDeathProbability())
            {
                plantWeatherModifiers.setDead(true);
            }
        }
        return plantWeatherModifiers;
    }
}
 
