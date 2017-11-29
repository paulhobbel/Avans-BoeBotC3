package boebot;

import boebot.output.LED.*;
import TI.*;
import static boebot.Transmission.Speed.*;

public class StateEight extends State{
    private Timer contextTimer;
    private int start;
    private int counter = 0;
    private Command lastCommand;
    public StateEight(StateContext context){
        super(context);
        
        this.contextTimer = new Timer(1000);
        this.contextTimer.mark();
    }

    public void init(){
        this.contextTimer.mark();
        context.setColor(Color.BLUE);
    }

    public void update(StateContext context){
        if(context.hasCollision()){
            context.setState(new CollisionState(context));
        }
        
        if(this.lastCommand != context.getCommand()){
            this.lastCommand = context.getCommand();
            if(this.contextTimer.timeout()){
                context.setState(new OverrideState(context));
            }
        }
        if(counter < 3700){
            if(counter == 0){
                this.context.getTransmission().curveRightForwards(NORMAL_CURVE);
            }
            counter++;
        }else if(counter >=3700){
            if(counter == 3700){
                this.context.getTransmission().curveLeftForwards(NORMAL_CURVE);
            }
            counter++;
        }
        if(counter >= 8200){
            counter = 0;
        }
    }
}
