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
public class Engine extends Updatable
{
    private Servo servo;
    private boolean reverse;

    private int amountOfCycles;
    private double speedPerCycle;
    private int currentCycles;
    private int beginSpeed;

    /**
     * Creates a new engine.
     * 
     * @param pin Enter the pin number to which the servo is connected.
     * @param reverse Specify wheter the servo needs to be reversed. 
     */
    public Engine(int pin, boolean reverse)
    {
        super(true); // Tells the Updatable not to automatically update!
        
        this.servo = new Servo(pin);
        this.reverse = reverse;
    }

    /**
     * This method updates the engine.
     * 
     * In here the speed will be increased with the said speedPerCycle.
     * This will continue until the currentCycles reached the amountOfCycles limit.
     */
    public void update() {
        if(this.currentCycles < this.amountOfCycles) {
            double speed = this.beginSpeed + this.currentCycles * this.speedPerCycle;

            this.setSpeed((int)Math.round(speed));

            this.currentCycles++;
        }
    }

    /**
     * Sets the servo to the targeted speed in the amount of time given by the user.
     * 
     * @param target Targeted speed which the servo's must reach.
     * @param time Time given by the user. In this amount of time the servo's must reach the target speed.
     */
    public void setSpeed(int target, int time)
    {
        if(!(target >= -100 && target <= 100 && time > 0))
            throw new Error("Parameter out of bounds");

        this.beginSpeed = this.getSpeed();

        double difference = target - this.beginSpeed;
        
        this.speedPerCycle = (difference / time);
        this.amountOfCycles = time + 1;
        this.currentCycles = 1;
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