import java.util.Random;
import java.util.List;

/**
 * This class contains most of the methods of a plant and threw this class acts out what a plant does
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Plant extends AnimalPlant
{
    private int foodValue; // The value given to anything that eats it.
    private int currentSpreadCooldown; // The number of steps until it can next spread.

    private static final Random rand = Randomizer.getRandom();
    private PlantWeatherModifiers weatherModifiers;
    private PlantEnum plantType;

    /**
     * Constructor for objects of class Plant.
     * @param age The initial age of the Plant.
     * @param field The Field the simulator is using.
     * @param loction The location of the Plant.
     * @param foodValue The initial foodValue of the Plant.
     */
    public Plant(Field field, Location location, PlantEnum plantType)
    {
        super(rand.nextInt(plantType.getMaxAge()), field, location);
        currentSpreadCooldown = 0;
        setLocation(location);
        this.foodValue = plantType.getDefualtFoodValue();
        this.plantType = plantType;
    }

    /**
     * This method acts out what the Plant does every step,
     * It gets older, trys to reproduce and grows.
     * @param newPlants The list of new Plants that are created are added to this list.
     * @param environmentConditions An instance of EnvironmentConditions that tells the Plant what the time and weather currently is. 
     */
    public void act(List<Life> newPlants, EnvironmentConditions environmentConditions)
    {
        incrementAge();
        incrementSpreadingCooldown();
        if (isAlive())
        {
            weatherModifiers = environmentConditions.getWeather().plantWeatherAction(plantType);
            if (weatherModifiers.isDead())
            {
                setDead();
                return;
            }
            if (canGrow(environmentConditions) && currentSpreadCooldown == 0)
            {
                grow(plantType.getGrowthRate() + weatherModifiers.getGrowthRateModifier());
                reproduce(newPlants);
            }
        }
    }

    /**
     * This method attempts to create new Plants.
     * @param newPlants The list of new Plants that are created are added to this list.
     */
    protected void reproduce(List<Life> newPlants)
    {
        List<Location> spawnable = getField().spawnableAdjacentLocations(getLocation(), plantType.getSpreadRange(), this.getClass());
        int numOfNewSeeds = spread();
        for(int b = 0; b < numOfNewSeeds && spawnable.size() > 0; b++) {
            Location spawnLocation = spawnable.remove(0);
            Plant young = new Plant(getField(), spawnLocation, plantType);
            newPlants.add(young);
        }
    }

    /**
     * Returns whether its the right time of day for this plant to grow.
     * @return true if the time of day is what is needed for the plant to grow, false if it isnt.
     */
    private boolean canGrow(EnvironmentConditions environmentConditions)
    {
        return environmentConditions.isDay() == plantType.canGrowDuringDay();
    }

    /**
     * The plants foodValue is increated by its growth rate unto a certain value.
     * @param growthRate the value to be added to the foodValue of the Plant.
     */
    public void grow(int growthRate)
    {
        foodValue = foodValue + growthRate;
        if (foodValue > plantType.getMaxFoodValue())
        {
            foodValue = plantType.getMaxFoodValue();
        }
    }

    /**
     * Generates the number of new plants that will be created by this plant.
     * @return The number of new plants that will be created by this plant. (may be 0)
     */
    protected int spread()
    {
        int seeds = 0;
        if(canReproduce() && rand.nextDouble() <= plantType.getSpreadingProbability()) {
            seeds = rand.nextInt(plantType.getMaxSeedCount()) + 1;
            setSpreadCooldown(plantType.getMaxSpreadingCooldown());
        }
        return seeds;
    }

    /**
     * Sets a new location for the Plant.
     * @param newLocation The location where the Plant will be.
     */
    protected void setLocation(Location newLocation)
    {
        if(getLocation() != null) {
            getField().clearPlant(getLocation());
        }
        super.setLocation(newLocation);
        getField().placePlant(this, newLocation);
    }

    /**
     * Kills the Plant and removes it from the field.
     */
    protected void setDead()
    {
        if (!isAlive())
        {
            return;
        }
        super.setDead();
        if(getLocation() != null) {
            getField().clearPlant(getLocation());
            setField(null);
        }
    }
    
    
    /**
     * Returns the foodvalue of the Plant.
     * @return The foodValue of the Plant.
     */
    public int getFoodValue()
    {
        return foodValue;
    }
    
    /**
     * Sets the spread cooldown, spopping it reproducing for a number of steps.
     * @param spreadCooldown The number of steps the cooldown will be active for.
     */
    protected void setSpreadCooldown(int spreadCooldown)
    {
        currentSpreadCooldown = spreadCooldown;
    }
    
    /**
     * Decreases the spread cooldown by 1.
     */
    protected void incrementSpreadingCooldown()
    {
        if(currentSpreadCooldown > 0)
        {
            currentSpreadCooldown--;
        }
    }
    
    /**
     * Returns the illness resistance of the plant.
     * @return The illness resistance of the plant.
     */
    protected double getIllnessResistance()
    {
        return plantType.getIllnessResistance();
    }
    
    /**
     * Returns the minimum age when grass can reproduce.
     * @return The minimum age when grass can reproduce.
     */
    protected int getMinReproduceAge()
    {
        return plantType.getMinReproduceAge();
    }
    
    /**
     * Returns the max age of the grass.
     * @return The max age of the grass.
     */
    public int getMaxAge()
    {
        return plantType.getMaxAge();
    }
    
    public LifeEnumInterface getType()
    {
        return plantType;
    }
}
