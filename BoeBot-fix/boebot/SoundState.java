package boebot;

import java.awt.Color;
import java.util.Arrays;
import TI.*;

import boebot.output.Notification;

/**
 * Write a description of class SoundState here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class SoundState extends State
{
    private Notification notification;
    
    public SoundState() {
        notification = new Notification();
    }

    public void update(StateContext context) {
        ;
    }

    public void init(StateContext context) {
        context.setColor(Color.CYAN);
    }
}
