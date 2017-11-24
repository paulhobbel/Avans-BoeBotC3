package boebot.hardware;

import TI.*;

/**
 * A hardware class to interact with the servo motors. Only to be used by the class Transmission.
 *
 * @author Daan van Kempen C3
 * @version 18-11-2017
 */
public class Engine
{
    private Servo servo;
    private boolean reverse;

    public Engine(int pin, boolean reverse)
    {
        this.servo = new Servo(pin);
        this.reverse = reverse;
    }

    /**
     * Immediately rotate the servo at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public void speed(int speed)
    {
        if(speed >= -100 && speed <= 100)
            if(!reverse)    
                servo.update(1500 + speed * 2);
            else
                servo.update(1500 - speed * 2);
        else
            throw new Error("Parameter out of bounds");
    }

    /**
     * Returns the current speed of the servo motor.
     * @return  the current speed between -100 and 100 (0 is standing still)
     */
    public int getSpeed()
    {
        if(!reverse)
        return (servo.getPulseWidth() - 1500) / 2;
        else
        return (servo.getPulseWidth() - 1500) / -2;
    }

    /**
     * Immediately stops the engine
     */
    public void emergencyBrake()
    {
        servo.update(1500);
    }
}