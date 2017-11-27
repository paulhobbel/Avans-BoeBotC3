package boebot.output;

import TI.*;
import boebot.Updatable;

/**
 * Write a description of class FlashingLED here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FlashingLED extends Updatable
{
    private int ledPin;
    private Timer timer;
    private boolean state;
    
    public FlashingLED(int ledPin, int speed) {
        this.ledPin = ledPin;
        this.timer = new Timer(speed);
        this.timer.mark();
    }
    
    public void update() {
        if(this.timer.timeout()) {
            this.state = !this.state;
            BoeBot.digitalWrite(this.ledPin, this.state);
        }
    }
}
