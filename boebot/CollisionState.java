package boebot;

/**
 * Write a description of class ColissionState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CollisionState extends State
{
    private CommandHandler handler;
    
    public CollisionState() {
        this.handler = new CommandHandler();
    }
    
    public void update(StateContext context, Robot robot) {
        if(robot.getCurrentDistance() != -1 && robot.getCurrentDistance() > 10) {
            context.goBack();
        }
        
        Command command = robot.getCurrentCommand();
        
        if(command != Command.FORWARDS && command != Command.FORWARDS_CURVE_LEFT && command != Command.FORWARDS_CURVE_RIGHT) {
            this.handler.handle(command);
        }
    }
}
