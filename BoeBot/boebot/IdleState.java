package boebot;

import java.awt.Color;
import java.util.*;
import TI.*;

import boebot.Transmission;
import boebot.output.Tone;
import static boebot.Transmission.Speed.*;
import boebot.output.MerryChristmas;

/**
 * Write a description of class IdleState here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 05-12-2017 (Version 1.0)
 */
public class IdleState extends State
{   
    private Transmission transmission;

    public IdleState() {
        this.transmission = new Transmission();
    }

    public void init(StateContext context) {
        context.setColor(Color.RED);

        context.setRemoteListener(command ->
            {
                if(command.equals(Command.STANDBY)) {
                    context.setState(new OverrideState());
                }
            }
        );

        // context.setBluetoothListener(message ->
            // {
                // // if(command.equals(Command.STANDBY)) {
                    // // context.setState(new OverrideState());
                // // }
                // System.out.println(message);
                // System.out.println(message.getFunction());
                // System.out.println(message.getData());
            // }
        // );
        
        context.setProtocolRouteListener(route -> context.setState(new LineFollowerState(route)));
        
        this.transmission.brake(MEDIUM);
        
        // final Tone tone = Tone.A;
        // tone.setDuration(1000);

        context.getNotification().playlist(new MerryChristmas());
        
        // context.getNotification().playSound(sContext -> sContext.addTone(tone));
    }

    public void update(StateContext context) {
        // We update our transmission manually per state
        this.transmission.update();
    }
}
