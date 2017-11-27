package boebot;

import java.util.ArrayList;

/**
 * Write a description of interface Runnable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Updatable
{
    private static ArrayList<Updatable> updatables = new ArrayList();
    
    public Updatable() {
        updatables.add(this);
    }
    
    /**
     * The method that is run every ms.
     */
    public abstract void update();
    
    /**
     * Update all updatables
     */
    public static void updateAll() {
        for(Updatable updatable : updatables) {
            updatable.update();
        }
    }
}
