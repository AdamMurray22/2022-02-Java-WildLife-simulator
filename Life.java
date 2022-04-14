import java.util.List;

/**
 * This class contains a few common fields and methods from Animal, Plant and Disease,
 * however its real purpose is to allow for other classes to have a single point of access to all life, this point of access is threw the act method.
 *
 * @author Adam Murray and Ryan Yan
 * @version 2022.02.28
 */
public abstract class Life
{
    private boolean alive; //true for alive, false for dead
    private int age; // number of steps since the life was created
    private Field field;
    
    /**
     * Constructor for class life.
     * @param age The starting age for this life.
     * @param field The field that the simulator is using.
     */
    public Life(int age, Field field)
    {
        alive = true;
        this.age = age;
        this.field = field;
    }
    
    /**
     * Act is the point of access for other classes to call upon any form of life to perform there actions.
     * @param newLife A list that all new life created is added to.
     * @param environmentConditions An instance of EnvironmentConditions that tells the life what the time and weather currently is.
     */
    public abstract void act(List<Life> newLife, EnvironmentConditions environmentConditions);
    
    /**
     * Returns whether the life is still alive.
     * @return true if still alive, false if dead.
     */
    protected boolean isAlive()
    {
        return alive;
    }
    
    /**
     * Sets alive to false, causing the animal to be considered dead by all other classes.
     */
    protected void setDead()
    {
        alive = false;
    }

    /**
     * Returns age, the number of steps since the life was created.
     * @return age, the number of steps since the life was created.
     */
    protected int getAge()
    {
        return age;
    }
    
    /**
     * increments the age by 1.
     */
    protected void incrementAge()
    {
        ++age;
    }
    
    /**
     * Returns the field.
     * @return The field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Sets the field to a new field, this value can be null.
     * @param field The field that is currently being used by the simulator, can be null for when the life is dead.
     */
    protected void setField(Field field)
    {
        this.field = field;
    }
    
    public abstract LifeEnumInterface getType();
}
