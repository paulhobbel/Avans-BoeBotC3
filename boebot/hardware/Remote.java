package boebot.hardware;

import TI.*;
import boebot.Command;

/**
 * Write a description of class Remote here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Remote implements Runnable
{
    private int pin;
    private RemoteListener listener;
    /**
     * Constructor for objects of class Remote
     * 
     */    
    public Remote(int pin, RemoteListener listener)
    {
        this.pin = pin;
        this.listener = listener;
    }
    
    /**
     * Method run
     * Gets activated when the OverrideTransmission is activated
     * Checks which buttons are pressed on the remote
     * Adds all the values sent by the remote to an array lengths
     * Pushes the array lengths trough the method converLengths and then sends it to the listener
     */
    public void run() {
        while(true) {
            int pulseLen = BoeBot.pulseIn(this.pin, false, 8000);
            if(pulseLen > 2000) {
                int lengths[] = new int[12];

                for(int i = 0; i < 12; i++)
                    lengths[i] = BoeBot.pulseIn(this.pin, false, 20000);
                this.listener.onCommandUpdate(this.convertLengths(lengths));
            }
            BoeBot.wait(100);
        }
    }
    
    /**
     * Method convertLengths
     * Recieves an array from the method run and splits the signal in two parts: code and id
     * id is the number unique to the used remode
     * code is the signal that countains the information about the command
     * @param lengths Array recieved from method run
     * @return The array split appart in code and id
     */
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
        /**
         * Method onCommandUpdate
         * Recieves a command from run after it has been split appart in converLengths
         * @param command A parameter
         */
        public void onCommandUpdate(Command command);
    }
}
