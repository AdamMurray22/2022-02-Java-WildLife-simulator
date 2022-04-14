import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

/**
 * A builder for initialising & setting up the simulation on launch.
 * 
 * 
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */

//POPULATE()
public class SimulatorBuilder
{

    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 180;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 120;

    // Constants representing configuration information for the simulation.
    // The probability that a fox will be created in any given grid position.
    private static final double BEAR_CREATION_PROBABILITY = 0.0065;
    private static final double TIGER_CREATION_PROBABILITY = 0.006;
    private static final double CHICKEN_CREATION_PROBABILITY = 0.05;
    private static final double WORM_CREATION_PROBABILITY = 0.1;
    private static final double SHEEP_CREATION_PROBABILITY = 0.05;
    
    private static final double GRASS_CREATION_PROBABILITY = 0.4;
    private static final double CORN_CREATION_PROBABILITY = 0.45;

    private static Set<LifeEnumInterface> BEAR_FOOD_SET;
    private static Set<LifeEnumInterface> CHICKEN_FOOD_SET;
    private static Set<LifeEnumInterface> TIGER_FOOD_SET;
    private static Set<LifeEnumInterface> WORM_FOOD_SET;
    private static Set<LifeEnumInterface> SHEEP_FOOD_SET;

    private static HashMap<DiseaseEnum, Set<LifeEnumInterface>> diseaseInfectionMap;
    
    private static Set<LifeEnumInterface> CHICHKENPOX_INFECTION_SET;
    private static Set<LifeEnumInterface> CORNPOX_INFECTION_SET;

    // List of animals in the field.
    private List<Life> lifeList;
    
    private List<NaturalDisaster> disasterList;
    // The current state of the field.
    private Field field;
    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Construct a simulation field with default size.
     */
    public SimulatorBuilder(Simulator simulator)
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH, simulator);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public SimulatorBuilder(int depth, int width, Simulator simulator)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        lifeList = new ArrayList<>();
        disasterList = new ArrayList<>();
        field = new Field(depth, width, disasterList);

        view = new SimulatorView(depth, width, simulator);
        populateSimulatorViewColours();
        // Create a view of the state of each location in the field.   

        populateFoodSets();
        
        populateInfectionSets();

        // Setup a valid starting point.
        reset();
    }

    /**
     * Assigns each plant and animal their unique colour.
     */
    private void populateSimulatorViewColours()
    {
        view.setColor(AnimalEnum.Bear, Color.ORANGE); 
        view.setColor(AnimalEnum.Chicken, Color.BLUE);
        view.setColor(AnimalEnum.Tiger, Color.RED);
        view.setColor(AnimalEnum.Worm, Color.PINK);
        view.setColor(AnimalEnum.Sheep, Color.BLACK);
        
        view.setColor(PlantEnum.Grass, Color.GREEN);
        view.setColor(PlantEnum.Corn, Color.YELLOW);
    }

    /**
     * Assigns each animal their food source.
     */
    private void populateFoodSets()
    {
        BEAR_FOOD_SET = new HashSet();
        BEAR_FOOD_SET.add(AnimalEnum.Chicken);
        BEAR_FOOD_SET.add(AnimalEnum.Sheep);

        TIGER_FOOD_SET = new HashSet();
        TIGER_FOOD_SET.add(AnimalEnum.Chicken);
        TIGER_FOOD_SET.add(AnimalEnum.Sheep);


        CHICKEN_FOOD_SET = new HashSet();
        CHICKEN_FOOD_SET.add(AnimalEnum.Worm);
        CHICKEN_FOOD_SET.add(PlantEnum.Corn);
        
        WORM_FOOD_SET = new HashSet();
        WORM_FOOD_SET.add(PlantEnum.Grass);
        
        SHEEP_FOOD_SET = new HashSet();
        SHEEP_FOOD_SET.add(PlantEnum.Grass);

    }
    
    /**
     * Assigns each disease their potential victims.
     */
    private void populateInfectionSets()
    {
        diseaseInfectionMap = new HashMap<>();
        
        CHICHKENPOX_INFECTION_SET = new HashSet();
        CHICHKENPOX_INFECTION_SET.add(AnimalEnum.Chicken);
        
        CORNPOX_INFECTION_SET = new HashSet<>();
        CORNPOX_INFECTION_SET.add(PlantEnum.Corn);
        
        diseaseInfectionMap.put(DiseaseEnum.ChickenPox, CHICHKENPOX_INFECTION_SET);
        diseaseInfectionMap.put(DiseaseEnum.CornPox, CORNPOX_INFECTION_SET);
    }

    /**
     * Randomly populate the field with animals and plants.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.reset();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal bear = new Animal(field, location, BEAR_FOOD_SET, AnimalEnum.Bear);
                    field.placeAnimal(bear, location);
                    lifeList.add(bear);
                }
                else if(rand.nextDouble() <= TIGER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal tiger = new Animal(field, location, TIGER_FOOD_SET, AnimalEnum.Tiger);
                    field.placeAnimal(tiger, location);
                    lifeList.add(tiger);
                }
                else if(rand.nextDouble() <= CHICKEN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal chicken = new Animal(field, location, CHICKEN_FOOD_SET, AnimalEnum.Chicken);
                    field.placeAnimal(chicken, location);
                    lifeList.add(chicken);
                }
                else if(rand.nextDouble() <= WORM_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal worm = new Animal(field, location, WORM_FOOD_SET, AnimalEnum.Worm);
                    field.placeAnimal(worm, location);
                    lifeList.add(worm);
                }
                else if(rand.nextDouble() <= SHEEP_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Animal sheep = new Animal(field, location, SHEEP_FOOD_SET, AnimalEnum.Sheep);
                    field.placeAnimal(sheep, location);
                    lifeList.add(sheep);
                }
                
                
                if(rand.nextDouble() <= GRASS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Plant grass = new Plant(field, location, PlantEnum.Grass);
                    field.placePlant(grass, location);
                    lifeList.add(grass);
                }
                else if(rand.nextDouble() <= CORN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Plant corn = new Plant(field, location, PlantEnum.Corn);
                    field.placePlant(corn, location);
                    lifeList.add(corn);
                }
                // else leave the location empty
            }
        }
    }

    /**
     * Resets and repopulate the simulator.
     */
    public void reset()
    {
        populate();
    }

    /**
     * Returns the GUI of the simulator.
     * @return The GUI of the simulator.
     */
    public SimulatorView getView()
    {
        return view;
    }

    /**
     * Returns the field of the simulator.
     * @return The field of the simulator
     */
    public Field getField()
    {
        return field;
    }

    /**
     * Returns the list containing all lives in the simulator.
     * @return The list containing all lives in the simulator.
     */
    public List<Life> getLifeList()
    {
        return lifeList;
    }
    
    /**
     * Returns the hashmap mapping animals and diseases they're vulnerable to.
     * @return The Hashmap mapping animals and diseases they're vulnerable to.
     */
    public HashMap<DiseaseEnum, Set<LifeEnumInterface>> getDiseaseInfectionMap()
    {
        return diseaseInfectionMap;
    }
    
    /**
     * Returns the list containing ongoing natural disasters in the simulator.
     * @return The list containing ongoing natural disasters in the simulator.
     */
    public List<NaturalDisaster> getDisasterList()
    {
        return disasterList;
    }
}
