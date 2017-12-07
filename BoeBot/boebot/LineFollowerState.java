package boebot;

import java.awt.Color;
import java.util.ArrayList;
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

    private boolean busy = false;
    private int counter = 0;
    private int currentDirection;
    private ArrayList<Integer> directions = new ArrayList();
    private int directionIndex = 0;

    public LineFollowerState() {
        this.transmission = new Transmission();
        this.lineFollower = new LineFollower();

        directions.add(2); directions.add(0); directions.add(0); directions.add(0); directions.add(0); directions.add(2); directions.add(2); directions.add(2); 
    }

    public void update(StateContext context) {
        if(!this.busy) {
            if(this.lineFollower.onCrossing()) {
                if(getDirection() != -1) {
                    this.counter = 0;
                    crossingUpdate();
                    this.busy = true;
                    System.out.println("Crossing...");
                }
                else {
                    System.out.println("Done with the directions. Starting over...");
                    followLine();
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
        this.counter = 0;
    }

    private void followLine() {
        this.lineFollower.calculateTurn();
        this.transmission.speedLeft(this.lineFollower.getSpeedLeft(), this.lineFollower.getTimeLeft());
        this.transmission.speedRight(this.lineFollower.getSpeedRight(), this.lineFollower.getTimeRight());
    }

    private int getDirection() {
        if(this.directions.size() >= this.directionIndex) {
            this.currentDirection = -1;
            this.directionIndex = 0;
        }
        else {
            this.currentDirection = this.directions.get(this.directionIndex);
            this.directionIndex++;
        }
        return this.currentDirection;
    }

    private void crossingUpdate()  {
        switch(this.currentDirection) {
            case 0: //left
            if (this.counter == 0)
                this.transmission.speed(Constants.TP, 100);
            else if (this.counter >= 300)
                this.transmission.left(SLOW);
            else if(this.counter >= 1000 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case 1: //forwards
            if (this.counter == 0)
                this.transmission.speed(Constants.TP, 100);
            else if(this.counter >= 100)
                this.busy = false;
            this.counter++;
            break;

            case 2: //right
            if (this.counter == 0)
                this.transmission.speed(Constants.TP, 100);
            else if (this.counter >= 300)
                this.transmission.right(SLOW);
            else if(this.counter >= 1000 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;

            case 3: //backwards
            if (this.counter == 0)
                this.transmission.speed(Constants.TP, 100);
            else if (this.counter >= 300)
                this.transmission.right(SLOW);
            else if(this.counter >= 2000 && this.lineFollower.onLineNoError())
                this.busy = false;
            this.counter++;
            break;
        }
    }
}
