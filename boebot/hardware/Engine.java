package boebot.hardware;

import TI.*;
import boebot.Updatable;

/**
 * A hardware class to interact with the servo motors. Only to be used by the class Transmission.
 *
 * @author Daan van Kempen C3
 * @version 18-11-2017
 */
public class Engine extends Updatable
{
    private Servo servo;
    private boolean reverse;

    private int amountOfCycles;
    private double speedPerCycle;
    private int currentCycles;
    private int beginSpeed;

    private int seq;
    private int counter;
    private int targetSpeed = 0;

    public Engine(int pin, boolean reverse)
    {
        this.servo = new Servo(pin);
        this.reverse = reverse;
    }

    public void update() {
        if(this.currentCycles < this.amountOfCycles) {

            double speed = this.beginSpeed + this.currentCycles * this.speedPerCycle;

            //System.out.println(speed);
            setSpeedInstant((int)speed);

            //this.servo.update((int)speed);

            this.currentCycles++;
            //System.out.println("speed: " + speed + "; currentCycles: " + this.currentCycles);
        }
        else
        {
            this.beginSpeed = 0;
            this.amountOfCycles = 0;
            this.speedPerCycle = 0;
            this.currentCycles = 0;
        }

        // if(this.targetSpeed != this.getSpeed()) {

        // if(this.seq <= this.counter) {
        // if(this.reverse) {
        // this.servo.update(this.servo.getPulseWidth() + 2);
        // } else {
        // this.servo.update(this.servo.getPulseWidth() - 2);
        // }
        // this.counter = 0;
        // }
        // this.counter++;
        // }
        // if(this.currentCycles < this.amountOfCycles) {
        // this.currentCycles++;
        // int speed = this.beginSpeed + (int)Math.round(this.currentCycles * speedPerCycle);
        // if(this.reverse) {
        // this.servo.update(1500 + speed * 2);
        // } else {
        // this.servo.update(1500 - speed * 2);
        // }
        // }
    }

    /**
     * Immediately rotate the servo at the desired speed
     * @param   speed   the speed between -100 and 100 (0 is standing still)
     */
    public void setSpeed(int target, int time)
    {
        if(!(target >= -100 && target <= 100 && time > 0))
            throw new Error("Parameter out of bounds");

        // int difference = target - this.getSpeed();
        // this.seq = Math.abs(time/difference);
        // this.targetSpeed = target;
        // double difference = speed - this.getSpeed();
        // System.out.println(difference);
        // if(difference != 0) {
        // this.amountOfCycles = time;
        // this.speedPerCycle = difference / this.amountOfCycles;
        // this.currentCycles = 0;
        // }

        this.beginSpeed = this.getSpeed();

        double difference = target - this.beginSpeed;
        
        this.speedPerCycle = (difference / time);
        this.amountOfCycles = time + 1;
        this.currentCycles = 1;

        System.out.println("amountOfCycles: " + this.amountOfCycles + "; speedPerCycle: " + this.speedPerCycle + "; currentCycles: " + this.currentCycles + "; difference: " + difference + "; beginSpeed: " + beginSpeed);
    }

    public void setSpeedInstant(int speed)
    {
        if(!(speed >= -100 && speed <= 100))
            throw new Error("Parameter out of bounds");
        if(!this.reverse)
            this.servo.update(1500 + speed * 2);
        else
            this.servo.update(1500 - speed * 2);
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