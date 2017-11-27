package boebot.hardware;

import boebot.Updatable;
import boebot.Command;
import TI.*;

/**
 * Write a description of class Remote here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RemoteOld implements Updatable
{
    private int pin;
    private RemoteListener listener;
    private Timer remoteTimer = new Timer(10);

    /**
     * Constructor for objects of class Remote
     */
    public RemoteOld(int pin, RemoteListener listener)
    {
        this.pin = pin;
        this.listener = listener;
        remoteTimer.mark();
    }

    public void update() {
        if(remoteTimer.timeout()) {
            int pulseLen = BoeBot.pulseIn(this.pin, false, 6000);
            if(pulseLen > 2000) {
                int lengths[] = new int[12];

                for(int i = 0; i < 12; i++)
                    lengths[i] = BoeBot.pulseIn(this.pin, false, 20000);
                this.listener.onCommandUpdate(this.convertLengths(lengths));
            }
        }
    }

    private Command convertLengths(int lengths[]) {
        int code = 0;
        int id = 0;

        for(int i = 0; i < 12; i++) {
            if(lengths[i] > 900) {
                if(i < 7) { // Button Code
                    code |= (1<<i);
                } else {     // ID
                    id |= (1<<i-7);
                }
            }
        }
        return Command.forCodeAndID(code, id);
    }

    public interface RemoteListener
    {
        public void onCommandUpdate(Command command);
    }
}