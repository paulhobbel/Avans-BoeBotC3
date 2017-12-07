package boebot;

import java.awt.Color;
import TI.*;

import boebot.Transmission;
import static boebot.Transmission.Speed.*;

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

        context.setBluetoothListener(command ->
            {
                if(command.equals(Command.STANDBY)) {
                    context.setState(new OverrideState());
                }
            }
        );
        this.transmission.brake(MEDIUM);
    }

    public void update(StateContext context) {
        // We update our transmission manually per state
        this.transmission.update();
    }
}