package boebot;

import boebot.hardware.LightSensor;
import TI.*;

/**
 * Write a description of class LineFollwer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LineFollower
{
    private int integral = 0;
    private int lastError = 0;
    private int derivative = 0;
    private int error = 0;
    private int turn = 0;

    private int valueLeft;
    private int valueMiddle;
    private int valueRight;

    private int white = 3500;
    private int black = 2400;

    private int speedLeft;
    private int speedRight;
    private int timeLeft;
    private int timeRight;

    private LightSensor lightSensorLeft;
    private LightSensor lightSensorMiddle;
    private LightSensor lightSensorRight;
    
    private LineFollowerEventListener listener;
    
    public LineFollower()
    {
        lightSensorLeft = new LightSensor(Constants.LIGHT_RIGHT_PIN);
        lightSensorMiddle = new LightSensor(Constants.LIGHT_MIDDLE_PIN);
        lightSensorRight = new LightSensor(Constants.LIGHT_LEFT_PIN);
    }

    public boolean offTrack() {
        boolean answer = false;
        if(lightSensorLeft.getValue() > this.white - Constants.ACCURACY && lightSensorMiddle.getValue() > this.white - Constants.ACCURACY && 
        lightSensorRight.getValue() > this.white - Constants.ACCURACY)
            answer = true;
        return answer;
    }

    public boolean onCrossing() {
        boolean answer = false;
        if(lightSensorLeft.getValue() < this.black + Constants.ACCURACY && lightSensorMiddle.getValue() < this.black + Constants.ACCURACY && 
        lightSensorRight.getValue() < this.black + Constants.ACCURACY)
            answer = true;
        return answer;
    }

    public boolean onLine() {
        this.valueLeft = this.lightSensorLeft.getValue();
        this.valueMiddle = this.lightSensorMiddle.getValue();
        this.valueRight = this.lightSensorRight.getValue();
        
        boolean answer = false;
        if(this.valueLeft < this.black + Constants.ACCURACY || this.valueMiddle < this.black + Constants.ACCURACY || this.valueRight < this.black + Constants.ACCURACY)
            answer = true;
        return answer;
    }
    
    public boolean onLineNoError() {
        boolean answer = false;
        this.error = getError();
        if ((this.error > -50 && this.error < 50) && onLine())
            answer = true;
        return answer;
    }

    public void calculateTurn() {
        this.error = getError();
        //System.out.println("Error: " + this.error);

        this.integral += this.error;
        this.derivative = this.error - this.lastError;
        this.lastError = this.error;
        this.turn = (int)(Constants.KP * this.error + Constants.KI * this.integral + Constants.KD * this.derivative);
        this.integral *= Constants.DAMP;
        //System.out.println("Turn: " + this.turn);

        setSpeedLeft(Constants.TP - this.turn);
        setSpeedRight(Constants.TP + this.turn);

        // this.engineLeft.update();
        // this.engineRight.update();
    }

    public int getError() {
        this.valueLeft = this.lightSensorLeft.getValue();
        this.valueMiddle = this.lightSensorMiddle.getValue();
        this.valueRight = this.lightSensorRight.getValue();
        this.error = (this.valueLeft + this.valueMiddle) - (this.valueMiddle + this.valueRight);
        return this.error;
    }

    public void setSpeedLeft(int speed) {
        if(speed < -100)
            speed = -100;
        else if(speed > 100)
            speed = 100;
        this.speedLeft = speed;

        this.timeLeft = (int)(Math.abs(Constants.ENGINE_LEFT.getSpeed()-speed)*Constants.ACCELERATION);
        if(this.timeLeft < 1)
            this.timeLeft = 1;
        //System.out.println("Speed: " + this.speedLeft + " Time: " + this.timeLeft);
    }

    public int getSpeedLeft() {
        return this.speedLeft;
    }

    public int getTimeLeft() {
        return this.timeLeft;
    }

    public void setSpeedRight(int speed) {
        if(speed < -100)
            speed = -100;
        else if(speed > 100)
            speed = 100;
        this.speedRight = speed;

        this.timeRight = (int)(Math.abs(Constants.ENGINE_RIGHT.getSpeed()-speed)*Constants.ACCELERATION);
        if(this.timeRight < 1)
            this.timeRight = 1;
    }

    public int getSpeedRight() {
        return this.speedRight;
    }

    public int getTimeRight() {
        return this.timeRight;
    }
    
    public void setEventListener(LineFollowerEventListener listener) {
        this.listener = listener;
    }
    
    public void update() {
        if(this.listener != null) {
            if(this.onCrossing()) {
                this.listener.onCrossing();
            }
            
            if(this.onLine()) {
                this.listener.onLine();
            }
            
            if(this.onLineNoError()) {
                this.listener.onLineNoError();
            }
        }
    }
    
    public interface LineFollowerEventListener {
        public void onCrossing();
        public void onLine();
        public void onLineNoError();
    }
}
