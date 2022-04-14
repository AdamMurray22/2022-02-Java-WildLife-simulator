/**
 * Class that holds and sets plant's modifiers and 
 * whether if it has been killed under the weather condition or not
 *
 * @author Adam Murray & Ryan Yan
 * @version 2022.02.28
 */
public class PlantWeatherModifiers
{
    private int growthRateModifier;
    private boolean isDead;
    
    /**
     * Constructing the class, setting the modifier to 0 and stating the plant is alive.
     */
    public PlantWeatherModifiers()
    {
        growthRateModifier = 0;
        isDead = false;
    }
    
    /**
     * Returns the current growth rate modifier.
     * @return The current growth rate modifier
     */
    public int getGrowthRateModifier()
    {
        return growthRateModifier;
    }
    
    /**
     * Returns if the plant is killed under the weather condition or not.
     * @return Whether if the plant is killed under the weather condition or not.
     */
    public boolean isDead()
    {
        return isDead;
    }
    
    /**
     * Increases the growth rate modifier by a given value.
     * @param modifier The value to increase the modifier by
     */
    public void setGrowthRateModifier(int modifier)
    {
        growthRateModifier = modifier;
    }
    
    /**
     * Sets the plant to be dead or alive, depending on the given boolean value.
     * @param dead A boolean value indicating whether the plant has been killed under the 
     *             weather condition or not.
     */
    public void setDead(boolean dead)
    {
        isDead = dead;
    }
    
}
