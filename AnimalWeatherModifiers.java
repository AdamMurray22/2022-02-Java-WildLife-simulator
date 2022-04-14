
/**
 * Class that holds and sets animal's modifiers
 *
 * @author Adam Murray & Ryan Yan
 * @version 2022.02.28
 */
public class AnimalWeatherModifiers
{
    // instance variables - replace the example below with your own
    private int movementModifier;
    private int huntingModifier;
    private int breedingModifier;

    /**
     * Constructing the class, setting all modifiers to 0.
     */
    public AnimalWeatherModifiers()
    {
        movementModifier = 0;
        huntingModifier = 0;
        breedingModifier = 0;
    }
    
    /**
     * Returns the current movement modifier.
     * @return The current movement modifier
     */
    public int getMovementModifier()
    {
        return movementModifier;
    }
    
    /**
     * Returns the current hunting modifier.
     * @return The current hunting modifier
     */
    public int getHuntingModifier()
    {
        return huntingModifier;
    }
    
    /**
     * Returns the current breeding modifier.
     * @return The current breeding modifier
     */
    public int getBreedingModifier()
    {
        return breedingModifier;
    }
    
    /**
     * Increments the current movement modifier by a given value.
     * @param modifier The value to increase the modifier by.
     */
    public void setMovementModifier(int modifier)
    {
        movementModifier = modifier;
    }
    
    /**
     * Increments the current hunting modifier by a given value.
     * @param modifier The value to increase the modifier by.
     */
    public void setHuntingModifier(int modifier)
    {
        huntingModifier = modifier;
    }
    
    /**
     * Increments the current breeding modifier by a given value.
     * @param modifier The value to increase the modifier by.
     */
    public void setBreedingModifier(int modifier)
    {
        breedingModifier = modifier;
    }
}
