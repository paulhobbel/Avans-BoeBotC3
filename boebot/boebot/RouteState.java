package boebot;

import java.awt.Color;
import java.util.*;
import TI.*;

import boebot.hardware.LightSensor;
import boebot.Route;
import boebot.Route.RelativeDirection;
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
public class RouteState extends State {
    private Transmission transmission;
    private LineFollower lineFollower;

    private boolean busy = false;
    private boolean done = false;
    private boolean turnOnly = false;
    private boolean offTrack = false;
    private int counter = 0;
    private RelativeDirection currentDirection;
    private ArrayList<RelativeDirection> directions;
    private int directionIndex = 0;

    public RouteState(Route route) {
        this.transmission = new Transmission();
        this.lineFollower = new LineFollower();
        this.directions = route.getDirections();
        //directions = new int[]{2, 0, 0, 0, 0, 2, 2, 2}; //eight
        //directions = new int[]{1, 2, 1, 2, 1, 2, 1, 2}; //square
        //directions = new int[]{1, 1, 1, 3}; //line
        //directions = new int[]{2, 0, 2, 0, 2, 2, 2, 0, 2, 0, 2, 2}; //squiggle
        //directions = new int[]{2, 2, 1, 2, 2, 2, 1, 1, 3, 1, 1, 2}; //something
        //directions = new int[]{0, 0, 2, 3, 0, 0, 2, 3, 0, 0, 2, 3, 0, 0, 2, 3}; //another thing
    }

    public void update(StateContext context) {
        if(!this.offTrack) {
            if(!this.busy) {
                if(this.lineFollower.onCrossing()) {
                    getDirection();
                    if(!done) {
                        this.counter = 0;
                        crossingUpdate();
                        this.busy = true;
                        System.out.println("Crossing...");
                    }
                    else {
                        System.out.println("Done with the directions");
                        this.transmission.brake(SLOW);
                        this.directionIndex = 0;
                        followLine();
                    }
                }
                else if(this.lineFollower.offTrack()) {
                    this.offTrack = true;
                    System.out.println("OffTrack: " + this.lineFollower.offTrack());
                    this.transmission.speed(0, 50);
                }
                else {
                    followLine();
                }
            }
            else {
                crossingUpdate();
            }
        }
        else if(!this.lineFollower.offTrack()) {
            this.offTrack = false;
        }
        this.transmission.update();
    }

    public void init(StateContext context) {
        context.setColor(Color.WHITE);
        this.busy = false;
        this.done = false;
        this.turnOnly = false;
        this.counter = 0;
    }

    private void followLine() {
        this.lineFollower.calculateTurn();
        this.transmission.speedLeft(this.lineFollower.getSpeedLeft(), this.lineFollower.getTimeLeft());
        this.transmission.speedRight(this.lineFollower.getSpeedRight(), this.lineFollower.getTimeRight());
    }

    private RelativeDirection getDirection() {
        if(this.directions.size() <= this.directionIndex) {
            this.done = true;
        }
        else if(this.directions.size() - 1 <= this.directionIndex) {
            this.turnOnly = true;
        }
        else {
            this.currentDirection = this.directions.get(this.directionIndex);
            this.directionIndex++;
        }
        return this.currentDirection;
    }

    private void crossingUpdate()  {
        /*
         * times for TP = 25:       TP = 75         TP = 90
         * turn : 0, 250, 500       0, 70, 500      0, 50, 500
         * 180:   0, 250, 1500      0, 70, 1500     0, 50, 1500
         */

        switch(this.currentDirection) {
            case LEFT: //left
            if (this.counter == 0) {
                if(this.turnOnly)
                    this.transmission.left(SLOW);
                else
                    this.transmission.speed(Constants.TP, 100);
            }
            else if (this.counter == 50)
                this.transmission.left(SLOW);
            else if (this.counter >= 500 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case FORWARDS: //forwards
            if (this.counter == 0)
                this.transmission.speed(Constants.TP, 100);
            else if (this.counter >= 50)
                this.busy = false;
            this.counter++;
            break;

            case RIGHT: //right
            if (this.counter == 0) {
                if(this.turnOnly)
                    this.transmission.left(SLOW);
                else
                    this.transmission.speed(Constants.TP, 100);
            }
            else if (this.counter == 50)
                this.transmission.right(SLOW);
            else if (this.counter >= 500 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case BACKWARDS: //backwards
            if (this.counter == 0) {
                if(this.turnOnly)
                    this.transmission.left(SLOW);
                else
                    this.transmission.speed(Constants.TP, 100);
            }
            else if (this.counter == 50)
                this.transmission.right(SLOW);
            else if (this.counter >= 1500 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case NOTHING: //nothing
            this.busy = false;
            break;
        }
    }
}
