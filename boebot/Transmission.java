package boebot;

import TI.*;
import boebot.hardware.Engine;

/**
 * Communication with the motors for the other classes.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 18-11-2017
 */
public class Transmission extends Updatable
{
    private final double DEGREETIME = 5.7; //time it take for the BoeBot to turn 1 degree at 1600 speed
    private Engine engineR = new Engine(15, false);
    private Engine engineL = new Engine(14, true);
    /**
     * Makes the servo's go straight forwards.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void forwards(Speed speed) {
        this.speed(speed.getSpeed(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move backwards.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void backwards(Speed speed) {
        this.speed(-speed.getSpeed(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's brake from the current speed to zero.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void brake(Speed speed) {
        this.emergencyBrake(speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move to the right with a given speed.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void right(Speed speed) {
        this.turnSpeed(speed.getSpeed(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move to the left with a given speed.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void left(Speed speed) {
        this.turnSpeed(-speed.getSpeed(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move to the right with a given speed and also makes a curve.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void curveRightForwards(Speed speed) {
        this.curve(speed.getSpeedSlow(), speed.getSpeedFast(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move to the left with a given speed and also makes a curve.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void curveLeftForwards(Speed speed) {
        this.curve(speed.getSpeedFast(), speed.getSpeedSlow(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move to the right with a given speed and also makes a curve.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void curveRightBackwards(Speed speed) {
        this.curve(-speed.getSpeedSlow(), -speed.getSpeedFast(), speed.getAcceleration());
    }
    
    /**
     * Makes the servo's move to the left with a given speed and also makes a curve.
     * 
     * @see #Speed for more info.
     * @param speed The speed constant.
     */
    public void curveLeftBackwards(Speed speed) {
        this.curve(-speed.getSpeedFast(), -speed.getSpeedSlow(), speed.getAcceleration());
    }
    
    /**
     * Updates the right and left engines.
     */
    public void update() {
        this.engineR.update();
        this.engineL.update();
    }
    
    /**
     * Drive to the given speed in the given amount of time.
     * 
     * @param speed The speed between -100 and 100 (0 is standing still).
     * @param time Time given by the user. In this amount of time the servo's must reach the target speed.
     */
    private void speed(int speed, int time)
    {
        engineR.setSpeed(speed, time);
        engineL.setSpeed(speed, time);
    }
    
    /**
     * BROKEN!!!
     * Immediately turn at the desired speed around the center point of the axis at speed 50 for a chosen amount of degrees 
     * @param   degrees the amount to turn in degrees (positive is clockwise and negative is counter clockwise)
     */
    private void turnDegrees(int degrees)
    {
        if(degrees >= 0)
            turnSpeed(50, 50);
        else if(degrees < 0)
            turnSpeed(-50, 50);
        BoeBot.wait((int)Math.abs(degrees * DEGREETIME));
        //emergencyBrake();
    }

    /**
     * Turn to the desired speed around the center point of the axis in a given amount of time.
     * 
     * @param speed The speed between -100 (counter clockwise) and 100 (clockwise). 0 is standing still.
     * @param time Time given by the user. In this amount of time the servo's must reach the target speed. 
     */
    private void turnSpeed(int speed, int time)
    {
        engineR.setSpeed(speed, time);
        engineL.setSpeed(-speed, time);
    }
    
    /**
     * Make a curve with a given speed in a given amount of time.
     * 
     * @param speedL Set the speed of the left servo.
     * @param speedR Set the speed of the right servo.
     * @param time Time given by the user. In this amount of time the servo's must reach the target speed.
     */
    private void curve(int speedL, int speedR, int time)
    {
        engineL.setSpeed(speedL, time);
        engineR.setSpeed(speedR, time);
    }

    /**
     * Stops the engines in a given amount of time.
     * 
     * @param time Time given by the user. In this amount of time the servo's must reach the target speed.
     */
    private void emergencyBrake(int time)
    {
        engineR.setSpeed(0, time);
        engineL.setSpeed(0, time);
    }

    /**
     * Class to store speed constants.
     */
    public enum Speed
    {
        FAST(100, 500),
        MEDIUM(50, 250),
        SLOW(25, 100),
        
        TIGHT_CURVE(5, 75, 200),
        NORMAL_CURVE(15, 75, 200),
        LOOSE_CURVE(30, 75, 200);
        
        private int speed;
        private int speedSlow;
        private int speedFast;
        private int acceleration;
        
        /**
         * The given speed by a user which should be reached with a constant acceleration.
         *
         * @param speed The speed between -100 (counter clockwise) and 100 (clockwise). 0 is standing still.
         * @param acceleration Acceleration given by the user, which should be constant untill the right speed is reached.
         */
        Speed(int speed, int acceleration) {
            this.speed = speed;
            this.acceleration = acceleration;
        }
        
        /**
         * The given speed by a user which should be reached with a constant acceleration.
         *
         * @param speedSlow Either the left or right wheel moves slow, depending on the method call.
         * @param speedFast Either the left or right wheel moves fast, depending on the method call.
         * @param acceleration Acceleration given by the user, which should be constant untill the right speed is reached.
         */
        Speed(int speedSlow, int speedFast, int acceleration) {
            this.speedSlow = speedSlow;
            this.speedFast = speedFast;
            this.acceleration = acceleration;
        }
        
        /**
         * Get the current speed from the servo's.
         */
        public int getSpeed() {
            return this.speed;
        }
        
        /**
         * Get the current slow speed from the servo's.
         */
        public int getSpeedSlow() {
            return this.speedSlow;
        }
        
        /**
         * Get the current fast speed from the servo's.
         */
        public int getSpeedFast() {
            return this.speedFast;
        }
        
        /**
         * Get the current acceleration from the servo's.
         */
        public int getAcceleration() {
            return this.acceleration;
        }
    }
}
