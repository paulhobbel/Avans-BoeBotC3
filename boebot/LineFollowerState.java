package boebot;

import java.awt.Color;
import TI.*;

import boebot.hardware.LineFollower;
import static boebot.Transmission.Speed.*;

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
    public LineFollowerState() {
        
    }
    
    public void init(StateContext context) {
        context.setColor(Color.WHITE);
    }
    
    public void update(StateContext context) {
        
    }
}