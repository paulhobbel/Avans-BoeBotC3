package boebot;

import TI.*;
import java.util.ArrayList;

import boebot.hardware.ListenerRunnable;
import boebot.hardware.Remote;
import boebot.hardware.Remote.RemoteEvent;
import boebot.hardware.Ultrasone;
import boebot.hardware.Ultrasone.UltrasoneEvent;
import boebot.hardware.Bluetooth;

/**
 * Class Robot.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class Robot {
    private ArrayList<Thread> threads = new ArrayList<>();
    
    private StateContext context;
    
    private ListenerRunnable<RemoteEvent> remote;
    private ListenerRunnable<UltrasoneEvent> ultrasone;
    
    public Robot() {
        this.context = new StateContext(this, new IdleState());
        
        this.remote = new Remote(Constants.REMOTE_PIN);
        this.ultrasone = new Ultrasone(Constants.ULTRASONE_TRIGGER_PIN, Constants.ULTRASONE_ECHO_PIN);
        new Bluetooth();
        
        this.threads.add(new Thread(this.ultrasone));
        this.threads.add(new Thread(this.remote));
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
    
    public ListenerRunnable<RemoteEvent> getRemote() {
        return this.remote;
    }
    
    public ListenerRunnable<UltrasoneEvent> getUltrasone() {
        return this.ultrasone;
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
