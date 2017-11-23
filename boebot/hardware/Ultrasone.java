package boebot.hardware;
import TI.*;
import boebot.Updatable;
/**
 * Write a description of class Ultrasone here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ultrasone implements Updatable
{
    private int pinTrigger;
    private int pinEcho;
    private Timer ultrasoneTimer = new Timer(50);
    private Timer ultrasonePulseTimer = new Timer(1);
    private int distance;
    private int pulse;
    private boolean test;

    public Ultrasone(){
        ultrasoneTimer.mark();
        pinTrigger = 4;
        pinEcho = 5;
        distance = -1;
        test = false;
    }

    public void update(){
        if(ultrasoneTimer.timeout()){
            BoeBot.digitalWrite(pinTrigger, true);
            ultrasonePulseTimer.mark();
            test = true;
        }
        if( ultrasonePulseTimer.timeout() && test){   
            BoeBot.digitalWrite(pinTrigger, false);
            test = false;
            pulse = BoeBot.pulseIn(pinEcho, false, 10000);
            if(pulse > 0){
                distance = (10000 - pulse) / 57;
                System.out.println(distance);
            }

            

        }

    }

}