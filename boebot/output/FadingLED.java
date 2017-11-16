package boebot.output;

import TI.*;
import boebot.Updatable;

/**
 * Write a description of class FadingLED here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FadingLED implements Updatable
{   
    private PWM led;
    private int i = 255;
    private Timer timer;
    private boolean round = false;
    
    public FadingLED(int ledPin, int speed) {
        this.timer = new Timer(speed);
        this.led = new PWM(ledPin, this.i);
        this.led.start();
    }
    
    public void update() {
        if(this.timer.timeout()) {
            if(this.round) {
                this.i++;
            } else {
                this.i--;
            }
            
            if(this.i < 0) {
                this.round = true;
                this.i = 0;
            }
            
            if(this.i > 255) {
                this.round = false;
                this.i = 255;
            }
            
            this.led.update(this.i);
        }
    }
}
