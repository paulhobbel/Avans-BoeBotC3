package boebot.hardware;
import TI.*;

/**
 * Class Ultrasone
 * 
 * Let the Ultrasone constant determine what the distance is from the BoeBot to the clossest object in front of it.
 * This Class has it own thread where it in runs so it won't slow the other processes down.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 30-11-2017 (Version 1.0)
 */
public class Ultrasone implements Runnable
{
    private int pinTrigger;
    private int pinEcho;
    private UltrasoneListener listener;

    /**
     * Ultrasone Constructor
     *
     * The constructor gives the object the pin number where to send pulse to.
     * The constructor gives the object the pin of the echo so the method run can calculate the time since it was send out.
     * The constructor listner gives it the listener who will update the distance to the object in front of the bot.
     * 
     * @param pinTrigger A parameter
     * @param pinEcho A parameter
     * @param listener A parameter
     */
    public Ultrasone(int pinTrigger, int pinEcho, UltrasoneListener listener) {
        this.pinTrigger = pinTrigger;
        this.pinEcho = pinEcho;
        this.listener = listener;
    }
    
    /**
     * Method run
     *
     * While there's an object of Ultrasone this will run and makes sure the BoeBot will see any objects in front of them
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
                this.listener.onDistanceUpdate(distance);
            } else {
                this.listener.onDistanceUpdate(-1);
            }
            
            BoeBot.wait(50);
        }
    }
    
    
    
    /**
     * Interface UltrasoneListener
     *
     * Sends the distance to the updater so you can check if the BoeBot is too close to an object.
     */
    public interface UltrasoneListener {
        public void onDistanceUpdate(int distance);
    }
}
