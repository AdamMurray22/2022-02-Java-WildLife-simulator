

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AnimalAndPlantContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AnimalAndPlantContainerTest
{
    private AnimalAndPlantContainer animalAn1;

    /**
     * Default constructor for test class AnimalAndPlantContainerTest
     */
    public AnimalAndPlantContainerTest()
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
        animalAn1 = new AnimalAndPlantContainer();
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
        AnimalAndPlantContainer animalAn1 = new AnimalAndPlantContainer();
    }

    @Test
    public void setter_test()
    {
        Field field1 = new Field(5, 5, null);
        Location location1 = new Location(1, 1);
        Plant corn1 = new Plant(field1, location1, PlantEnum.Corn);
        Animal worm1 = new Animal(field1, location1, null, AnimalEnum.Worm);
        animalAn1.setAnimal(worm1);
        animalAn1.setPlant(corn1);
    }

    @Test
    public void getters_test()
    {
        Field field1 = new Field(5, 5, null);
        Location location1 = new Location(1, 1);
        assertNull(animalAn1.getAnimal());
        assertNull(animalAn1.getPlant());
        Plant grass1 = new Plant(field1, location1, PlantEnum.Grass);
        Animal sheep1 = new Animal(field1, location1, null, AnimalEnum.Sheep);
        animalAn1.setAnimal(sheep1);
        animalAn1.setPlant(grass1);
        assertSame(sheep1, animalAn1.getAnimal());
        assertSame(grass1, animalAn1.getPlant());
        assertNotNull(animalAn1.getAnimalAndPlantSet());
    }
}



