package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.*;

/**
 * Write a description of class GridLine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GridLine
{
    private Color color;
    private int x , y , width, height;
    public GridLine(int x, int y, int width, int height)
    {
        this.color = Color.ORANGE;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setColor(Color color){
        this.color = color;
    }
    
    public void draw(Graphics g){   
        g.setColor(this.color);
        g.fillRect(x, y, width, height);
    }
}