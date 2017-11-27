package boebot;

import TI.*;

import boebot.hardware.Remote;
import boebot.hardware.Remote.RemoteListener;

/**
 * Write a description of class OverrideState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OverrideState extends State
{   
    private Timer switchTimer;

    public OverrideState() {
        this.switchTimer = new Timer(1000);
        this.switchTimer.mark();
    }

    public void init(StateContext context) {
        this.switchTimer.mark();
    }

    public void update(StateContext context) {
        Robot robot = context.getRobot();
        
        if(robot.getCurrentDistance() != -1 && robot.getCurrentDistance() < 10) {
            context.setState(new CollisionState());
        }

        Command command = robot.getCurrentCommand();
        Transmission transmission = robot.getTransmission();

        if(command == Command.STANDBY) {
            if(this.switchTimer.timeout()) {
                context.setState(new IdleState());
            }
        } else {
            switch(command) {
                case BREAK:
                transmission.emergencyBrake();
                break;

                case FORWARDS:
                transmission.speed(100);
                break;
                case FORWARDS_CURVE_LEFT:
                transmission.curve(15, 75);
                break;
                case FORWARDS_CURVE_RIGHT:
                transmission.curve(75, 15);
                break;

                case BACKWARDS:
                transmission.speed(-100);
                break;
                case BACKWARDS_CURVE_LEFT:
                transmission.curve(-15, -75);
                break;
                case BACKWARDS_CURVE_RIGHT:
                transmission.curve(-75, -15);
                break;

                case RIGHT:
                transmission.turnSpeed(25);
                break;
                case LEFT:
                transmission.turnSpeed(-25);
                break;
                case RIGHT_NINETY:
                break;
                case LEFT_NINETY:
                break;
            }
        }
    }
}
