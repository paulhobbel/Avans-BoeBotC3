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
    private Transmission transmission;

    public OverrideState() {
        this.switchTimer = new Timer(1000);
        this.switchTimer.mark();

        this.transmission = new Transmission();
    }

    public void init() {
        this.switchTimer.mark();
        this.transmission.emergencyBrake();
    }

    public void update(StateContext context, Robot robot) {
        if(robot.getCurrentDistance() != -1 && robot.getCurrentDistance() < 10) {
            context.setState(new CollisionState());
        }

        switch(robot.getCurrentCommand()) {
            case FORWARDS:
            this.transmission.speed(50);
            break;
            
            case BACKWARDS:
            this.transmission.speed(-50);
            break;
            
            case BACKWARDS_CURVE_LEFT:
            case BACKWARDS_CURVE_RIGHT:
            
            case RIGHT:
            this.transmission.turnSpeed(25);
            break;
            
            case LEFT:
            this.transmission.turnSpeed(-25);
            break;
            
            case RIGHT_NINETY:
            case LEFT_NINETY:
            break;

            case STANDBY:
            if(this.switchTimer.timeout()) {
                System.out.println("Switching to last state");
                context.goBack();
            }
            break;
        }
    }
}
