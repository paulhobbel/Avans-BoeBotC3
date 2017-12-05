package boebot.hardware;
import TI.*;

/**
 * A hardware class to interact with the Ultrasone module
 * 
 * Let the Ultrasone module constantly determine what the distance is to the clossest object in front of the BoeBot.
 * This class runs in it's own thread so it won't slow the other processes down.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class Ultrasone implements ListenerRunnable<Ultrasone.UltrasoneEvent>
{
    private int pinTrigger;
    private int pinEcho;
    private UltrasoneEvent listener;

    /**
     * Ultrasone Constructor
     *
     * The constructor gives the object the pin number where to send pulse to.
     * The constructor gives the object the pin of the echo so the method run can calculate the time since it was send out.
     * The constructor listner gives it the listener who will update the distance to the object in front of the bot.
     * 
     * @param pinTrigger A parameter
     * @param pinEcho A parameter
     */
    public Ultrasone(int pinTrigger, int pinEcho) {
        this.pinTrigger = pinTrigger;
        this.pinEcho = pinEcho;
    }
    
    public void setListener(UltrasoneEvent listener) {
        this.listener = listener;
    }
    
    /**
     * This method is called by the thread running this Runnable.
     * 
     * This method makes sure that the ultrasound sensor sends a tone out and waits until it returns. 
     * With the length the tone is gone we can calculate the distance to the object infront of the BoeBot.
     */
    public void run() {
        while(true) {
            BoeBot.digitalWrite(this.pinTrigger, true);
            BoeBot.wait(1);
            BoeBot.digitalWrite(this.pinTrigger, false);
            
            int pulse = BoeBot.pulseIn(this.pinEcho, false, 10000);
            if(pulse >= 0) {
                int distance = (10000 - pulse) / 57;
                
                if(!this.listener.equals(null)) {
                    this.listener.onDistance(distance);
                }
            }
            
            BoeBot.wait(50);
        }
    }
    
    
    /**
     * Interface UltrasoneListener
     *
     * Sends the distance to the updater so you can check if the BoeBot is too close to an object.
     */
    public interface UltrasoneEvent {
        /**
         * 
         * @param distance The current distance
         */
        public void onDistance(int distance);
    }
}
