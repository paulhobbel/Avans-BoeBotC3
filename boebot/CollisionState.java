package boebot;

/**
 * Write a description of class ColissionState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CollisionState extends State
{   
    public void update(StateContext context) {
        Robot robot = context.getRobot();
        if(robot.getCurrentDistance() != -1 && robot.getCurrentDistance() > 10) {
            context.goBack();
        }
        
        Command command = robot.getCurrentCommand();
        Transmission transmission = robot.getTransmission();
        switch(command) {
            case BREAK:
            transmission.emergencyBrake();
            break;

            case BACKWARDS:
            transmission.speed(-100);
            break;
            case BACKWARDS_CURVE_LEFT:
            transmission.curve(-15, -75);
            break;
            case BACKWARDS_CURVE_RIGHT:
            transmission.curve(-75, -15);
            break;

            case RIGHT:
            transmission.turnSpeed(25);
            break;
            case LEFT:
            transmission.turnSpeed(-25);
            break;
            case RIGHT_NINETY:
            break;
            case LEFT_NINETY:
            break;
        }
    }
}
