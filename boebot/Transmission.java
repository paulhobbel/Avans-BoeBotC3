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

    public void forwards(Speed speed) {
        this.speed(speed.getSpeed(), speed.getAcceleration());
    }
    
    public void backwards(Speed speed) {
        this.speed(-speed.getSpeed(), speed.getAcceleration());
    }
    
    public void brake(Speed speed) {
        this.emergencyBrake(speed.getAcceleration());
    }
    
    public void right(Speed speed) {
        this.turnSpeed(speed.getSpeed(), speed.getAcceleration());
    }
    
    public void left(Speed speed) {
        this.turnSpeed(-speed.getSpeed(), speed.getAcceleration());
    }
    
    public void curveRightForwards(Speed speed) {
        this.curve(speed.getSpeedFast(), speed.getSpeedSlow(), speed.getAcceleration());
    }
    
    public void curveLeftForwards(Speed speed) {
        this.curve(speed.getSpeedSlow(), speed.getSpeedFast(), speed.getAcceleration());
    }
    
    public void curveRightBackwards(Speed speed) {
        this.curve(-speed.getSpeedFast(), -speed.getSpeedSlow(), speed.getAcceleration());
    }
    
    public void curveLeftBackwards(Speed speed) {
        this.curve(-speed.getSpeedSlow(), -speed.getSpeedFast(), speed.getAcceleration());
    }
    
    public void update() {
        this.engineR.update();
        this.engineL.update();
    }
    
    /**
     * Immediately drive at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
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
     * Immediately turn to the desired speed around the center point of the axis
     * @param   speed   the speed between -100 (counter clockwise) and 100 (clockwise). 0 is standing still
     */
    private void turnSpeed(int speed, int time)
    {
        engineR.setSpeed(speed, time);
        engineL.setSpeed(-speed, time);
    }
    
    private void curve(int speedL, int speedR, int time)
    {
        engineL.setSpeed(speedL, time);
        engineR.setSpeed(speedR, time);
    }

    /**
     * Immediately stops the 2 engines
     */
    private void emergencyBrake(int time)
    {
        engineR.setSpeed(0, time);
        engineL.setSpeed(0, time);
    }

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
        
        Speed(int speed, int acceleration) {
            this.speed = speed;
            this.acceleration = acceleration;
        }
        
        Speed(int speedSlow, int speedFast, int acceleration) {
            this.speedSlow = speedSlow;
            this.speedFast = speedFast;
            this.acceleration = acceleration;
        }
        
        public int getSpeed() {
            return this.speed;
        }
        
        public int getSpeedSlow() {
            return this.speedSlow;
        }
        
        public int getSpeedFast() {
            return this.speedFast;
        }
        
        public int getAcceleration() {
            return this.acceleration;
        }
    }
}
