package boebot;

/**
 * Write a description of class ColissionState here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CollisionState extends State
{
    private Transmission transmission;
    
    public CollisionState() {
        this.transmission = new Transmission();
    }
    
    public void update(StateContext context, Robot robot) {
        if(robot.getCurrentDistance() >= 10) {
            context.goBack();
        }
        Command command = robot.getCurrentCommand();

        switch(command) {
            case BACKWARDS:
            this.transmission.speed(-50);
            break;
            case BACKWARDS_CURVE_LEFT:
            case BACKWARDS_CURVE_RIGHT:
            case RIGHT:
            case LEFT:
            case RIGHT_NINETY:
            case LEFT_NINETY:
            System.out.println("Bou, push push dat Engine en Transmission... : " + command);
            break;
        }
    }
}
