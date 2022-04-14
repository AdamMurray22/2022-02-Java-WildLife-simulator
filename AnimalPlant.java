import java.util.Set;
import java.util.HashSet;

/**
 * This class contains some common methods between Animal and Plant such as incrementAge() and canReproduce(),
 * however the real purpose of this class is to be able to allow for other classes to refer to animals and plants as the same for certain processes.
 * 
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public abstract class AnimalPlant extends Life
{
    private Location location; //Current location of the animal or plant
    private Set<DiseaseEnum> infectedBy; // Set of Diseases that are currently infecting the animal or plant
    
    /**
     * Constructor for objects of class AnimalPlant.
     * @param age The initial age of the AnimalPlant.
     * @param field The field that the simulator is using.
     * @param location The location of this AnimalPlant on the field.
     */
    public AnimalPlant(int age, Field field, Location location)
    {
        super(age, field);
        this.location = location;
        infectedBy = new HashSet<>();
    }
    
    /**
     * Returns whether the AnimalPlant is old enough to reproduce.
     * @return true if the AnimalPlant is old enought to reproduce, false if its too young.
     */
    protected boolean canReproduce()
    {
        return getAge() >= getMinReproduceAge();
    }
    
    /**
     * Calls increment age in life, then checks if the AnimalPlant hasnt become too old, if it is too old it dies.
     */
    protected void incrementAge()
    {
        super.incrementAge();
        if(getAge() > getMaxAge()) 
        {
            setDead();
        }
    }
    
    /**
     * Adds a Disease Class to the infectedBy set.
     * @param newInfectionClass The Disease Class to be added to the infection set.
     */
    public void addInfection(DiseaseEnum newInfectionClass)
    {
        infectedBy.add(newInfectionClass);
    }
    
    /**
     * Removes a Disease Class from the infectedBy set.
     * @param removeInfectionClass The Disease Class to be removed from the infection set.
     */
    public void removeInfection(DiseaseEnum removeInfectionClass)
    {
        infectedBy.remove(removeInfectionClass);
    }
    
    /**
     * Checks if a given Class object is present in the infectedBy set.
     * @param newInfection A class to be checked against the infectedBy set.
     * @return true if newInfection is found in infectedBy, false if its not.
     */
    public boolean infected(DiseaseEnum newInfection)
    {
        for (DiseaseEnum infection : infectedBy)
        {
            if (infection == newInfection)
            {
                return true;
            }
        }
        return false;
    }
    
    public Set<DiseaseEnum> getInfectedBy()
    {
        return infectedBy;
    }
    
    /**
     * Returns the location of the AnimalPlant.
     * @return the location of the AnimalPlant.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Sets the location of the AnimalPlant to the location given.
     * @param location the new location of the AnimalPlant.
     */
    protected void setLocation(Location location)
    {
        this.location = location;
    }
    
    /**
     * The max Age of the AnimalPlant.
     * @return The max age of the AnimalPlant.
     */
    protected abstract int getMaxAge();
    
    /**
     * The minimum age that the AnimalPlant can reproduce at.
     * @return The minimum age that the AnimalPlant can reproduce at.
     */
    protected abstract int getMinReproduceAge();
    
    /**
     * The Food value of the AnimalPlant.
     * @return The Food value of the AnimalPlant.
     */
    protected abstract int getFoodValue();
    
    /**
     * The illness resistance of the AnimalPlant.
     * @return The illness resistance of the AnimalPlant.
     */
    protected abstract double getIllnessResistance();
}
