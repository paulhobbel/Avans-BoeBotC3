package boebot.output;

import TI.*;
import boebot.Updatable;

/**
 * Write a description of class Speaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Speaker implements Runnable {
    private int pin;
    private int speed;
    private float freq;
    
    public Speaker(int pin, int speed, float freq) {
        this.pin = pin;
        this.speed = speed;
        this.freq = freq;
    }
    
    public void run() {
        BoeBot.freqOut(this.pin, this.freq, this.speed * 1000);
    }
}
