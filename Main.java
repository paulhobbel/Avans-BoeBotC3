import java.util.ArrayList;
import TI.*;

import boebot.*;
import boebot.output.*;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main {
    
    /**
     * The main loop of the BoeBot
     */
    public static void main(String[] args) {
        ArrayList<Updatable> updatables = new ArrayList<>();
        updatables.add(new FadingLED(1, 10));
        updatables.add(new FlashingLED(6, 200));
        updatables.add(new Notification());
        
        
        while(true) {
            for(Updatable updatable : updatables) {
                updatable.update();
            }
            BoeBot.wait(1);
        }
    }
}
