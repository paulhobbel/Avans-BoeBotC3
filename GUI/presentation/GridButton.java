package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.*;
import java.awt.geom.Ellipse2D.Double;

/**
 * Write a description of class GridButton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GridButton 
{
    private Double circle;
    private Color color;
    
    public GridButton(int x, int y, int width, int height) {
        this.circle = new Double(x , y , width, height);
        this.color = Color.BLACK;
    }
    
    public void setColor(Color color){
        this.color = color;
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
    
    public int getWidth() {
        return (int)this.circle.getWidth();
    }
    
    public int getHeight() {
        return (int)this.circle.getHeight();
    }
    
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public boolean collides(Point point){
       return this.circle.contains(point);
    }
}
