package boebot;

/**
 * Abstract class State - write a description of the class here
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 05-12-2017 (Version 1.0)
 */
public abstract class State
{   
    protected StateContext context;
    
    public State(StateContext context) {
        this.context = context;
    }
    
    public void init() { }
    
    public abstract void update(StateContext context);
}
