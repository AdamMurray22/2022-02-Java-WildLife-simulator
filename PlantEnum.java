
/**
 * Holds the constants used for the plant and allows to get methods to access them.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public enum PlantEnum implements LifeEnumInterface
{
    Grass(10, 25, 7, 4, 5, 2, 0.6, 10, 20, 0.1, true),
    Corn(10, 15, 3, 1, 6, 3, 0.55, 11, 20, 0.1, false);
    
    //Minmum age the plant can reproduce
    private final int minSpreadingAge;
    //Max age of the plant
    private final int maxAge;
    //The range at which the plantes children can spawn
    private final int spreadRange;
    //The growth rate of the plant
    private final int growthRate;
    //The number of new plant an object of plant can create each step
    private final int maxSeedCount;
    //The cooldown after reproducing
    private final int maxSpreadingCooldown;
    //The chance of reproducing
    private final double spreadingProbability;
    //The initial food value of the plant
    private final int defualtFoodValue;
    //The max food value of the plant
    private final int maxFoodValue;
    //The plantes resistance to getting ill
    private final double illnessResistance;
    //Whether the plant grows during day or night, true for day, false for night
    private final boolean growDuringDay;
    
    private PlantEnum(int minSpreadingAge, int maxAge, int spreadRange, int growthRate, int maxSeedCount, int maxSpreadingCooldown, double spreadingProbability, int defualtFoodValue, int maxFoodValue, double illnessResistance, boolean growDuringDay)
    {
        this.minSpreadingAge = minSpreadingAge;
        this.maxAge = maxAge;
        this.spreadRange = spreadRange;
        this.growthRate = growthRate;
        this.maxSeedCount = maxSeedCount;
        this.maxSpreadingCooldown = maxSpreadingCooldown;
        this.spreadingProbability = spreadingProbability;
        this.defualtFoodValue = defualtFoodValue;
        this.maxFoodValue = maxFoodValue;
        this.illnessResistance = illnessResistance;
        this.growDuringDay = growDuringDay;
    }
    
    /**
     * Returns the growth rate.
     * @return The growth rate.
     */
    public int getGrowthRate()
    {
        return growthRate;
    }
    
    /**
     * Returns the illness resistance of the plant.
     * @return The illness resistance of the plant.
     */
    public double getIllnessResistance()
    {
        return illnessResistance;
    }
    
    /**
     * Returns the max food value of the plant.
     * @return The max food value of the plant.
     */
    public int getMaxFoodValue()
    {
        return maxFoodValue;
    }
    
    /**
     * Returns the max age of the plant.
     * @return The max age of the plant.
     */
    public int getMaxAge()
    {
        return maxAge;
    }
    
    /**
     * Returns the spreading probability.
     * @return The spreading probability.
     */
    public double getSpreadingProbability()
    {
        return spreadingProbability;
    }
    
    /**
     * Returns the max number of children the plant can create in 1 step.
     * @return The max number of children the plant can create in 1 step.
     */
    public int getMaxSeedCount()
    {
        return maxSeedCount;
    }
    
    /**
     * Returns the range at which its children can spawn.
     * @return The range at which its children can spawn.
     */
    public int getSpreadRange()
    {
        return spreadRange;
    }
    
    /**
     * Returns whether plant can grow during the day.
     * @return Whether plant can grow during the day, true if it can, false if it grows at night.
     */
    public boolean canGrowDuringDay()
    {
        return growDuringDay;
    }
    
    /**
     * Returns the minimum age when plant can reproduce.
     * @return The minimum age when plant can reproduce.
     */
    public int getMinReproduceAge()
    {
        return minSpreadingAge;
    }
    
    /**
     * Returns the max spreading cooldown.
     * @return The max spreading cooldown.
     */
    public int getMaxSpreadingCooldown()
    {
        return maxSpreadingCooldown;
    }
    
    /**
     * Returns the defualt food value.
     * @return The defualt food value.
     */
    public int getDefualtFoodValue()
    {
        return defualtFoodValue;
    }
}
