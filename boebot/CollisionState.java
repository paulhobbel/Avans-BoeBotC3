package boebot;

import boebot.hardware.Remote.RemoteListener;
import boebot.hardware.Ultrasone.UltrasoneListener;

import static boebot.Transmission.Speed.*;
import boebot.output.LED.Color;

/**
 * Write a description of class ColissionState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CollisionState extends State
{   
    Command lastCommand = Command.UNKNOWN;

    public CollisionState(StateContext context) {
        super(context);
    }

    public void init() {
        // transmission.do(FORWARD, SLOW);
        // transmission.forward(SLOW);
        // 
        this.context.getTransmission().brake(SLOW);
        this.context.setColor(Color.ORANGE);
    }

    public void update(StateContext context) {
        if(!context.hasCollision()) {
            context.goBack();
        }

        if(this.lastCommand != context.getCommand()) {
            this.lastCommand = context.getCommand();

            Transmission transmission = context.getTransmission();
            switch(this.lastCommand) {
                case BREAK:
                transmission.brake(SLOW);
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
}
