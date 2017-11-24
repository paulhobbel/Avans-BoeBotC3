package boebot;

/**
 * Abstract class State - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class State
{   
    public void init() {
        // Not gonna do anything now :P
    }
    
    public abstract void update(StateContext context, Robot robot);
}
