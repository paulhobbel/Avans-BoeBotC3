package boebot;

import TI.*;
import static boebot.Transmission.Speed.*;
import boebot.output.LED.Color;

/**
 * Write a description of class IdleState here.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 05-12-2017 (Version 1.0)
 */
public class IdleState extends State
{   
    private Command lastCommand;
    private Timer switchTimer;
    
    public IdleState(StateContext context) {
        super(context);
        
        this.switchTimer = new Timer(1000);
        this.switchTimer.mark();
    }
    
    public void init() {
        this.switchTimer.mark();
        this.context.getTransmission().brake(SLOW);
        this.context.setColor(Color.RED);
    }
    
    public void update(StateContext context) {
        
        if(this.lastCommand != context.getCommand()) {
            this.lastCommand = context.getCommand();
            if(this.lastCommand == Command.STANDBY && this.switchTimer.timeout()) {
                context.setState(new OverrideState(context));
            }
        }
    }
}
