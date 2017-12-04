package boebot;

import boebot.hardware.Remote.RemoteListener;
import boebot.hardware.Ultrasone.UltrasoneListener;

import static boebot.Transmission.Speed.*;
import boebot.output.LED.Color;

/**
 * Class ColissionState 
 * 
 * Stops the BoeBot in case of being too close too an object in
 * front of it. 
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 05-12-2017 (Version 1.0)
 */
public class CollisionState extends State
{   
    Command lastCommand = Command.UNKNOWN;

    /**
     * CollisionState Constructor
     *
     * @param context A parameter
     */
    public CollisionState(StateContext context) {
        super(context);
    }

    /**
     * Method init
     *
     */
    public void init() {
        // transmission.do(FORWARD, SLOW);
        // transmission.forward(SLOW);
        // 
        this.context.getTransmission().brake(SLOW);
        this.context.setColor(Color.ORANGE);
    }

    /**
     * Method update
     *
     * @param context A parameter
     */
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
