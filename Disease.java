import java.util.List;
import java.util.Set;
import java.util.Random;

/**
 * This class contains most of the methods of a disease and threw this class acts out what a disease does
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Disease extends Life
{
    private AnimalPlant victim; // The AnimalPlant that has been infected.
    private static final Random rand = Randomizer.getRandom();
    private Set<LifeEnumInterface> infectionSet; // The set of Classes that can be infected by this disease.
    
    private DiseaseEnum diseaseType;

    /**
     * Constructor for objects of class Disease
     * @param field The field that the simulator is using.
     * @param infectionSet The set of Classes that can be infected by this disease.
     * @param victim The AnimalPlant that has been infected.
     */
    public Disease(Field field, Set<LifeEnumInterface> infectionSet, AnimalPlant victim, DiseaseEnum diseaseType)
    {
        super(0, field);
        this.infectionSet = infectionSet;
        this.victim = victim;
        victim.addInfection(diseaseType);
        this.diseaseType = diseaseType;
    }

    /**
     * This method acts out what the disease does every step.
     * It reproduces, gets older and when it reaches its time, trys to kill its host.
     * @param newDisease The list of new diseases that are created are added to this list.
     * @param environmentConditions An instance of EnvironmentConditions that tells the disease what the time and weather currently is. 
     */
    public void act(List<Life> newDisease, EnvironmentConditions environmentConditions)
    {
        infect(newDisease);
        incrementAge();
        if (getAge() >= diseaseType.getKillAfter())
        {
            attemptKill();
        }
    }

    /**
     * This method attemps to infect the surrounding Animals and Plants.
     * @param newDisease The list of new diseases that are created are added to this list.
     */
    public void infect(List<Life> newDiseases)
    {
        if (!victim.isAlive()) // If the victim is dead then the disease has no host so must die as well.
        {
            setDead();
            return;
        }
        //List of the locations near the victim in the infection range of the Disease
        List<Location> potentialVictimsLocations = getField().adjacentLocations(victim.getLocation(), diseaseType.getInfectRange());
        for(Location location: potentialVictimsLocations)
        {
            Set<AnimalPlant> AnimalPlantSet = getField().getAnimalAndPlantSetAt(location); // Set of the Animal and the Plant at a given location
            for (AnimalPlant animalPlant : AnimalPlantSet)
            {
                if(animalPlant != null && attemptInfection(animalPlant))
                {
                    Disease newDisease = new Disease(getField(), infectionSet, animalPlant, diseaseType);
                    newDiseases.add(newDisease);
                }
            }
        }
    }

    /**
     * Attemps to kill the victim.
     * The disease either successfully kills the victim causing it to have no host and so dying its self or the victim is cured of the Disease causing the Disese to die,
     * either way the Disease will die.
     */
    protected void attemptKill()
    {
        if (diseaseType.getKillProbability() > rand.nextDouble())
        {
            kill();
        }
        victim.removeInfection(diseaseType);
        setDead();
    }

    /**
     * Kills the victim
     */
    protected void kill()
    {
        victim.setDead();
    }
    
    /**
     * Returns whether the disease succesfully infects a chosen victim.
     * @param potentialVictim The AnimalPlant that the disease is trying to infect.
     * @return true if the infection is succesfull, false if it fails.
     */
    protected boolean attemptInfection(AnimalPlant potentialVictim)
    {
        // checks if the potential victim is of a class that can infected by this disease and that it isnt already infected by this disease
        if (!infectable(potentialVictim) || potentialVictim.infected(diseaseType))
        {
            return false;
        }
        double finalInfectionProbability = (1 - potentialVictim.getIllnessResistance()) * diseaseType.getInfectionProbability();
        return finalInfectionProbability > rand.nextDouble();
    }

    /**
     * Tests if the given AnimalPlant is of a Class that can be infected by this Disease.
     * @return true if the AnimalPlant is of a Class that can be infected, false if its not.
     */
    private boolean infectable(AnimalPlant potentialVictim)
    {
        for (LifeEnumInterface victimType : infectionSet)
        {
            if(victimType == potentialVictim.getType())
            {
                return true;
            }
        }
        return false;
    }
    
    public LifeEnumInterface getType()
    {
        return diseaseType;
    }
}
