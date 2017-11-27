package boebot;

import boebot.Command;
import boebot.Transmission;

/**
 * Write a description of class CommandHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandHandler
{
    private Transmission transmission;
    
    public CommandHandler() {
        this.transmission = new Transmission();
    }
    
    public void handle(Command command) {
        switch(command) {
            case BREAK:
            transmission.emergencyBrake();
            break;

            case FORWARDS:
            transmission.speed(100);
            break;
            case FORWARDS_CURVE_LEFT:
            transmission.curve(15, 75);
            break;
            case FORWARDS_CURVE_RIGHT:
            transmission.curve(75, 15);
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
