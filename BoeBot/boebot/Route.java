package boebot;

import java.util.ArrayList;

/**
 * Write a description of class Route here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Route
{
    // instance variables - replace the example below with your own
    private ArrayList<RelativeDirection> directions = new ArrayList<>();

    /**
     * Constructor for objects of class Route
     */
    public Route()
    {

    }

    public void addDirection(RelativeDirection direction) {

        this.directions.add(direction);
    }

    public ArrayList<RelativeDirection> getDirections() {
        return this.directions;
    }

    public static Route fromStringArray(String[] directions) {
        Route route = new Route();
        for(String direction : directions) {
            try {
                route.addDirection(RelativeDirection.valueOf(direction));
            } catch(Exception e) {
                // Probs got an unsupported direction, just log for now...
                System.out.println(e);
            }
        }
        return route;
    }

    public enum RelativeDirection {
        LEFT,
        FORWARDS,
        RIGHT,
        BACKWARDS,
        NOTHING
    }
}
