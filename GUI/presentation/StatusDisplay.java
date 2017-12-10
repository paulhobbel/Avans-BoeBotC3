package presentation;

import java.awt.Graphics;
import java.awt.Graphics.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JPanel;

/**
 * Write a description of class StatusDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatusDisplay extends JPanel {
    
    public StatusDisplay() {
        JPanel content = new JPanel();
        
        content.add(new JLabel("BoeBot state: IDLE"));
        content.add(new JLabel("BoeBot state: IDLE"));
        
        setVisible(true);
    }
    
    
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        
        g.drawRect( 0, 0, this.getWidth(), this.getHeight());
        
    }
}