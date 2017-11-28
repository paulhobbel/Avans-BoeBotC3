package boebot;

/**
 * Abstract class State - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
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
