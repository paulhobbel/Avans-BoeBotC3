package boebot;

import TI.*;
import boebot.hardware.Engine;

/**
 * Communication with the motors for the other classes.
 *
 * @author Daan van Kempen C3
 * @version 18-11-2017
 */
public class Transmission extends Updatable
{
    private final double DEGREETIME = 5.7; //time it take for the BoeBot to turn 1 degree at 1600 speed
    private Engine engineR = new Engine(15, false);
    private Engine engineL = new Engine(14, true);

    /**
     * Immediately drive at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public void speed(int speed)
    {
        engineR.setSpeed(speed, 1000);
        engineL.setSpeed(speed, 1000);
    }

    public void update() {
        this.engineR.update();
        this.engineL.update();
    }

    /**
     * Immediately drive at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public void curve(int speedL, int speedR)
    {
        this.engineR.setSpeed(speedR, 1000);
        this.engineL.setSpeed(speedL, 1000);
    }

    /**
     * Slowly accelerate the motors to the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     * @param   milli   the amount of milliseconds in which the acceleration takes place. 
     */
    public void goToSpeed(int speed, int milli)
    {
        int currentSpeed = (engineR.getSpeed() + engineL.getSpeed()) / 2;
        int target = speed;
        int difference = target - currentSpeed;
        int updateSpeed = 1;
        int counter = 0;
        int amountOfCycles = (int)Math.ceil(milli / updateSpeed);
        double speedPerCycle = (difference+0.0) / (amountOfCycles+0.0);

        if(difference != 0)
        {
            while(true)
            {
                counter++;
                speed((int)(currentSpeed + Math.round(counter * speedPerCycle)));
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
    public void turnDegrees(int degrees)
    {
        if(degrees >= 0)
            turnSpeed(50);
        else if(degrees < 0)
            turnSpeed(-50);
        BoeBot.wait((int)Math.abs(degrees * DEGREETIME));
        //emergencyBrake();
    }

    /**
     * Immediately turn to the desired speed around the center point of the axis
     * @param   speed   the speed between -100 (counter clockwise) and 100 (clockwise). 0 is standing still
     */
    public void turnSpeed(int speed)
    {
        engineR.setSpeed(speed, 100);
        engineL.setSpeed(-speed, 100);
    }

    /**
     * Immediately stops the 2 engines
     */
    public void emergencyBrake()
    {
        engineR.setSpeed(0, 100);
        engineL.setSpeed(0, 100);
    }
}
