package boebot;

import TI.*;

import static boebot.Transmission.Speed.*;
import boebot.output.LED.Color;

/**
 * Write a description of class OverrideState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OverrideState extends State
{
    private Command lastCommand = Command.UNKNOWN;
    private Timer switchTimer;

    private Transmission transmission;

    public OverrideState(StateContext context) {
        super(context);
        //this.transmission = new Transmission();

        this.switchTimer = new Timer(1000);
        this.switchTimer.mark();
    }

    public void init() {
        this.switchTimer.mark();
        //if(!this.lastCommand.equals(Command.UNKNOWN)) {
        this.handleCommand(this.lastCommand);
        System.out.println("INIT");
        this.context.setColor(Color.GREEN);
        //}
    }

    public void update(StateContext context) {
        if(context.hasCollision()) {
            context.setState(new CollisionState(context));
        }

        //Transmission transmission = context.getTransmission();
        if(this.lastCommand != context.getCommand()) {
            this.lastCommand = context.getCommand();
            if(this.lastCommand == Command.STANDBY) {
                if(this.switchTimer.timeout()) {
                    context.setState(new IdleState(context));
                }
            } else {
                this.handleCommand(this.lastCommand);
            }
        }
    }

    private void handleCommand(Command command) {
        Transmission transmission = this.context.getTransmission();
        System.out.println(command);
        switch(command) {
            case BREAK:
            transmission.brake(FAST);
            break;

            case FORWARDS:
            transmission.forwards(FAST);
            break;
            case FORWARDS_CURVE_LEFT:
            transmission.curveLeftForwards(NORMAL_CURVE);
            break;
            case FORWARDS_CURVE_RIGHT:
            transmission.curveRightForwards(NORMAL_CURVE);
            break;

            case BACKWARDS:
            transmission.backwards(FAST);
            break;
            case BACKWARDS_CURVE_LEFT:
            transmission.curveLeftBackwards(NORMAL_CURVE);
            break;
            case BACKWARDS_CURVE_RIGHT:
            transmission.curveRightBackwards(NORMAL_CURVE);
            break;

            case RIGHT:
            transmission.right(SLOW);
            break;
            case LEFT:
            transmission.left(SLOW);
            break;
            case RIGHT_NINETY:
            break;
            case LEFT_NINETY:
            break;
        }
    }
}