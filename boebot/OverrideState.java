package boebot;

import TI.*;

import boebot.hardware.Remote;
import boebot.hardware.Remote.RemoteListener;

/**
 * Write a description of class OverrideState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class OverrideState extends State
{   
    private Timer switchTimer;
    private CommandHandler handler;

    public OverrideState() {
        this.switchTimer = new Timer(1000);
        this.switchTimer.mark();
        
        this.handler = new CommandHandler();
    }

    public void init(StateContext context) {
        this.switchTimer.mark();
    }

    public void update(StateContext context, Robot robot) {
        this.handler.update();
        if(robot.getCurrentDistance() != -1 && robot.getCurrentDistance() < 10) {
            context.setState(new CollisionState());
        }
        
        Command command = robot.getCurrentCommand();
        
        if(command == Command.STANDBY) {
            if(this.switchTimer.timeout()) {
                context.setState(new IdleState());
            }
        } else {
            this.handler.handle(command);
        }
    }
}
