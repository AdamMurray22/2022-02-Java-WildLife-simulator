import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class NaturalDisaster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class NaturalDisaster
{
    private Location centre;
    private Field field;
    private int age;
    private boolean disasterOnGoing;
    
    /**
     * Constructor for objects of class NaturalDisaster
     */
    public NaturalDisaster(Field field, Location location)
    {
        this.field = field;
        centre = location;
        age = 0;
        disasterOnGoing = true;
    }
    
    public void progress()
    {
        incrementAge();
        if (age >= getAftermathTime())
        {
            disasterOnGoing = false;
        }
    }

    protected Field getField()
    {
        return field;
    }
    
    protected Location getCentre()
    {
        return centre;
    }
    
    protected void incrementAge()
    {
        age++;
    }
    
    protected int getAge()
    {
        return age;
    }
    
    protected boolean isOnGoing()
    {
        return disasterOnGoing;
    }
    
    public List<Location> getUnspawnableLocations(Class targetClass)
    {
        return null;
    }
    
    protected abstract int getAftermathTime();

}
