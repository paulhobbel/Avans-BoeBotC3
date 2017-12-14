package presentation;

import java.awt.*;

public class GridLine {
    private Color color;

    private Rectangle rectangle;


    public GridLine(Point pointA, Point pointB) {
        this.color = Color.ORANGE;

        pointA.setLocation(pointA.getX()+13, pointA.getY()+13);
        pointB.setLocation(pointB.getX()+15+4, pointB.getY()+15+4);

        this.rectangle = new Rectangle(pointA);

        rectangle.add(pointB);

    }

    public void setColor(Color color){
        this.color = color;
    }

    public void draw(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.fill(this.rectangle);

    }
}
