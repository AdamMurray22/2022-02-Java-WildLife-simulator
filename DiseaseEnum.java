
/**
 * Holds the constants used for the disease and allows to get methods to access them.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public enum DiseaseEnum implements LifeEnumInterface
{
    ChickenPox(2, 0.1, 10, 0.3, 0.01),
    CornPox(1, 0.1, 2, 0.1, 0.005);
    
    // The range at which the disease can infect other animals/plants
    private final int infectRange;
    // The probability of infecting another animal/plant near the current victim
    private final double infectionProbability;
    // The number of steps until the victim is cured or dead
    private final int killAfter;
    // The probability of the disease killing its victim
    private final double killProbability;
    //The probability of an instance of the disease spontaneously infecting a victim
    private final double randomInfectionProbability;
    
    private DiseaseEnum(int infectRange, double infectionProbability, int killAfter, double killProbability, double randomInfectionProbability)
    {
        this.infectRange = infectRange;
        this.infectionProbability = infectionProbability;
        this.killAfter = killAfter;
        this.killProbability = killProbability;
        this.randomInfectionProbability = randomInfectionProbability;
    }
    
    /**
     * Returns the range at which the disease can infect nearby animals or plants.
     * @return The range at which the disease can infect nearby animals or plants.
     */
    public int getInfectRange()
    {
        return infectRange;
    }
    
    /**
     * Returns the probability of a potential victim being infected.
     * @return The probability of a potential victim being infected.
     */
    public double getInfectionProbability()
    {
        return infectionProbability;
    }
    
    /**
     * Returns the number of steps after which the disease is either cured or kills its victim.
     * @return The number of steps after which the disease is either cured or kills its victim.
     */
    public int getKillAfter()
    {
        return killAfter;
    }
    
    /**
     * Returns the probability of the disease killing its victim.
     * @return The probability of the disease killing its victim.
     */
    public double getKillProbability()
    {
        return killProbability;
    }
    
    /**
     * Returns the random infection probability.
     * @return The random infection probability.
     */
    public double getRandomInfectionProbability()
    {
        return randomInfectionProbability;
    }
}
