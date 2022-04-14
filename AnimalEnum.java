
/**
 * Holds the constants used for the animls and allows to get methods to access them.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public enum AnimalEnum implements LifeEnumInterface
{
    Bear(25, 90, 0.3, 2, 20, 0.1, 80, 2, 2, 2, 0, 0, 1, -1, 1, -1, 5),
    Chicken(20, 60, 0.51, 2, 25, 0.1, 45, 2, 2, 2, 2, 1, 0, -2, -1, -1, 6),
    Tiger(20, 90, 0.3, 3, 20, 0.1, 55, 3, 2, 3, 2, 2, 2, -2, -2, -2, 7),
    Sheep(25, 60, 0.3, 2, 15, 0.1, 40, 2, 1, 2, 0, 0, 0, 1, 1, 1, 5),
    Worm(15, 40, 0.5, 3, 13, 0.1, 20, 1, 1, 1, 0, 0, 0, 1, 1, 1, 3);
    
    // The age at which a Animal can start to breed.
    private final int breedingAge;
    // The age to which a Animal can live.
    private final int maxAge;
    // The likelihood of a Animal breeding.
    private final double breedingProbability;
    // The maximum number of births.
    private final int maxLitterSize;
    // The amount of food given to an animal that eats a Animal.
    private final int foodValue;
    // The Animals resistance to getting ill.
    private final double illnessResistance;
    // The max food value of the Animal.
    private final int foodCapcity;
           
    private final int breedingRange;
    private final int huntingRange;
    private final int movementRange;
    
    private final int dayBreedingModifier;
    private final int dayHuntingModifier;
    private final int dayMovementModifier;
    private final int nightBreedingModifier;
    private final int nightHuntingModifier;
    private final int nightMovementModifier;
    
    private final int breedingCooldown;
    
    private AnimalEnum(int breedingAge, int maxAge, double breedingProbability, int maxLitterSize, int foodValue, double illnessResistance,
                       int foodCapcity, int breedingRange, int huntingRange, int movementRange, int dayBreedingModifier, int dayHuntingModifier,
                       int dayMovementModifier, int nightBreedingModifier, int nightHuntingModifier, int nightMovementModifier, int breedingCooldown)
    {
        this.breedingAge = breedingAge;
        this.maxAge = maxAge;
        this.breedingProbability = breedingProbability;
        this.maxLitterSize = maxLitterSize;
        this.foodValue = foodValue;
        this.illnessResistance = illnessResistance;
        this.foodCapcity = foodCapcity;
        this.breedingRange = breedingRange;
        this.huntingRange = huntingRange;
        this.movementRange = movementRange;
        this.dayBreedingModifier = dayBreedingModifier;
        this.dayHuntingModifier = dayHuntingModifier;
        this.dayMovementModifier = dayMovementModifier;
        this.nightBreedingModifier = nightBreedingModifier;
        this.nightHuntingModifier = nightHuntingModifier;
        this.nightMovementModifier = nightMovementModifier;
        this.breedingCooldown = breedingCooldown;
    }
    
    /**
     * Returns the day breeding modifier of the Animal.
     * @return The day breeding modifier of the Animal.
     */
    protected int getDayBreedingModifier()
    {
        return dayBreedingModifier;
    }
    
    /**
     * Returns the day movement modifier of the Animal.
     * @return The day movement modifier of the Animal.
     */
    protected int getDayMovementModifier()
    {
        return dayMovementModifier;
    }
    
    /**
     * Returns the day hunting modifier of the Animal.
     * @return The day hunting modifier of the Animal.
     */
    protected int getDayHuntingModifier()
    {
        return dayHuntingModifier;
    }
    
    /**
     * Returns the night breeding modifier of the Animal.
     * @return The night breeding modifier of the Animal.
     */
    protected int getNightBreedingModifier()
    {
        return nightBreedingModifier;        
    }
    
    /**
     * Returns the night movement modifier of the Animal.
     * @return The night movement modifier of the Animal.
     */
    protected int getNightMovementModifier()
    {
        return nightMovementModifier;
    }
    
    /**
     * Returns the night hunting modifier of the Animal.
     * @return The night hunting modifier of the Animal.
     */
    protected int getNightHuntingModifier()
    {
        return nightHuntingModifier;
    }
    
    /**
     * Returns the breeding range of the Animal.
     * @return The breeding range of the Animal.
     */
    protected int getBreedingRange()
    {
        return breedingRange;
    }
    
    /**
     * Returns the movement range of the Animal.
     * @return The movement range of the Animal.
     */
    protected int getMovementRange()
    {
        return movementRange;
    }
    
    /**
     * Returns the hunting range of the Animal.
     * @return The hunting range of the Animal.
     */
    protected int getHuntingRange()
    {
        return huntingRange;
    }
    
    /**
     * Returns the max breeding cooldown of the Animal.
     * @return The max breeding cooldown of the Animal.
     */
    protected int getMaxBreedingCooldown()
    {
        return breedingCooldown;
    }
    
    /**
     * Returns the minimum age when a Animal can breed.
     * @return The minimum age when a Animal can breed.
     */
    protected int getMinReproduceAge()
    {
        return breedingAge;
    }
    
    /**
     * Returns the breeding probability of a Animal.
     * @return The breeding probability of a Animal.
     */
    protected double getBreedingProbability()
    {
        return breedingProbability;
    }
    
    /**
     * Returns the max litter size.
     * @return The max litter size.
     */
    protected int getMaxLitterSize()
    {
        return maxLitterSize;
    }
    
    /**
     * Returns the Max age of the Animal.
     * @return The max age of the Animal.
     */
    protected int getMaxAge()
    {
        return maxAge;
    }
    
    /**
     * Returns the food capacity of the Animal.
     * @return The food capacity of the Animal.
     */
    protected int getFoodCapacity()
    {
        return foodCapcity;
    }
    
    /**
     * Returns the food value of the Animal.
     * @return The food value of the Animal.
     */
    protected int getFoodValue()
    {
        return foodValue;
    }
    
    /**
     * Returns the Animals resistance to illness.
     * @return The Animals resistance to illness.
     */
    public double getIllnessResistance()
    {
        return illnessResistance;
    }
}
