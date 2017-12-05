package boebot;

import java.util.ArrayList;
import java.awt.Color;
 
import boebot.hardware.Remote.RemoteEvent;
import boebot.hardware.Ultrasone.UltrasoneEvent;
import boebot.output.LED;

/**
 * Write a description of class StateContext here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class StateContext extends Updatable
{
    private State currentState;
    private State lastState;
    
    private Robot robot;
    
    /**
     * An array of all past states, were the current state will be on index 0
     * 
     * Everytime goBack() is called the state at index 0 will be removed
     * this way you can go all the way back to your first state if needed
     * 
     * @experimental
     */
    public ArrayList<State> stateHistory = new ArrayList<>();
    
    private LED led1 = new LED(0);
    private LED led2 = new LED(2);
    
    public StateContext(Robot robot, State initialState) {
        this.robot = robot;
        //this.currentState = initialState;
        
        initialState.init(this);
        this.stateHistory.add(0, initialState);
    }
    
    /**
     * A bridge for states to update the current listener of Remote
     * <p>
     * Design Tip: Run this function in the onInit function of your state.
     * 
     * @param listener The listener to update to
     */
    public void setRemoteListener(RemoteEvent listener) {
        this.robot.getRemote().setListener(listener);
    }
    
    /**
     * A bridge for states to update the current listener of Ultrasone
     * <p>
     * Design Tip: Run this function in the onInit function of your state.
     * 
     * @param listener The listener to update to
     */
    public void setUltrasoneListener(UltrasoneEvent listener) {
        this.robot.getUltrasone().setListener(listener);
    }
    
    public void setState(State newState) {
        // this.lastState = this.currentState;
        // this.currentState = newState;
        // this.currentState.init(this);
        
        // TODO: Maybe check in the stateHistory if we still have this state.
        
        newState.init(this);
        this.stateHistory.add(0, newState);
        
        System.out.println("Switched to state: " + newState + ", there are " + (this.stateHistory.size()-1) + " previous states.");
    }
    
    public void update() {
        this.currentState.update(this);
        
        // The first item in the state history is the most recent one
        // thats why we take index 0 since that is the first item in the list
        this.stateHistory.get(0).update(this);
    }
    
    /**
     * Let the BoeBot go back to the previous state it was in.
     */
    public void goBack() {
        // this.currentState = this.lastState;
        // this.currentState.init(this);
        
        // Remove the currentState
        this.stateHistory.remove(0);
        
        // The previous state is now at index 0
        State previousState = this.stateHistory.get(0);
        previousState.init(this);
        
        System.out.println("Went back to previousState: " + previousState + ", there are " + (this.stateHistory.size()-1) + " previous states.");
    }
    
    /**
     * Gives a led the color you insert in this method.
     * 
     * @todo Move this to Notification
     * @param color A parameter
     */
    public void setColor(Color color) {
        this.led1.turnOn(color);
        this.led2.turnOn(color);
    }
    
    public void setColor(Color color, int alpha) {
        this.led1.turnOn(color, alpha);
        this.led2.turnOn(color, alpha);
    }
}
