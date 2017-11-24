package boebot;

import TI.*;

import boebot.hardware.Remote;
import boebot.hardware.Remote.RemoteListener;

/**
 * Write a description of class IdleState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IdleState extends State
{   
    private Timer switchTimer;
    
    public IdleState() {
        this.switchTimer = new Timer(1000);
        this.switchTimer.mark();
    }
    
    public void init() {
        this.switchTimer.mark();
    }
    
    public void update(StateContext context, Robot robot) {
        if(robot.getCurrentCommand() == Command.STANDBY) {
            if(this.switchTimer.timeout()) {
                System.out.println("Switching to OverrideState");
                context.setState(new OverrideState());
            }
        }
    }
}
