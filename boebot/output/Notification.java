package boebot.output;

import TI.*;
import boebot.Updatable;

/**
 * Write a description of class Notification here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Notification implements Updatable
{
    private Thread speaker;
    private Timer timer;
    
    public Notification() {
        this.speaker = new Thread(new Speaker(9, 1000, 2000));
        this.timer = new Timer(2000);
    }
    
    public void update() {
        if(!this.speaker.isAlive() && this.timer.timeout()) {
            this.speaker = new Thread(new Speaker(9, 1000, 2000));
            this.speaker.start();
        }
    }
}
