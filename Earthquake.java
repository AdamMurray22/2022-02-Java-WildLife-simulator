import java.util.List;
import java.util.Set;
import java.util.Random;

/**
 * Write a description of class Earthquake here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Earthquake extends NaturalDisaster
{
    // instance variables - replace the example below with your own
    private static final double SPAWN_PROBABILITY = 0.03;
    private static final int EARTHQUAKE_RANGE = 20;
    private static final double DEATH_PROBABILITY_AT_EPICENTRE = 0.999999;
    private static final int AFTERMATH_TIME = 0;
    private List<Location> nearbyLocations;
    
    private static final Random rand = Randomizer.getRandom();

    /**
     * Constructor for objects of class Earthquake
     */
    public Earthquake(Field field, Location location)
    {
        super(field, location);
        quake();
    }
    
    private void quake()
    {
        nearbyLocations = getField().adjacentLocations(getCentre(), EARTHQUAKE_RANGE);
        nearbyLocations.add(getCentre());
        for(Location location : nearbyLocations)
        {
            Set<AnimalPlant> animalPlantAtLocation = getField().getAnimalAndPlantSetAt(location);
            for (AnimalPlant animalPlant : animalPlantAtLocation)
            {
                if (animalPlant != null)
                {
                    // Distance calculated using pythagoras theorem
                    double distanceFromEpicentre = 
                    (Math.pow(Math.pow(getCentre().getRow() - location.getRow(), 2)
                    + Math.pow(getCentre().getCol() - location.getCol() , 2), 0.5));
                    
                    double deathProbability = DEATH_PROBABILITY_AT_EPICENTRE / Math.pow(distanceFromEpicentre, 0.4);
                    
                    if (deathProbability > rand.nextDouble())
                    {
                        animalPlant.setDead();
                    }
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
