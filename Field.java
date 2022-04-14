import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single animal.
 * 
 * @author David J. Barnes and Michael Kölling, Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class Field
{
    // A random number generator for providing random locations.
    private static final Random rand = Randomizer.getRandom();


    // The depth and width of the field.
    private int depth, width;
    // Storage for the animals.
    private AnimalAndPlantContainer[][] fieldLocations;
    // List containing ongoing natural disasters.
    private List<NaturalDisaster> currentDisasters;

    /**
     * Represent a field of the given dimensions.
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public Field(int depth, int width, List<NaturalDisaster> listOfDisasters)
    {
        this.depth = depth;
        this.width = width;
        fieldLocations = new AnimalAndPlantContainer[depth][width];
        currentDisasters = listOfDisasters;
        createAnimalAndPlantContainers();
    }
    
    /**
     * Resets the field, clearing all locations and assigning an Animal&Plant container to each location.
     */
    public void reset()
    {
        clear();
        createAnimalAndPlantContainers();
    }
    
    /**
     * Allocates an Animal&Plant container to every location on the field.
     */
    private void createAnimalAndPlantContainers()
    {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                fieldLocations[row][col] = new AnimalAndPlantContainer();
            }
        }
    }

    /**
     * Empty the field, setting all locations to null.
     */
    private void clear()
    {
        for(int row = 0; row < depth; row++) {
            for(int col = 0; col < width; col++) {
                fieldLocations[row][col] = null;
            }
        }
    }

    /**
     * Clears the animal at given location.
     * @param location The location to clear the animal from.
     */
    public void clearAnimal(Location location)
    {
        fieldLocations[location.getRow()][location.getCol()].setAnimal(null);
    }

    /**
     * Clears the plant at given location.
     * @param location The location to clear the plant from.
     */
    public void clearPlant(Location location)
    {
        fieldLocations[location.getRow()][location.getCol()].setPlant(null);
    }
    
    /**
     * Places given animal at given location.
     * @param animal The animal to be placed.
     * @param location The location to place the animal in.
     */
    public void placeAnimal(Animal animal, Location location)
    {
        fieldLocations[location.getRow()][location.getCol()].setAnimal(animal);
    }
    
    /**
     * Places given plant at given location
     * @param plant The plant to be placed.
     * @param location The location to place the plant in.
     */
    public void placePlant(Plant plant, Location location)
    {
        fieldLocations[location.getRow()][location.getCol()].setPlant(plant);
    }

    /**
     * Place given animal at the given location.
     * If there is already an animal at the location it will
     * be lost.
     * @param animal The animal to be placed.
     * @param row Row coordinate of the location.
     * @param col Column coordinate of the location.
     */
    public void placeAnimal(Animal animal, int row, int col)
    {
        Location location = new Location(row, col);
        fieldLocations[row][col].setAnimal(animal);
        animal.setLocation(location);
    }

    /**
     * Place given plant at the given location.
     * If there is already a plant at the location it will be lost.
     * @param plant The plant to be placed.
     * @param row Row coordinate of the location.
     * @param col Column coordinate of the location.
     */
    public void placePlant(Plant plant, int row, int col)
    {
        Location location = new Location(row, col);
        fieldLocations[row][col].setPlant(plant);
        plant.setLocation(location);
    }


    /**
     * Return the animal at the given location, if any.
     * @param location Where in the field.
     * @return The animal at the given location, or null if there is none.
     */
    public Animal getAnimalAt(Location location)
    {
        return fieldLocations[location.getRow()][location.getCol()].getAnimal();
    }

    /**
     * Return the plant at the given location, if any.
     * @param location Where in the field
     * @return The plant at the given location, or null if there is none.
     */
    public Plant getPlantAt(Location location)
    {
        return fieldLocations[location.getRow()][location.getCol()].getPlant();
    }
    
    /**
     * Returns the set containing both animal and plant at the given location, if any.
     * @param location The location from which animals and plants are to be returned.
     * @return The set containing both animal and plant at the given location, or null if there is none.
     */
    public Set<AnimalPlant> getAnimalAndPlantSetAt(Location location)
    {
        return fieldLocations[location.getRow()][location.getCol()].getAnimalAndPlantSet();
    }

    /**
     * Return the animal at the given location, if any.
     * @param row The desired row.
     * @param col The desired column.
     * @return The animal at the given location, or null if there is none.
     */
    public Animal getAnimalAt(int row, int col)
    {
        if (fieldLocations[row][col] == null)
        {
            return null;
        }
        return fieldLocations[row][col].getAnimal();
    }

    /**
     * Return the plant at the given location, if any.
     * @param row The desired row.
     * @param col The desired column.
     * @return The plant at the given location, or null if there is none.
     */
    public Plant getPlantAt(int row, int col)
    {
        if (fieldLocations[row][col] == null)
        {
            return null;
        }
        return fieldLocations[row][col].getPlant();
    }

    /**
     * Returns the location with certain coordinates.
     * @param row The desired row,
     * @param col The desired column.
     * @return The location at the given coordinates.
     */
    public Location getLocationAt(int row, int col)
    {
        return new Location(row, col);
    }

    /**
     * Generate a random location that is adjacent to the
     * given location, or is the same location.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @param range The maximum distance between the given location and its adjacent locations.
     * @return A valid location within the grid area.
     */
    public Location randomAdjacentLocation(Location location, int range)
    {
        List<Location> adjacent = adjacentLocations(location, range);
        return adjacent.get(0);
    }
    
    /**
     * Filters an original list, removing locations that were overlapping with the filter list, then return it.
     * @param originalList The original list containing locations, if any locations overlap with 
     *                     locations in filterList, remove it from originalList.
     *                     
     * @param filterList The list containing locations to be filtered out.
     * @return The original list after filtering out overlapping locations.
     */
    private List<Location> filterLocations(List<Location> list1, List<Location> list2)
    {
        if (list2 == null || list1 == null)
        {
            return list1;
        }
        for (Iterator<Location> locationIterator = list1.iterator(); locationIterator.hasNext();)
        {
            Location location1 = locationIterator.next();
            for (Location location2 : list2)
            {
                if (location1.equals(location2))
                {
                    locationIterator.remove();
                    break;
                }
            }
        }
        return list1;
    }
    
    /**
     * Obtain & returns a list of unspawnable locations that targets the given class's spawning capabilities.
     * @param targetClass The class which has its spawning capabilities 
     *                    impaired by the natural disaster's aftermath.
     * @return The list of locations where the given object class can't spawn upon.
     */
    private List<Location> getUnspawnableLocations(Class targetClass)
    {
        List<Location> unspawnableLocations = new ArrayList<>();
        for (NaturalDisaster disaster : currentDisasters)
        {
            List<Location> disasterLocations = disaster.getUnspawnableLocations(targetClass);
            if (disasterLocations == null)
            {
                disasterLocations = new ArrayList<>();
            }
            unspawnableLocations.addAll(disasterLocations);
        }
        return unspawnableLocations;
    }
    
    /**
     * Obtain & returns a list of spawnable locations for the target class,
     * via filtering its adjacent locations against unspawnable locations.
     * @param location The location which will act as the centre point.
     * @param range The maximum distance between the given location and its adjacent locations.
     * @param targetClass The class of which its spawnability will be tested against the locations.
     * @return The list containing locations which are spawnable for the target class's offsprings.
     */
    public List<Location> spawnableAdjacentLocations(Location location, int range, Class targetClass)
    {
        List<Location> free = getFreeAdjacentLocations(location, range, targetClass);
        List<Location> unspawnableLocations = getUnspawnableLocations(targetClass);
        
        free = filterLocations(free, unspawnableLocations);
        return free;
    }

    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @param range The maximum distance between the given location and its adjacent locations.
     * @param targetClass The class to obtain free adjacent locations for.
     * @return A list of free adjacent locations.
     */
    public List<Location> getFreeAdjacentLocations(Location location, int range, Class targetClass)
    {
        List<Location> free = new LinkedList<>();
        List<Location> adjacent = adjacentLocations(location, range);
        for(Location nextLocation : adjacent) {
            AnimalAndPlantContainer next = fieldLocations[nextLocation.getRow()][nextLocation.getCol()];
            if (targetClass == Animal.class)
            {
                if(next.getAnimal() == null){
                    free.add(nextLocation);
                }
            }
            else if (targetClass == Plant.class)
            {
                if(next.getPlant() == null){
                    free.add(nextLocation);
                }
            }
        }
        return free;
    }

    /**
     * Try to find a free location that is adjacent to the
     * given location. If there is none, return null.
     * The returned location will be within the valid bounds
     * of the field.
     * @param location The location from which to generate an adjacency.
     * @param range The maximum distance between the given location and its adjacent locations.
     * @param targetClass The class to obtain free adjacent locations for.
     * @return A valid location within the grid area.
     */
    public Location freeAdjacentLocation(Location location, int range, Class targetClass)
    {
        // The available free ones.
        List<Location> free = getFreeAdjacentLocations(location, range, targetClass);
        if(free.size() > 0) {
            return free.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param location The location from which to generate adjacencies.
     * @param range The maximum distance between the given location and its adjacent locations.
     * @return A list of locations adjacent to that given.
     */
    public List<Location> adjacentLocations(Location location, int range)
    {
        assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<>();

        if(location != null) {
            int row = location.getRow();
            int col = location.getCol();

            for(int roffset = -(range); roffset <= range; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -(range); coffset <= range; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            Location nextLocation = getLocationAt(nextRow, nextCol);
                            if (nextLocation == null)
                            {
                                nextLocation = new Location(nextRow, nextCol);
                            }
                            locations.add(nextLocation);
                        }
                    }
                }
            }

            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    /**
     * Return the depth of the field.
     * @return The depth of the field.
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * Return the width of the field.
     * @return The width of the field.
     */
    public int getWidth()
    {
        return width;
    }
}
