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
    
    /**
     * Constructor for objects of class Remote
     */
    public Remote(int pin, RemoteListener listener)
    {
        this.pin = pin;
        this.listener = listener;
    }
    
    public void update() {
        
    }
    
    
    public void main(String[] args) {
        System.out.println("Listening...");
        while(true) {
            int pulseLen = BoeBot.pulseIn(4, false, 6000);
            if(pulseLen > 2000) {
                int lenghts[] = new int[12];

                for(int i = 0; i < 12; i++)
                    lenghts[i] = BoeBot.pulseIn(4, false, 20000);

                //handleCommand(convert(lenghts));
                this.listener.onCommandUpdate(lenghts);
            }

            BoeBot.wait(10);
        }
    }

    public static Command convert(int lengths[]) {
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
}
