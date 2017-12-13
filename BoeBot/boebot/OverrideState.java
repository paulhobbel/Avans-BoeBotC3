package boebot;

import java.awt.Color;
import TI.*;

import boebot.hardware.Remote.RemoteEvent;
import boebot.hardware.Ultrasone.UltrasoneEvent;
import boebot.hardware.Bluetooth.*;

import static boebot.Transmission.Speed.*;

/**
 * Write a description of class OverrideState here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class OverrideState extends State
{
    private Transmission transmission;

    public OverrideState() {
        this.transmission = new Transmission();
    }

    public void init(StateContext context) {
        context.setColor(Color.GREEN);
        
        context.setUltrasoneListener(new UltrasoneEvent()
        {
            @Override
            public void onDistance(int distance) {
                if(distance != -1 && distance < Constants.COLLISION_DISTANCE) {
                    //context.setState(new CollisionState());
                }
            }
        });
        
        context.setRemoteListener(new RemoteEvent()
        {
            @Override
            public void onCommand(Command command) {
                if(command.equals(Command.STANDBY)) {
                    context.setState(new IdleState());
                } else if(command.equals(Command.FIGURE_EIGHT)) {
                    context.setState(new EightState());
                }else {
                    handleCommand(command);
                }
            }
        });
        
        
    }

    public void update(StateContext context) {
        // We update our transmission manually per state
        this.transmission.update();
    }

    public void handleCommand(Command command) {
        switch(command) {
            case BREAK:
            this.transmission.brake(MEDIUM);
            break;
            
            case FORWARDS:
            this.transmission.forwards(FAST);
            break;
            case FORWARDS_CURVE_LEFT:
            this.transmission.curveLeftForwards(NORMAL_CURVE);
            break;
            case FORWARDS_CURVE_RIGHT:
            this.transmission.curveRightForwards(NORMAL_CURVE);
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