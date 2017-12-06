package boebot;

import java.awt.Color;

import boebot.hardware.Remote.RemoteEvent;
import boebot.hardware.Ultrasone.UltrasoneEvent;

import static boebot.Transmission.Speed.*;
//import boebot.output.LED.Color;

/**
 * Class ColissionState 
 * 
 * Stops the BoeBot in case of being too close too an object in
 * front of it. 
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class CollisionState extends State
{
    Transmission transmission;

    /**
     * CollisionState Constructor
     */
    public CollisionState() {
        this.transmission = new Transmission();
    }

    /**
     * Method init
     *
     */
    public void init(StateContext context) {
        // Set the color of this state
        context.setColor(Color.ORANGE);
        
        // Change the listener of the ultrasone module
        context.setUltrasoneListener(new UltrasoneEvent()
            {
                @Override
                public void onDistance(int distance) {
                    if(distance != -1 && distance > Constants.COLLISION_DISTANCE) {
                        context.goBack();
                    }
                }
            }
        );
        
        // Change the listener of the remote IR
        context.setRemoteListener(new RemoteEvent()
            {
                @Override
                public void onCommand(Command command) {
                    if(command.equals(Command.STANDBY)) {
                        context.setState(new IdleState());
                    } else {
                        handleCommand(command);
                    }
                }
            }
        );
        
        this.transmission.brake(FAST);
    }

    /**
     * Method update
     *
     * @param context A parameter
     */
    @Override
    public void update(StateContext context) {
        // We update our transmission manually per state
        this.transmission.update();
    }

    private void handleCommand(Command command) {
        switch(command) {
            case BREAK:
            this.transmission.brake(FAST);
            break;

            case BACKWARDS:
            this.transmission.backwards(FAST);
            break;
            case BACKWARDS_CURVE_LEFT:
            this.transmission.curveLeftBackwards(NORMAL_CURVE);
            break;
            case BACKWARDS_CURVE_RIGHT:
            this.transmission.curveRightBackwards(NORMAL_CURVE);
            break;

            case RIGHT:
            this.transmission.right(SLOW);
            break;
            case LEFT:
            this.transmission.left(SLOW);
            break;
            case RIGHT_NINETY:
            break;
            case LEFT_NINETY:
            break;
        }
    }
}
