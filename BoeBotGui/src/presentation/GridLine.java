package presentation;

import java.awt.*;

public class GridLine {
    private Color color;
    private int x , y , width, height;

    private Point pointA;
    private Point pointB;

    public GridLine(int x, int y, int width, int height)
    {
        this.color = Color.ORANGE;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GridLine(Point pointA, Point pointB) {
        this.color = Color.ORANGE;
        this.pointA = pointA;
        this.pointB = pointB;

        this.pointA.setLocation(this.pointA.getX()+13, this.pointA.getY()+13);
        this.pointB.setLocation(this.pointB.getX()+15+4, this.pointB.getY()+15+4);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void draw(Graphics g){

        Rectangle rectangle = new Rectangle(this.pointA);

        rectangle.add(this.pointB);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.fill(rectangle);

        //g2.

        //g.setColor(this.color);
        //g.fillRect(x, y, width, height);
        //g.fill
    }
}
