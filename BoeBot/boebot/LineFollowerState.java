package boebot;

import java.awt.Color;
import TI.*;

import boebot.hardware.LightSensor;
import static boebot.Transmission.Speed.*;
import boebot.hardware.Engine;
import boebot.Constants;

/**
 * Write a description of class StateLineFollower here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class LineFollowerState extends State {
    private Transmission transmission;
    private LineFollower lineFollower;
    private boolean turning = false;
    private Timer timer;

    public LineFollowerState() {
        this.transmission = new Transmission();
        this.lineFollower = new LineFollower();
    }

    public void update(StateContext context) {
        if(!turning) {
            if(lineFollower.onCrossing()) {
                // future if-statement
                turning(0);
                turning = true;
                System.out.println("Crossing...");
            }
            else {
                lineFollower.calculateTurn();
                followLine();
            }
        }
        else if(timer.timeout()) {
            turning = false;
        }
        this.transmission.update();
    }

    public void init(StateContext context) {
        context.setColor(Color.WHITE);
        turning = false;
    }

    private void followLine() {
        transmission.speedLeft(lineFollower.getSpeedLeft(), lineFollower.getTimeLeft());
        transmission.speedRight(lineFollower.getSpeedRight(), lineFollower.getTimeRight());
    }

    private void turning(int direction)  {
        switch(direction) {
            case 0:
            timer = new Timer(800);
            timer.mark();
            transmission.left(SLOW);
            break;
            case 1:

            break;

            case 2:

            break;

            case 3:

            break;
        }
    }
}
