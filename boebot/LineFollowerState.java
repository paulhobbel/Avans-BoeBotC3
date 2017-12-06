package boebot;

import java.awt.Color;
import TI.*;

import boebot.hardware.LightSensor;
import static boebot.Transmission.Speed.*;
import boebot.hardware.Engine;
import boebot.Constants;

/**
 * Write a description of class StateLineFollower here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class LineFollowerState extends State {
    private int integral = 0;
    private int lastError = 0;
    private int derivative = 0;
    private int error = 0;
    private int turn = 0;

    private int valueLeft;
    private int valueMiddle;
    private int valueRight;

    private LightSensor lightSensorLeft;
    private LightSensor lightSensorMiddle;
    private LightSensor lightSensorRight;

    private Engine engineLeft;
    private Engine engineRight;

    public LineFollowerState() {
        engineLeft = new Engine(Constants.SERVO_LEFT_PIN, true);
        engineRight = new Engine(Constants.SERVO_RIGHT_PIN, false);

        lightSensorLeft = new LightSensor(Constants.LIGHT_RIGHT_PIN);
        lightSensorMiddle = new LightSensor(Constants.LIGHT_MIDDLE_PIN);
        lightSensorRight = new LightSensor(Constants.LIGHT_LEFT_PIN);
    }

    public void init(StateContext context) {
        context.setColor(Color.WHITE);
    }

    @Override
    public void update(StateContext context) {
        this.valueLeft = this.lightSensorLeft.getValue();
        this.valueMiddle = this.lightSensorMiddle.getValue();
        this.valueRight = this.lightSensorRight.getValue();
        this.error = getError(valueLeft, valueMiddle, valueRight);
        //System.out.println("Error: " + this.error);

        this.integral += this.error;
        this.derivative = this.error - this.lastError;
        this.lastError = this.error;
        this.turn = (int)(Constants.KP * this.error + Constants.KI * this.integral + Constants.KD * this.derivative);
        this.integral *= Constants.DAMP;
        //System.out.println("Turn: " + this.turn);

        setSpeedLeft(Constants.TP - this.turn);
        setSpeedRight(Constants.TP + this.turn);

        this.engineLeft.update();
        this.engineRight.update();
    }

    public int getError(int lightSensorLeft, int lightSensorMiddle, int lightSensorRight) {
        return (lightSensorLeft + lightSensorMiddle) - (lightSensorMiddle + lightSensorRight);
    }

    public void setSpeedLeft(int speed) {
        if(speed < -100)
            speed = -100;
        else if(speed > 100)
            speed = 100;

        int time = (int)(Math.abs(engineLeft.getSpeed()-speed)*Constants.ACCELERATION);
        if(time < 1)
            time = 1;
        this.engineLeft.setSpeed(speed, time);
    }

    public void setSpeedRight(int speed) {
        if(speed < -100)
            speed = -100;
        else if(speed > 100)
            speed = 100;

        int time = (int)(Math.abs(engineLeft.getSpeed()-speed)*Constants.ACCELERATION);
        if(time < 1)
            time = 1;
        this.engineRight.setSpeed(speed, time);
    }
}
