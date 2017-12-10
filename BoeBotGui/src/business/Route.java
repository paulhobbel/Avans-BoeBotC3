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
    private ArrayList<Direction> directions = new ArrayList();

    public void addDirection(GridButton currentPoint, int factor) {
        if(this.directions.size() > 0) {
            Direction lastDirection = this.directions.get(this.directions.size()-1);

            double degrees = Math.toDegrees(
                    Math.atan2(lastDirection.getX() - currentPoint.getX(), lastDirection.getY() - currentPoint.getY()));
            // if(currentPoint.getX() - previousPoint.getX() == factor && currentPoint.getY() - previousPoint.getY() == 0) {
            // ; //right
            // }
            System.out.println(degrees);
        } else {
            Direction direction = Direction.FORWARDS;
            direction.setButton(currentPoint);
            this.directions.add(direction);
        }
    }

    public String toString() {
        return Arrays.toString(this.directions.toArray());
    }

    public enum Direction
    {
        LEFT,
        FORWARDS,
        RIGHT,
        BACKWARDS;

        private GridButton button;

        public void setButton(GridButton button) {
            this.button = button;
        }

        public GridButton getButton() {
            return this.button;
        }

        public int getX() {
            if(this.button != null)
                return this.button.getX();
            else
                return 0;
        }

        public int getY() {
            if(this.button != null)
                return this.button.getY();
            else
                return 0;
        }
    }
}

/**
 * if(Math.abs(x - previousX) == stapgroote && Math.abs(y - previousY) == 0 || )
 */