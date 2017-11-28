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
public class Robot {
    private ArrayList<Thread> threads = new ArrayList();
    
    private StateContext context;
    private Transmission transmission;
    
    public Robot() {
        this.context = new StateContext(this);
        this.context.setState(new IdleState(this.context));
        this.transmission = new Transmission();
        
        this.threads.add(new Thread(new Ultrasone(3, 2, this.context)));
        this.threads.add(new Thread(new Remote(0, this.context)));
    }
    
    public void loop() {
        for(Thread thread : this.threads) {
            thread.start();
        }
        
        while(true) {
            Updatable.updateAll();
            BoeBot.wait(1);
        }
    }
    
    public StateContext getContext() {
        return this.context;
    }
    
    public Transmission getTransmission() {
        return this.transmission;
    }
    
    /**
     * Callback of RemoteListener
     * 
     * This callback will be called by Remote,
     * once the remote command has changed.
     * 
     * @param command A command given by the remote.
     */
    @Deprecated
    public void onCommandUpdate(Command command) {
        //if(this.currentCommand != command)
        //    this.currentCommand = command;
    }
    
    /**
     * Callback of UltrasoneListener
     * 
     * This callback will be called by Ultrasone,
     * once the distance has changed.
     * 
     * @param distance Distance in centimeters.
     */
    @Deprecated
    public void onDistanceUpdate(int distance) {
        //this.currentDistance = distance;
    }
}
