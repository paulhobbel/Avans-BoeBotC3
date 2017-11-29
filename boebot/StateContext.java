package boebot;

import java.util.ArrayList;
import boebot.hardware.Remote.RemoteListener;
import boebot.hardware.Ultrasone.UltrasoneListener;
import boebot.output.LED;
import boebot.output.LED.Color;

/**
 * Write a description of class StateContext here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    public void goBack() {
        this.currentState = this.lastState;
        this.currentState.init();
        System.out.println("Went back to lastState: " + this.currentState);
    }
    
    public void onCommandUpdate(Command command) {
        this.command = command;
    }
    
    public void onDistanceUpdate(int distance) {
        this.distance = distance;
    }
    
    public void setColor(Color color) {
        this.led1.turnOn(color);
        this.led2.turnOn(color);
    }
}
