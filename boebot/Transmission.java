package boebot;

import TI.*;
import boebot.hardware.Engine;

/**
 * Communication with the motors for the other classes.
 *
 * @author Daan van Kempen C3
 * @version 18-11-2017
 */
public class Transmission
{
    private static final double DEGREETIME = 5.7; //time it take for the BoeBot to turn 1 degree at 1600 speed

    /**
     * Immediately drive at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public static void speed(int speed)
    {
        Engine.speed(speed);
    }
    
    /**
     * Immediately drive at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public static void curve(int speedL, int speedR)
    {
        Engine.speedR(speedR);
        Engine.speedL(speedL);
    }

    /**
     * Slowly accelerate the motors to the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     * @param   milli   the amount of milliseconds in which the acceleration takes place. 
     */
    public static void goToSpeed(int speed, int milli)
    {
        int currentSpeed = (Engine.getSpeedR() + Engine.getSpeedR()) / 2;
        int target = speed;
        int difference = target - currentSpeed;
        int updateSpeed = 10;
        int counter = 0;
        int amountOfCycles = (int)Math.ceil(milli / updateSpeed);
        double speedPerCycle = (difference+0.0) / (amountOfCycles+0.0);

        if(difference != 0)
        {
            while(true)
            {
                counter++;
                Engine.speed((int)(currentSpeed + (counter * speedPerCycle)));
                //System.out.println(Engine.getSpeedR());
                if(counter >= amountOfCycles)
                    break;
                BoeBot.wait(updateSpeed);
            }
        }
    }

    /**
     * Immediately turn at the desired speed around the center point of the axis at speed 50 for a chosen amount of degrees 
     * @param   degrees the amount to turn in degrees (positive is clockwise and negative is counter clockwise)
     */
    public static void turn(int degrees)
    {
        if(degrees >= 0)
            Engine.turn(50);
        else if(degrees < 0)
            Engine.turn(-50);
        BoeBot.wait((int)Math.abs(degrees * DEGREETIME));
        Engine.emergencyBrake();
    }

    /**
     * Immediately turn at the desired speed around the center point of the axis at a chosen speed
     * @param   speed   the speed between -100 and 100 (0 is standing still) (positive is clockwise and negative is counter clockwise)
     */
    public static void turnSpeed(int speed)
    {
        Engine.turn(speed);
    }

    /**
     * Immediately stops the 2 engines
     */
    public static void emergencyBrake()
    {
        Engine.emergencyBrake();
    }
}
