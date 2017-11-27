package boebot;

import java.util.ArrayList;
import TI.*;

import boebot.*;
import boebot.output.*;

import boebot.hardware.Remote;
import boebot.hardware.Remote.RemoteListener;
import boebot.hardware.Ultrasone;
import boebot.hardware.Ultrasone.UltrasoneListener;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Robot implements RemoteListener, UltrasoneListener {
    ArrayList<Updatable> updatables = new ArrayList<>();
    
    private Command currentCommand; 
    private int currentDistance;
    
    private Thread ultrasoneThread;
    private Thread remoteThread;
    
    public Robot() {
        //this.updatables.add(new FadingLED(1, 10));
        //this.updatables.add(new FlashingLED(6, 200));
        //this.updatables.add(new Notification());
        
        //this.updatables.add(new Ultrasone(0, 1, this));
        //this.updatables.add(new Remote(2, this));
        this.updatables.add(new StateContext(new IdleState(), this));
        
        this.ultrasoneThread = new Thread(new Ultrasone(0, 1, this));
        this.remoteThread = new Thread(new Remote(2, this));
    }
    
    public void loop() {
        this.ultrasoneThread.start();
        this.remoteThread.start();
        
        while(true) {
            for(Updatable updatable : this.updatables) {
                updatable.update();
            }
            BoeBot.wait(1);
        }
    }
    
    public Command getCurrentCommand() {
        Command tempCommand = this.currentCommand;
        this.currentCommand = Command.UNKNOWN;
        
        return tempCommand;
    }
    
    public int getCurrentDistance() {
        return this.currentDistance;
    }
    
    public void onCommandUpdate(Command command) {
        System.out.println(command);
        this.currentCommand = command;
    }
    
    public void onDistanceUpdate(int distance) {
        //System.out.println(distance);
        this.currentDistance = distance;
    }
    
    /**
     * The main loop of the BoeBot
     */
    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.loop();
    }
}
