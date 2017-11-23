package boebot.hardware;

import boebot.listeners.RemoteListener;
import boebot.Updatable;
import boebot.Command;
import TI.*;

/**
 * Write a description of class Remote here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Remote implements Updatable
{
    private int pin;
    private RemoteListener listener;
    private Timer remoteTimer = new Timer(10);
    
    /**
     * Constructor for objects of class Remote
     */
    public Remote(int pin, RemoteListener listener)
    {
        this.pin = pin;
        this.listener = listener;
        remoteTimer.mark();
    }
    
    public void update() {
        if(remoteTimer.timeout()) {
            int pulseLen = BoeBot.pulseIn(4, false, 6000);
            if(pulseLen > 2000) {
                int lenghts[] = new int[12];

                for(int i = 0; i < 12; i++)
                    lenghts[i] = BoeBot.pulseIn(4, false, 20000);

                this.listener.onCommandUpdate(lenghts);
            }
        }
    }
}