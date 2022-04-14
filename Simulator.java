import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.util.Set;
import java.util.HashMap;

/**
 * A simple wildlife simulator, based on a rectangular field
 * containing animals, plants, diseases and natural disasters.
 * 
 * @author David J. Barnes and Michael Kölling, Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Simulator
{

    // List of animals in the field.
    private List<Life> lifeList;
    
    private List<NaturalDisaster> disasterList;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    private SimulatorBuilder simBuilder;
    
    private HashMap<DiseaseEnum, Set<LifeEnumInterface>> diseaseInfectionMap;
    
    private static final Random rand = Randomizer.getRandom();
    
    private EnvironmentConditions environmentConditions;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        simBuilder = new SimulatorBuilder(this);
        getBuiltSim();
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        simBuilder = new SimulatorBuilder(depth, width, this);
        getBuiltSim();
    }
    
    /**
     * Obtains the data and values needed to construct the simulator from the SimulatorBuilder class.
     * e.g. lifeList - contains all lives on the field.
     *      diseaseInfectionMap - contains all possible diseases and their potential victims.
     */
    private void getBuiltSim()
    {
        view = simBuilder.getView();
        lifeList = simBuilder.getLifeList();
        field = simBuilder.getField();
        disasterList = simBuilder.getDisasterList();
        diseaseInfectionMap = simBuilder.getDiseaseInfectionMap();
        
        environmentConditions = new EnvironmentConditions();        
        
        view.showStatus(step, field);
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            // delay(60);   // uncomment this to run more slowly
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each life, weather, time
     * and potential natural disasters.
     */
    public void simulateOneStep()
    {
        step++;
        // Provide space for newborn animals.
        List<Life> newLives = new ArrayList<>();        
        environmentConditions.incrementConditions();
        // Let all rabbits act.
        for(Iterator<Life> it = lifeList.iterator(); it.hasNext(); ) {
            Life life = it.next();
            randomInfection(life, newLives);
            life.act(newLives, environmentConditions);
            if(!life.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        lifeList.addAll(newLives);
        
        NaturalDisaster newDisaster = randomDisaster();
        if (newDisaster != null)
        {
            disasterList.add(newDisaster);
        }
        for (Iterator<NaturalDisaster> disasterIt = disasterList.iterator(); disasterIt.hasNext();)
        {
            NaturalDisaster disaster = disasterIt.next();
            disaster.progress();
            if (!disaster.isOnGoing())
            {
                disasterIt.remove();
            }
        }
        
        
        view.showStatus(step, field);
    }
    
    /**
     * Takes a life object and checks if it's vulnerable to any diseases, if so,
     * calls a method attempting to infect it.
     * 
     * @param life The life object that is targetted for infection.
     * @param newLives The list containing all new lives (animals, plants, diseases) created from simulating a step.
     */
    private void randomInfection(Life life, List<Life> newLives)
    {
        if (life instanceof Disease)
        {
            return;
        }
        AnimalPlant animalPlant = (AnimalPlant) life;
        for (DiseaseEnum disease : diseaseInfectionMap.keySet())
        {
            for (LifeEnumInterface animalPlantType : diseaseInfectionMap.get(disease))
            {
                if (animalPlantType == animalPlant.getType())
                {
                    Disease newDisease = createInfection(disease, animalPlant);
                    if (newDisease != null)
                    {
                        newLives.add(newDisease);
                    }
                }
            }
        }
    }
    
    /**
     * Attempts to infect a life with diseases it's vulnerable to.
     * 
     * @param diseaseClass The class object of the disease.
     * @param life The life object that is targetted for infection.
     * @return The created disease if the infection was successful, or null if none created.
     */
    private Disease createInfection(DiseaseEnum diseaseType, AnimalPlant life)
    {
        Disease newDisease;
        
        if (diseaseType == DiseaseEnum.CornPox && DiseaseEnum.CornPox.getRandomInfectionProbability() > rand.nextDouble() && !life.infected(diseaseType))
        {
            return newDisease = new Disease(field, diseaseInfectionMap.get(diseaseType), life, DiseaseEnum.CornPox);
        }
        if (diseaseType == DiseaseEnum.ChickenPox && DiseaseEnum.ChickenPox.getRandomInfectionProbability() > rand.nextDouble() && !life.infected(diseaseType))
        {
            return newDisease = new Disease(field, diseaseInfectionMap.get(diseaseType), life, DiseaseEnum.ChickenPox);
        }
        return null;
    }
    
    /**
     * Attempts to introduce a natural disaster onto the field randomly.
     * @return The natural disaster if it was created.
     */
    private NaturalDisaster randomDisaster()
    {
        NaturalDisaster newDisaster;
        if (Meteor.getRandomSpawnProbability() > rand.nextDouble())
        {
            Location location = new Location(rand.nextInt(field.getDepth()), rand.nextInt(field.getWidth()));
            return newDisaster = new Meteor(field, location);
        }
        else if (Earthquake.getRandomSpawnProbability() > rand.nextDouble())
        {
            Location location = new Location(rand.nextInt(field.getDepth()), rand.nextInt(field.getWidth()));
            return newDisaster = new Earthquake(field, location);
        }
        else if (Flood.getRandomSpawnProbability() > rand.nextDouble())
        {
            Location location = new Location(rand.nextInt(field.getDepth()), rand.nextInt(field.getWidth()));
            return newDisaster = new Flood(field, location);
        }
        return null;
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        lifeList.clear();
        simBuilder.reset();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}

