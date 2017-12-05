package boebot;
import java.util.ArrayList;

/**
 * abstract Class Updatable
 * 
 * Holds all the project updates so we can update them all at once.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 28-11-2017 (Version 1.0)
 */
public abstract class Updatable
{
    private static ArrayList<Updatable> updatables = new ArrayList();
    
    /**
     * Updatable Constructor
     *
     * adds a new updatable to the string updatebles
     */
    public Updatable() {
        updatables.add(this);
    }
    
    public Updatable(boolean override) {
        if(!override) {
            updatables.add(this);
        }
    }
    
    /**
     * Method update
     * 
     * Letting all the updates update every millisecond
     */
    public abstract void update();
    
    /**
     * Method update
     * 
     * Update all updatables that were added in the constructor with an for each loop
     */
    public static void updateAll() {
        for(int i = 0; i < updatables.size(); i++) {
            updatables.get(i).update();
        }
    }
}