package boebot;

import java.util.ArrayList;
import java.awt.Color;

import boebot.ProtocolHelper.ProtocolRouteListener;

import boebot.output.Notification;

import boebot.hardware.Remote.RemoteEvent;
import boebot.hardware.Ultrasone.UltrasoneEvent;
import boebot.hardware.Bluetooth.BluetoothListener;
import boebot.hardware.LED;

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
    
    private Notification notification;
    private ProtocolHelper protocolHelper;
    
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
        
        this.notification = new Notification();
        this.protocolHelper = new ProtocolHelper(this.notification);
        
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
    
    // public void setBluetoothListener(BluetoothListener listener) {
        // this.robot.getBluetooth().setListener(listener);
    // }
    
    public void setProtocolRouteListener(ProtocolRouteListener listener) {
        this.protocolHelper.setRouteListener(listener);
    }
    
    public void setState(State newState) {
        // TODO: Maybe check in the stateHistory if we still have this state.
        
        this.setRemoteListener(null);
        this.setProtocolRouteListener(null);
        this.setUltrasoneListener(null);
        
        this.notification.getSoundContext().removeAllTones();
        
        newState.init(this);
        this.stateHistory.add(0, newState);
        
        this.protocolHelper.sendLog("DEBUG", "Switched to state: " + newState + ", there are " + (this.stateHistory.size()-1) + " previous states.");
    }
    
    public Notification getNotification() {
        return this.notification;
    }
    
    public ProtocolHelper getProtocolHelper() {
        return this.protocolHelper;
    }
    
    public void update() {
        // The first item in the state history is the most recent one
        // thats why we take index 0 since that is the first item in the list
        this.stateHistory.get(0).update(this);
    }
    
    /**
     * Let the BoeBot go back to the previous state it was in.
     */
    public void goBack() {        
        // Remove the currentState
        this.stateHistory.remove(0);
        
        this.setRemoteListener(null);
        this.setProtocolRouteListener(null);
        this.setUltrasoneListener(null);
        
        // The previous state is now at index 0
        State previousState = this.stateHistory.get(0);
        previousState.init(this);
        
        this.protocolHelper.sendLog("DEBUG", "Went back to previousState: " + previousState + ", there are " + (this.stateHistory.size()-1) + " previous states.");
    }
    
    /**
     * Gives a led the color you insert in this method.
     * 
     * @todo Move this to Notification
     * @param color A parameter
     */
    public void setColor(Color color) {
        this.led1.turnOn(color, 0.1f);
        this.led2.turnOn(color, 0.1f);
    }
    
    public void setColor(Color color, float brightness) {
        this.led1.turnOn(color, brightness);
        this.led2.turnOn(color, brightness);
    }
}
