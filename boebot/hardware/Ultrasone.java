package boebot.hardware;

import TI.*;

/**
 * Write a description of class UltrasoneThread here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ultrasone implements Runnable
{
    private int pinTrigger;
    private int pinEcho;
    private UltrasoneListener listener;

    public Ultrasone(int pinTrigger, int pinEcho, UltrasoneListener listener) {
        this.pinTrigger = pinTrigger;
        this.pinEcho = pinEcho;
        this.listener = listener;
    }
    
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
    
    public interface UltrasoneListener {
        public void onDistanceUpdate(int distance);
    }
}
