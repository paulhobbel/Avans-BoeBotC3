package boebot.hardware;

import TI.*;
import boebot.Command;

/**
 * Write a description of class Remote here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017
 */
public class Remote implements ListenerRunnable<Remote.RemoteEvent>
{
    private int pin;
    private RemoteEvent listener;
    
    /**
     * Constructor for the Remote IR
     * 
     * @param pin The pin the IR is connected on
     */
    public Remote(int pin) {
        this.pin = pin;
    }
    
    public void setListener(RemoteEvent listener) {
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
                
                if(!this.listener.equals(null)) {
                    this.listener.onCommand(this.convertLengths(lengths));
                }
            }
            BoeBot.wait(10);
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

    public interface RemoteEvent
    {
        /**
         * Recieves a command from run after it has been split appart in converLengths
         * @param command A parameter
         */
        public void onCommand(Command command);
    }
}
