import java.util.Set;
import java.util.HashSet;
/**
 * A container class that holds an Animal object and a Plant object.
 * 
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public class AnimalAndPlantContainer
{
    private Animal animal;
    private Plant plant;
    
    /**
     * Constructor for objects of class AnimalAndPlantContainer
     */
    public AnimalAndPlantContainer()
    {
        
    }
    
    /**
     * Returns the animal in the container.
     * @return The animal in the container.
     */
    public Animal getAnimal()
    {
        return animal;
    }
    
    /**
     * Returns the plant in the container.
     * @return The plant in the container.
     */
    public Plant getPlant()
    {
        return plant;
    }
    
    /**
     * Binds the animal to this instance.
     * @param animal Animal to be bound to this instance.
     */
    public void setAnimal(Animal animal)
    {
        this.animal = animal;
    }
    
    /**
     * Binds the plant to this instance.
     * @param plant Plant to be bound to this instance.
     */
    public void setPlant(Plant plant)
    {
        this.plant = plant;
    }
    
    /**
     * Create and returns a set containing both the animal and the plant in this container.
     * @return A set containing both the animal and the plant in this container.
     */
    public Set<AnimalPlant> getAnimalAndPlantSet()
    {
        Set<AnimalPlant> animalPlantSet = new HashSet<>();
        animalPlantSet.add(animal);
        animalPlantSet.add(plant);
        return animalPlantSet;
    }

}
