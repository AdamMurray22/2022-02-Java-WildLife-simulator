

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FieldTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FieldTest
{
    private Field field1;

    /**
     * Default constructor for test class FieldTest
     */
    public FieldTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        field1 = new Field(50, 50, null);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void constructor_test()
    {
        Field field2 = new Field(60, 60, null);
    }

    @Test
    public void adjacent_test()
    {
        Location location2 = new Location(22, 22);
        assertNotNull(field1.randomAdjacentLocation(location2, 3));        
        assertNotNull(field1.adjacentLocations(location2, 3));
        assertNotNull(field1.freeAdjacentLocation(location2, 3, Plant.class));
        assertNotNull(field1.freeAdjacentLocation(location2, 3, Animal.class));
        assertNotNull(field1.getFreeAdjacentLocations(location2, 2, Plant.class));
        assertNotNull(field1.getFreeAdjacentLocations(location2, 2, Animal.class));
    }
    
    @Test
    public void place_lives_test()
    {
        Location location2 = new Location(22, 22);
        Plant grass2 = new Plant(field1, location2, PlantEnum.Grass);
        Animal worm1 = new Animal(field1, location2, null, AnimalEnum.Worm);
        Location location3 = new Location(23, 23);
        Plant corn1 = new Plant(field1, location3, PlantEnum.Corn);
        Animal sheep1 = new Animal(field1, location3, null, AnimalEnum.Sheep);
        field1.placeAnimal(worm1, location2);
        field1.placePlant(grass2, location2);
        field1.placeAnimal(sheep1, 23, 23);
        field1.placePlant(corn1, 23, 23);
    }
    
    @Test
    public void getters_test()
    {
        Location location2 = new Location(24, 24);
        Plant grass2 = new Plant(field1, location2, PlantEnum.Grass);
        Animal worm1 = new Animal(field1, location2, null, AnimalEnum.Worm);
        Location location3 = new Location(25, 25);
        Plant corn1 = new Plant(field1, location3, PlantEnum.Corn);
        Animal sheep1 = new Animal(field1, location3, null, AnimalEnum.Sheep);
        field1.placeAnimal(worm1, location2);
        field1.placePlant(grass2, location2);
        field1.placeAnimal(sheep1, 25, 25);
        field1.placePlant(corn1, 25, 25);
        
        assertSame(grass2, field1.getPlantAt(location2));
        assertSame(worm1, field1.getAnimalAt(location2));
        assertSame(grass2, field1.getPlantAt(24,24));
        assertSame(worm1, field1.getAnimalAt(24,24));
        
        assertNotNull(field1.getAnimalAndPlantSetAt(location2));
        
        assertSame(50, field1.getDepth());
        assertSame(50, field1.getWidth());
        
        assertEquals(location2, field1.getLocationAt(24,24));
    }
    
    @Test
    public void clear_lives_test()
    {
        Location location2 = new Location(2, 2);
        Plant grass2 = new Plant(field1, location2, PlantEnum.Grass);
        
        field1.clearAnimal(location2);
        field1.clearPlant(location2);
    }
    
    @Test
    public void reset_test()
    {
        field1.reset();
    }
}




