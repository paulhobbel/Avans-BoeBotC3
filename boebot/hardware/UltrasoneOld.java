package boebot.hardware;
import TI.*;
import boebot.Updatable;
/**
 * Write a description of class Ultrasone here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UltrasoneOld implements Updatable
{
    private int pinTrigger;
    private int pinEcho;
    private Timer timer;
    private UltrasoneListener listener;
    private boolean triggered;

    public UltrasoneOld(int pinTrigger, int pinEcho, UltrasoneListener listener) {
        this.timer = new Timer(50);
        this.timer.mark();

        this.pinTrigger = pinTrigger;
        this.pinEcho = pinEcho;
        this.listener = listener;

        this.triggered = false;
    }

    public void update(){
        if(this.triggered) {
            BoeBot.digitalWrite(this.pinTrigger, false);
            this.triggered = false;
            int pulse = BoeBot.pulseIn(this.pinEcho, false, 10000);
            if(pulse >= 0){
                int distance = (10000 - pulse) / 57;
                this.listener.onDistanceUpdate(distance);
            } else {
                //this.listener.onDistanceUpdate(-1);
            }
        } else {
            if(this.timer.timeout()) {
                BoeBot.digitalWrite(this.pinTrigger, true);
                this.triggered = true;
            }
        }
    }

    public interface UltrasoneListener {
        public void onDistanceUpdate(int distance);
    }
}