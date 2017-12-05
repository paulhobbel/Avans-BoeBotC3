package boebot;

import java.awt.Color;
import TI.*;

import boebot.hardware.Ultrasone.UltrasoneEvent;
import boebot.hardware.Remote.RemoteEvent;
import static boebot.Transmission.Speed.*;

/**
 * Write a description of class StateEight here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class EightState extends State{
    private int counter = 0;
    
    private Transmission transmission;
    
    public EightState() {
        this.transmission = new Transmission();
    }

    public void init(StateContext context){
        context.setColor(Color.BLUE);
        
        context.setUltrasoneListener(new UltrasoneEvent()
        {
            @Override
            public void onDistance(int distance) {
                if(distance != -1 && distance < Constants.COLLISION_DISTANCE) {
                    context.setState(new CollisionState());
                }
            }
        });
        
        context.setRemoteListener(new RemoteEvent()
        {
            @Override
            public void onCommand(Command command) {
                if(!command.equals(null)) {
                    context.goBack();
                }
            }
        });
    }

    public void update(StateContext context){
        if(counter < 3700){
            if(counter == 0){
                this.transmission.curveRightForwards(NORMAL_CURVE);
            }
            counter++;
        }else if(counter >=3700){
            if(counter == 3700){
                this.transmission.curveLeftForwards(NORMAL_CURVE);
            }
            counter++;
        }
        if(counter >= 8200){
            counter = 0;
            //context.goBack();
        }
        
        this.transmission.update();
    }
}
