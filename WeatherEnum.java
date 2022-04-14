
/**
 * Class holding all modifiers related to a variety of weather environments.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public enum WeatherEnum
{
    Sunny(1, 1, 2, 0, 0, 1, 1, 0, 0),
    Stormy(1, -2, -1, 1, -2, -1, -1, 0.3, 0.3),
    Snowy(1, 0, -2, -1, 0, -(PlantEnum.Grass.getGrowthRate()), -(PlantEnum.Corn.getGrowthRate()), 0, 0),
    Cloudy(0, 1, 2, 0, 0, 0, 0, 0, 0),
    Rainy(-1, -2, 0, 1, 0, 2, 2, 0, 0);
    
    private final int bearEnvironmentModifier;
    private final int chickenEnvironmentModifier;
    private final int tigerEnvironmentModifier;
    private final int sheepEnvironmentModifier;
    private final int wormEnvironmentModifier;
    
    
    private final int grassEnvironmentModifier;
    private final int cornEnvironmentModifier;
    private final double grassEnvironmentDeathProbability;
    private final double cornEnvironmentDeathProbability;
    
    private WeatherEnum(int bearEnvironmentModifier, int chickenEnvironmentModifier, int tigerEnvironmentModifier,
                        int sheepEnvironmentModifier, int wormEnvironmentModifier, int grassEnvironmentModifier,
                        int cornEnvironmentModifier, double grassEnvironmentDeathProbability, double cornEnvironmentDeathProbability)
    {
        this.bearEnvironmentModifier = bearEnvironmentModifier;
        this.chickenEnvironmentModifier = chickenEnvironmentModifier;
        this.tigerEnvironmentModifier = tigerEnvironmentModifier;
        this.sheepEnvironmentModifier = sheepEnvironmentModifier;
        this.wormEnvironmentModifier = wormEnvironmentModifier;
        
        this.grassEnvironmentModifier = grassEnvironmentModifier;
        this.cornEnvironmentModifier = cornEnvironmentModifier;
        this.grassEnvironmentDeathProbability = grassEnvironmentDeathProbability;
        this.cornEnvironmentDeathProbability = cornEnvironmentDeathProbability;
    }
    
    /**
     * Returns the bear's environment modifiers.
     * @return Bear's environment modifier under a particular weather condition.
     */
    protected int getBearEnvironmentModifier()
    {
        return bearEnvironmentModifier;
    }
    
    /**
     * Returns the chicken's environment modifiers.
     * @return Chicken's environment modifier under a particular weather condition.
     */
    protected int getChickenEnvironmentModifier()
    {
        return chickenEnvironmentModifier;
    }
    
    /**
     * Returns the tiger's environment modifiers.
     * @return Tiger's environment modifier under a particular weather condition.
     */
    protected int getTigerEnvironmentModifier()
    {
        return tigerEnvironmentModifier;
    }
    
    /**
     * Returns the sheep's environment modifiers.
     * @return Sheep's environment modifier under a particular weather condition.
     */
    protected int getSheepEnvironmentModifier()
    {
        return sheepEnvironmentModifier;
    }
    
    /**
     * Returns the worm's environment modifiers.
     * @return Worm's environment modifier under a particular weather condition.
     */
    protected int getWormEnvironmentModifier()
    {
        return wormEnvironmentModifier;
    }
    
    /**
     * Returns the grass's environment modifiers.
     * @return Grass's environment modifier under a particular weather condition.
     */
    protected int getGrassEnvironmentModifier()
    {
        return grassEnvironmentModifier;
    }
    
    /**
     * Returns the corn's environment modifiers.
     * @return Corn's environment modifier under a particular weather condition.
     */
    protected int getCornEnvironmentModifier()
    {
        return cornEnvironmentModifier;
    }
    
    /**
     * Returns grass's death probability.
     * @return Grass's death probability.
     */
    protected double getGrassDeathProbability()
    {
        return grassEnvironmentDeathProbability;
    }
    
    /**
     * Returns corn's death probability.
     * @return Corn's death probability.
     */
    protected double getCornDeathProbability()
    {
        return cornEnvironmentDeathProbability;
    }
}
