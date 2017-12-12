package presentation;

import sun.swing.PrintColorUIResource;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GridButton {
    private Ellipse2D.Double circle;
    private Color color;

    public GridButton(int x, int y, int width, int height) {
        this.circle = new Ellipse2D.Double(x , y , width, height);
        this.color = new Color(51, 51, 51);
    }

    private void setColor(Color color){
        this.color = color;
    }
    public void setType(GridButtonType type) {
        switch(type) {
            case BASIC:
                this.setColor(new Color(51, 51, 51));
                break;
            case START:
                this.setColor(new Color(76, 175, 80));
                break;
            case POINT:
                this.setColor(new Color(255, 152, 0));
                break;
            case END:
                this.setColor(new Color(244, 67, 54));
                break;
        }
    }

    public Point getPoint() {
        return new Point(this.getX(), this.getY());
    }

    public int getX() {
        return (int)this.circle.getX();
    }

    public int getY() {
        return (int)this.circle.getY();
    }

    private int getWidth() {
        return (int)this.circle.getWidth();
    }

    private int getHeight() {
        return (int)this.circle.getHeight();
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(new Color(51, 51, 51));
    }

    public boolean collides(Point point){
        return this.circle.contains(point);
    }

    public enum GridButtonType {
        BASIC,
        START,
        POINT,
        END
    }
}
