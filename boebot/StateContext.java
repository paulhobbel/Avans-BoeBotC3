package boebot;

import java.util.ArrayList;
import boebot.hardware.Remote.RemoteListener;
import boebot.hardware.Ultrasone.UltrasoneListener;
import boebot.output.LED;
import boebot.output.LED.Color;

/**
 * Write a description of class StateContext here.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 05-12-2017 (Version 1.0)
 */
public class StateContext extends Updatable implements RemoteListener, UltrasoneListener
{
    private State currentState;
    private State lastState;
    private Robot robot;
    
    private Transmission transmission;
    private LED led1;
    private LED led2;
    
    private int distance;
    private Command command;
    
    public StateContext(Robot robot) {
        this.robot = robot;
        this.transmission = new Transmission();
        this.led1 = new LED(0);
        this.led2 = new LED(2);
    }
    
    public Transmission getTransmission() {
        return this.transmission;
    }
    
    public Command getCommand() {
       return this.command; 
    }
    
    public boolean hasCollision() {
        return this.distance != -1 && this.distance < 15;
    }
    
    public void setState(State newState) {
        this.lastState = this.currentState;
        this.currentState = newState;
        this.currentState.init();
        System.out.println("Switched to state: " + newState);
    }
    
    public void update() {
        this.currentState.update(this);
    }
    
    /**
     * Method goBack
     *
     * Let the BoeBot go back to the previous state it was in.
     */
    public void goBack() {
        this.currentState = this.lastState;
        this.currentState.init();
        System.out.println("Went back to lastState: " + this.currentState);
    }
    
    public void onCommandUpdate(Command command) {
        this.command = command;
    }
    
    /**
     * Method onDistanceUpdate
     * 
     * Updates the distance from the BoeBot to the wall.
     *
     * @param distance A parameter
     */
    public void onDistanceUpdate(int distance) {
        this.distance = distance;
    }
    
    /**
     * Method setColor
     *
     * Gives a led the color you insert in this method.
     *
     * @param color A parameter
     */
    public void setColor(Color color) {
        this.led1.turnOn(color);
        this.led2.turnOn(color);
    }
}
