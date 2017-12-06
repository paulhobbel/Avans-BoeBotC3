package boebot.hardware;

import TI.*;
import boebot.Updatable;

/**
 * A hardware class to interact with the servo motors. Only to be used by the class Transmission.
 *
 * @author Daan van Kempen
 * @author Paul Hobbel
 * @version 05-12-2017
 */
public class Engine
{
    private Servo servo;
    private boolean reverse;

    /**
     * Creates a new engine.
     * 
     * @param pin Enter the pin number to which the servo is connected.
     * @param reverse Specify wheter the servo needs to be reversed. 
     */
    public Engine(int pin, boolean reverse)
    {   
        this.servo = new Servo(pin);
        this.reverse = reverse;
    }

    /**
     * Instantly sets a speed that must be constantly driven.
     * <p>
     * NOTE: This function is for internal usage only,
     * consider using {@link #setSpeed(int target, int time)} instead!
     * 
     * @param speed Set a speed between -100 and 100 (0 is standing still).
     */
    public void setSpeed(int speed)
    {
        if(!(speed >= -100 && speed <= 100))
            throw new Error("Parameter out of bounds, please use a speed between -100 and 100");
        
        if(!this.reverse)
            this.servo.update(1500 + speed * 2);
        else
            this.servo.update(1500 - speed * 2);
    }

    /**
     * Returns the current speed of the servo motor.
     * 
     * @return the current speed between -100 and 100 (0 is standing still)
     */
    public int getSpeed()
    {
        if(!reverse)
            return (servo.getPulseWidth() - 1500) / 2;
        else
            return (servo.getPulseWidth() - 1500) / -2;
    }
}