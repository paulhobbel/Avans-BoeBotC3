package boebot;
import TI.*;

/**
 * A hardware class to interact with the servo motors. Only to be used by the class Transmission.
 *
 * @author Daan van Kempen C3
 * @version 18-11-2017
 */
public class Engine
{
    private static Servo servoL = new Servo(14);
    private static Servo servoR = new Servo(15);

    /**
     * Immediately drive at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public static void speed(int speed)
    {
        speedR(speed);
        speedL(speed);
    }

    /**
     * Immediately rotate the right motor at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public static void speedR(int speed)
    {
        if(speed >= -100 && speed <= 100)
            servoR.update(1500 + speed * 2);
        else
            System.out.println("Parameter out of bounds");
    }

    /**
     * Immediately rotate the left motor at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public static void speedL(int speed)
    {
        if(speed >= -100 && speed <= 100)
            servoL.update(1500 - speed * 2);
        else
            System.out.println("Parameter out of bounds");
    }

    /**
     * Immediately turn to the desired speed around the center point of the axis
     * @param   speed   the speed between -100 (counter clockwise) and 100 (clockwise). 0 is standing still
     */
    public static void turn(int speed)
    {
        if(speed >= -100 && speed <= 100) {
            servoR.update(1500 + speed * 2);
            servoL.update(1500 + speed * 2);
        }
        else
            System.out.println("Parameter out of bounds");
    }

    /**
     * Returns the current speed of the right servo motor.
     * @return  the current speed between -100 and 100 (0 is standing still)
     */
    public static int getSpeedR()
    {
        return (servoR.getPulseWidth() - 1500) / 2;
    }

    /**
     * Returns the current speed of the left servo motor.
     * @return  the current speed between -100 and 100 (0 is standing still)
     */
    public static int getSpeedL()
    {
        return (servoL.getPulseWidth() - 1500) / -2;
    }

    /**
     * Immediately stops the 2 engines
     */
    public static void emergencyBrake()
    {
        servoR.update(1500);
        servoL.update(1500);
    }
}