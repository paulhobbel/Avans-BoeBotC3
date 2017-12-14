package business;

import presentation.GridButton;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Route here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Route {
    private ArrayList<RawDirection> rawDirections = new ArrayList<>();

//    public void addDirection(GridButton currentPoint, int factor) {
//        if(this.directions.size() > 0) {
//            Direction lastDirection = this.directions.get(this.directions.size()-1);
//
//            double degrees = Math.toDegrees(
//                    Math.atan2(lastDirection.getX() - currentPoint.getX(), lastDirection.getY() - currentPoint.getY()));
//            // if(currentPoint.getX() - previousPoint.getX() == factor && currentPoint.getY() - previousPoint.getY() == 0) {
//            // ; //right
//            // }
//            System.out.println(degrees);
//        } else {
//            Direction direction = Direction.FORWARDS;
//            //direction.setButton(currentPoint);
//            this.directions.add(direction);
//        }
//    }

    public void addRawDirection(RawDirection direction) {
        this.rawDirections.add(direction);
    }

    public void resetRawDirections() {
        this.rawDirections.clear();
    }

    public ArrayList<RelativeDirection> calculateRelativePath() {
        ArrayList<RelativeDirection> path = new ArrayList<>();
        if(this.rawDirections.size() < 1) {
            return path;
        }
        
        for(int i = 1; i < this.rawDirections.size(); i++) {
            int previousDirection = this.rawDirections.get(i - 1).ordinal() + 1;
            int currentDirection = this.rawDirections.get(i).ordinal() + 1;


            int relativeDirection = currentDirection - previousDirection;

            if(relativeDirection == 3) {
                relativeDirection = -1;
            }
            else if(relativeDirection == -3) {
                relativeDirection = 1;
            }
            else if(relativeDirection == -2) {
                relativeDirection = 2;
            }

            relativeDirection++;
            path.add(RelativeDirection.values()[relativeDirection]);
        }

        // Add correction as final turn
        RawDirection lastDirection = this.rawDirections.get(this.rawDirections.size()-1);
        switch (lastDirection) {
            case NORTH:
                path.add(RelativeDirection.NOTHING);
                break;
            case EAST:
                path.add(RelativeDirection.LEFT);
                break;
            case WEST:
                path.add(RelativeDirection.RIGHT);
                break;
            case SOUTH:
                path.add(RelativeDirection.BACKWARDS);
                break;
        }

        System.out.println(Arrays.toString(path.toArray()));

        return path;
    }

    public String toString() {
        return Arrays.toString(this.calculateRelativePath().toArray());
    }

    public enum RelativeDirection {
        LEFT,
        FORWARDS,
        RIGHT,
        BACKWARDS,
        NOTHING
    }

    public enum RawDirection {
        NORTH,  // 0
        EAST,   // 1
        SOUTH,  // 2
        WEST    // 3
    }

}