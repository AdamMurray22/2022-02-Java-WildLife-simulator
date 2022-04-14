import java.util.List;
import java.util.Set;
import java.util.Random;

/**
 * This simulates a flood killing lots of animals and stopping new animals spawning in the flood
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Flood extends NaturalDisaster
{
    // The chance of a flood each turn
    private static final double SPAWN_PROBABILITY = 0.03;
    // The probability of killing an animal each turn
    private static final double DEATH_PROBABILITY = 0.3;
    // The range of the flood (the true range is actually 21 as its 20 to tiles in each direction)
    private static final int FLOOD_RANGE = 20;
    // The number of turns the flood lasts for
    private static final int AFTERMATH_TIME = 3;
    private List<Location> nearbyLocations;
    
    private static final Random rand = Randomizer.getRandom();

    /**
     * Constructor for objects of class Flood
     * Starts the flood
     * @param field The Field the simulator is using.
     * @param location The centre of the flood.
     */
    public Flood(Field field, Location location)
    {
        super(field, location);
        flood();
    }

    /**
     * This is called each step, it continues the flood
     */
    public void progress()
    {
        super.progress();
        flood();
    }

    /**
     * Finds all the animals in range and has a chance of killing them.
     */
    private void flood()
    {
        nearbyLocations = getField().adjacentLocations(getCentre(), FLOOD_RANGE);
        nearbyLocations.add(getCentre());
        for(Location location : nearbyLocations)
        {
            Animal animal = getField().getAnimalAt(location);
            if (animal != null)
            {
                if (DEATH_PROBABILITY > rand.nextDouble())
                {
                    animal.setDead();
                }
            }
        }
    }

    /**
     * Returns all the nearby locations that an animal cant spawn, if a plant is passed in it returns null
     * @param The superClass of the object being tested, Animal or Plant.
     * @return List of locations that the targetClass cannot spawn, can be null.
     */
    public List<Location> getUnspawnableLocations(Class targetClass)
    {
        if (targetClass == Animal.class)
        {
            return nearbyLocations;
        }
        return null;
    }

    /**
     * Returns the random spawn probability of a flood.
     * @return The random spawn probability of a flood.
     */
    public static double getRandomSpawnProbability()
    {
        return SPAWN_PROBABILITY;
    }
    
    /**
     * Returns the aftermath time.
     * @return The aftermath time.
     */
    protected int getAftermathTime()
    {
        return AFTERMATH_TIME;
    }
}
