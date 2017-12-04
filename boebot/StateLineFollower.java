package boebot;


import boebot.output.LED.*;
import TI.*;
import static boebot.Transmission.Speed.*;
import boebot.hardware.LineFollower;

/**
 * Write a description of class StateLineFollower here.
 *
 * @author Paul, Thomas, Daan, Tim, Nick & Boudewijn
 * @version 05-12-2017 (Version 1.0)
 */
public class StateLineFollower extends State {



    public StateLineFollower(StateContext context)
    {
        super(context);
    }
    
    public void init(){
        context.setColor(Color.WHITE);
    }
    
    public void update(StateContext context){
        
    }
}