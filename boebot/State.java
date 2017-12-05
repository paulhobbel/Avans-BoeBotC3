package boebot;

/**
 * Abstract class State - write a description of the class here
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public abstract class State
{
    public void init(StateContext context) { }
    
    public abstract void update(StateContext context);
}
