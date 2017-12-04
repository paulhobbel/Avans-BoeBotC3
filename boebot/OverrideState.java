package boebot;

import TI.*;
import boebot.output.LED.*;
import static boebot.Transmission.Speed.*;

/**
 * Write a description of class OverrideState here.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 05-12-2017 (Version 1.0)
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
        if(this.lastCommand == Command.FIGURE_EIGHT){
            context.setState(new StateEight(context));
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
            transmission.brake(SLOW);
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