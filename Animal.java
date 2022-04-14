import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

/**
 * This class contains most of the methods of a animal and threw this class acts out what a animal does
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Animal extends AnimalPlant
{
    private static final Random rand = Randomizer.getRandom();

    private Set<LifeEnumInterface> foodSet;
    
    private boolean sex; // true if female, false if male.
    private int currentHunger;

    private int breedingRangeModifier;
    private int huntingRangeModifier;
    private int movementRangeModifier;
    
    private int currentBreedingCooldown;
    
    
    private AnimalWeatherModifiers weatherModifiers;
    
    private AnimalEnum animalType;

    /**
     * Constructor for objects of class Animal
     * @param age the initial age of the Animal.
     * @param field The Field used by the simulator.
     * @param location The inital location of the Animal.
     * @param currentHunger The initial hunger of the Animal.
     * @param foodSet The Classes of the AnimalPlants that this Animal can eat.
     */
    public Animal(Field field, Location location, Set<LifeEnumInterface> foodSet, AnimalEnum animalType)
    {
        super(rand.nextInt(animalType.getMaxAge()), field, location);
        this.foodSet = foodSet;
        sex = (rand.nextInt(2) == 1); // rand returns 0 or 1, 1 is female, 0 is male
        currentHunger = rand.nextInt(animalType.getFoodCapacity() / 2) + (animalType.getFoodCapacity()/2) + 1;
        this.animalType = animalType;

        weatherModifiers = new AnimalWeatherModifiers();

        setLocation(location);
    }
    
    private Animal(Field field, Location location, int currentHunger, Set<LifeEnumInterface> foodSet, AnimalEnum animalType)
    {
        super(0, field, location);
        this.foodSet = foodSet;
        sex = (rand.nextInt(2) == 1); // rand returns 0 or 1, 1 is female, 0 is male
        this.currentHunger = currentHunger;
        this.animalType = animalType;

        weatherModifiers = new AnimalWeatherModifiers();

        setLocation(location);
    }
    
    /**
     * This method acts out what the animal does each step,
     * It ages, gets hungry, hunts, breads and moves.
     * @param new Animals The list of new Animals that are created are added to this list.
     * @param environmentConditions An instance of EnvironmentConditions that tells the Plant what the time and weather currently is. 
     */
    public void act(List<Life> newAnimals, EnvironmentConditions environmentConditions)
    {
        incrementAge();
        incrementHunger();
        incrementBreedingCooldown();
        if(isAlive()) {
            resetModifiers();
            if (environmentConditions.isDay())
            {
                addToBreedingRangeModifier(animalType.getDayBreedingModifier());
                addToMovementRangeModifier(animalType.getDayMovementModifier());
                addToHuntingRangeModifier(animalType.getDayHuntingModifier());
            }
            else
            {
                addToBreedingRangeModifier(animalType.getNightBreedingModifier());
                addToMovementRangeModifier(animalType.getNightMovementModifier());
                addToHuntingRangeModifier(animalType.getNightHuntingModifier());
            }
            
            weatherBehaviour(environmentConditions.getWeather());
            
            if (isFemale() && getCurrentHunger() > 1 && findMate() && getCurrentBreedingCooldown() == 0)
            {
                giveBirth(newAnimals);
            }
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getMovementLocation();
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
        }
    }
    
    /**
     * Check whether or not this Animal is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newAnimals A list to return newly born animals.
     */
    private void giveBirth(List<Life> newAnimals)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.spawnableAdjacentLocations(getLocation(), animalType.getBreedingRange(), this.getClass());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location spawnLocation = free.remove(0);
            Animal young = new Animal(getField(), spawnLocation, getCurrentHunger(), getFoodSet(), animalType);
            newAnimals.add(young);
        }
        if (births > 0)
        {
            incrementHunger();
        }
    }

    /**
     * This method searches the surrounds and returns whether there is a potential mate nearby.
     * @return true if a mate is found, false is a mate is not found.
     */
    protected boolean findMate()
    {
        int currentBreedingRange = animalType.getBreedingRange() + breedingRangeModifier;
        if (currentBreedingRange < 0)
        {
            currentBreedingRange = 0;
        }
        List<Location> listOfLocations = getField().adjacentLocations(getLocation(), currentBreedingRange);
        for (Location nearLocation : listOfLocations)
        {
            Animal mate = getField().getAnimalAt(nearLocation);
            if((mate != null) && (mate.getType() == animalType) && (isFemale() != mate.isFemale()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Applies the weather modifiers.
     * @param weather The current weather.
     */
    protected void weatherBehaviour(Weather weather)
    {
        weatherModifiers = weather.animalWeatherAction(animalType);
        addToBreedingRangeModifier(weatherModifiers.getBreedingModifier());
        addToMovementRangeModifier(weatherModifiers.getMovementModifier());
        addToHuntingRangeModifier(weatherModifiers.getHuntingModifier());
    }

    /**
     * The location the Animal is moving to, Null if non is found.
     * @return The Location the animal is moving to, Null if non is found.
     */
    protected Location getMovementLocation()
    {
        int currentMovementRange = animalType.getMovementRange() + movementRangeModifier;
        if (currentMovementRange < 0)
        {
            currentMovementRange = 0;
        }
        return getField().freeAdjacentLocation(getLocation(), currentMovementRange, Animal.class);
    }

    /**
     * Returns the sex of the animal, true if female, false if male.
     * @return The sex of the animal, true if female, false if male.
     */
    public boolean isFemale()
    {
        //true = female, false = male
        return sex;
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canReproduce() && rand.nextDouble() <= animalType.getBreedingProbability()) {
            births = rand.nextInt(animalType.getMaxLitterSize()) + 1;
            setBreedingCooldown(animalType.getMaxBreedingCooldown());
        }
        return births;
    }

    /**
     * Look for food adjacent to the current location.
     * Only the first live food is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    protected Location findFood()
    {
        if((float)currentHunger >= ((float)animalType.getFoodCapacity()) * 0.9f) // returns null if food capacity is above 90%
        {
            return null;
        }
        
        Field field = getField();
        int currentHuntingRange = animalType.getHuntingRange() + huntingRangeModifier;
        if (currentHuntingRange < 0)
        {
            currentHuntingRange = 0;
        }
        List<Location> adjacent = field.adjacentLocations(getLocation(), currentHuntingRange);
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {  // Iterates through the adjcent locations
            Location where = it.next();
            List<AnimalPlant> potentialFoodList = new ArrayList<>(); //Contains the Animal and the Plant at a given location, null if there is no animal/plant
            potentialFoodList.add(field.getAnimalAt(where));
            potentialFoodList.add(field.getPlantAt(where));
            for (int i = 0; i < potentialFoodList.size(); i++) //Loops through the Animal and Plant at each location
            {
                AnimalPlant potentialFood = potentialFoodList.get(i);
                if(potentialFood != null)
                {
                    LifeEnumInterface animalPlantType = potentialFood.getType(); 
                    for(LifeEnumInterface foodType : foodSet) // compares the Animal/Plant to this animals foodSet
                    {
                        if (potentialFood.getClass() == Plant.class && potentialFoodList.get(0) != null)
                        {
                            break;
                        }
                        if(foodType == animalPlantType) {
                            if(potentialFood.isAlive()) { 
                                potentialFood.setDead();
                                currentHunger += potentialFood.getFoodValue();
                                if(currentHunger > animalType.getFoodCapacity())
                                {
                                    currentHunger = animalType.getFoodCapacity();
                                }
                                return where;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        if (!isAlive())
        {
            return;
        }
        super.setDead();
        if(getLocation() != null) {
            getField().clearAnimal(getLocation());
            setField(null);
        }
    }

    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(getLocation() != null) {
            getField().clearAnimal(getLocation());
        }
        super.setLocation(newLocation);
        getField().placeAnimal(this, newLocation);
    }

    /**
     * Returns the animals foodSet.
     * @return The animals foodSet.
     */
    protected Set<LifeEnumInterface> getFoodSet()
    {
        return foodSet;
    }

    /**
     * Make this animal more hungry. This could result in the animal's death.
     */
    protected void incrementHunger()
    {
        currentHunger--;
        if(currentHunger <= 0) {
            setDead();
        }
    }

    /**
     * Returns the animals hunger.
     * @return The animals hunger.
     */
    protected int getCurrentHunger()
    {
        return currentHunger;
    }

    /**
     * Resets the modifiers.
     */
    protected void resetModifiers()
    {
        movementRangeModifier = 0;
        breedingRangeModifier = 0;
        huntingRangeModifier = 0;
    }

    /**
     * Adds the given modifier to the movement modifier.
     * @param modifier The value to be added to the movement modifier.
     */
    protected void addToMovementRangeModifier(int modifier)
    {
        movementRangeModifier += modifier;
    }

    /**
     * Adds the given modifier to the hunting modifier.
     * @param modifier The value to be added to the hunting modifier.
     */
    protected void addToBreedingRangeModifier(int modifier)
    {
        breedingRangeModifier += modifier;
    }

    /**
     * Adds the given modifier to the hunting modifier.
     * @param modifier The value to be added to the hunting modifier.
     */
    protected void addToHuntingRangeModifier(int modifier)
    {
        huntingRangeModifier += modifier;
    }
    
    /**
     * Sets the current breeding cooldown to its max value.
     * @param breedingCooldown The max breeding cooldown value.
     */
    protected void setBreedingCooldown(int breedingCooldown)
    {
        currentBreedingCooldown = breedingCooldown;
    }
    
    /**
     * Reduces the breeding cooldown by 1.
     */
    protected void incrementBreedingCooldown()
    {
        if(currentBreedingCooldown > 0)
        {
            currentBreedingCooldown--;
        }
    }
    
    /**
     * Returns the current breeding cooldown.
     * @return The current breeding cooldown.
     */
    protected int getCurrentBreedingCooldown()
    {
        return currentBreedingCooldown;
    }
    
    /**
     * Returns the Bears resistance to illness.
     * @return The bears resistance to illness.
     */
    public double getIllnessResistance()
    {
        return animalType.getIllnessResistance();
    }
    
    /**
     * Returns the food value of the bear.
     * @return The food value of the bear.
     */
    protected int getFoodValue()
    {
        return animalType.getFoodValue();
    }
    
    /**
     * Returns the minimum age when a bear can breed.
     * @return The minimum age when a bear can breed.
     */
    protected int getMinReproduceAge()
    {
        return animalType.getMinReproduceAge();
    }
    
    /**
     * Returns the Max age of the Bear.
     * @return The max age of the bear.
     */
    protected int getMaxAge()
    {
        return animalType.getMaxAge();
    }
    
    public LifeEnumInterface getType()
    {
        return animalType;
    }
}
