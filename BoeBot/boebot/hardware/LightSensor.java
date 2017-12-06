package boebot.hardware; 

import TI.*;

/**
 * Write a description of class LightSensor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LightSensor
{
    int pin;
    
    public LightSensor(int pin) {
        if(pin >= 0 && pin <=3) {
            this.pin = pin;
        } else {
            throw new Error("Pin out of bounds! Choose a value between 0 and 3!");
        }
    }
    
    public int getValue() {
        int value = 4095 - BoeBot.analogRead(this.pin);
        //System.out.println("Value: " + value);
        return value;
    }
}
