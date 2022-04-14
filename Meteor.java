import java.util.List;
import java.util.Set;

/**
 * Write a description of class Meteor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Meteor extends NaturalDisaster
{
    // instance variables - replace the example below with your own
    private static final double SPAWN_PROBABILITY = 0.02;
    private static final int AFTERMATH_TIME = 5;
    private static final int IMPACT_RANGE = 5;
    private List<Location> nearbyLocations;
    
    
    /**
     * Constructor for objects of class Meteor
     */
    public Meteor(Field field, Location location)
    {
        super(field, location);
        impact();
    }
    
    public List<Location> getAffectedLocations()
    {
        return nearbyLocations;
    }
    
    public List<Location> getUnspawnableLocations(Class targetClass)
    {
        if (targetClass == Plant.class)
        {
            return nearbyLocations;
        }
        return null;
    }
    
    private void impact()
    {
        nearbyLocations = getField().adjacentLocations(getCentre(), IMPACT_RANGE);
        nearbyLocations.add(getCentre());
        for(Location location : nearbyLocations)
        {
            Set<AnimalPlant> animalPlantAtLocation = getField().getAnimalAndPlantSetAt(location);
            for (AnimalPlant animalPlant : animalPlantAtLocation)
            {
                if (animalPlant != null)
                {
                    animalPlant.setDead();
                }
            }
        }
    }
    
    public static double getRandomSpawnProbability()
    {
        return SPAWN_PROBABILITY;
    }
    
    protected int getAftermathTime()
    {
        return AFTERMATH_TIME;
    }
}
