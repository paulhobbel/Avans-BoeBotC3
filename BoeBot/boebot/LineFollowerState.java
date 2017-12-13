package boebot;

import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;
import TI.*;

import boebot.hardware.LightSensor;
import static boebot.Transmission.Speed.*;
import boebot.hardware.Engine;
import boebot.Constants;

import boebot.Route.RelativeDirection;

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

    private boolean busy = false;
    private int counter = 0;
    private boolean turnOnly = false;
    //private int currentDirection;
    private RelativeDirection currentDirection;
    //private int[] directions;
    private ArrayList<RelativeDirection> directions;
    private int directionIndex = 0;

    public LineFollowerState(Route route) {
        this.transmission = new Transmission();
        this.lineFollower = new LineFollower();

        //directions = new int[]{2, 0, 0, 0, 0, 2, 2, 2}; //eight
        //directions = new int[]{1, 2, 1, 2, 1, 2, 1, 2}; //square
        //directions = new int[]{1, 3}; //line

        this.directions = route.getDirections();
    }

    public void update(StateContext context) {
        if(!this.busy) {
            if(this.lineFollower.onCrossing() || this.turnOnly) {
                if(getDirection() != RelativeDirection.NOTHING) {
                    this.counter = 0;
                    crossingUpdate();
                    this.busy = true;
                    System.out.println("Crossing...");
                }
                else {
                    System.out.println("Done with the directions. Starting over...");
                    //followLine();
                    this.transmission.brake(MEDIUM);
                    context.setState(new IdleState());
                }
            }
            else {
                followLine();
            }
        }
        else {
            crossingUpdate();
        }
        this.transmission.update();
    }

    public void init(StateContext context) {
        context.setColor(Color.WHITE);
        this.busy = false;
        this.turnOnly = false;
        this.counter = 0;
    }

    private void followLine() {
        this.lineFollower.calculateTurn();
        this.transmission.speedLeft(this.lineFollower.getSpeedLeft(), this.lineFollower.getTimeLeft());
        this.transmission.speedRight(this.lineFollower.getSpeedRight(), this.lineFollower.getTimeRight());
    }

    private RelativeDirection getDirection() {
        System.out.println("size: " + this.directions.size() + " index: " + this.directionIndex);
        
        if(this.directions.size()-1 == this.directionIndex) {
            this.turnOnly = true;
            System.out.println("turnOnly");
        } 
        
        if(this.directions.size() <= this.directionIndex) {
            //this.currentDirection = -1;
            this.currentDirection = RelativeDirection.NOTHING;
            //this.directionIndex = 0;
        }
        else {
            this.currentDirection = this.directions.get(this.directionIndex);
            this.directionIndex++;
        }
        System.out.println(this.currentDirection);
        return this.currentDirection;
    }

    private void crossingUpdate()  {
        /*
         * times for TP = 25:       TP = 75
         * turn : 0, 250, 500       0, 70, 500
         * 180:   0, 250, 1500      0, 70, 1500
         */

        switch(this.currentDirection) {
            case LEFT: //left
            if (this.counter == 0) {
                if(this.turnOnly) {
                    System.out.println("turnOnly Drive");
                    this.transmission.left(SLOW);
                }
                else
                    this.transmission.speed(Constants.TP, 100);
            }
            else if (this.counter == 70)
                this.transmission.left(SLOW);
            else if (this.counter >= 500 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case FORWARDS: //forwards
            if (this.counter == 0)
                this.transmission.speed(Constants.TP, 100);
            else if (this.counter >= 100)
                this.busy = false;
            this.counter++;
            break;

            case RIGHT: //right
            if (this.counter == 0) {
                if(this.turnOnly)
                    this.transmission.right(SLOW);
                else
                    this.transmission.speed(Constants.TP, 100);
            }
            else if (this.counter == 70)
                this.transmission.right(SLOW);
            else if (this.counter >= 500 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case BACKWARDS: //backwards
            if (this.counter == 0) {
                if(this.turnOnly)
                    this.transmission.right(SLOW);
                else
                    this.transmission.speed(Constants.TP, 100);
            }
            else if (this.counter == 70)
                this.transmission.right(SLOW);
            else if (this.counter >= 1500 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;
        }
    }
}
