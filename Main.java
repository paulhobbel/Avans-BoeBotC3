import java.util.ArrayList;
import TI.*;

import boebot.*;
import boebot.output.*;

import boebot.hardware.Remote;
import boebot.listeners.RemoteListener;
import boebot.hardware.Ultrasone;
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
        updatables.add(new Remote(4,
                new RemoteListener() {
                    public Command onCommandUpdate(int lengths[]) {
                        int code = 0;
                        int id = 0;

                        for(int i = 0; i < 12; i++) {
                            if(lengths[i] > 900) {
                                if(i < 7) { // Button Code
                                    code |= (1<<i);
                                } else {     // ID
                                    id |= (1<<i-7);
                                }
                            }
                        }
                        Command command = Command.forCodeAndID(code, id);
                        System.out.println(command);
                        return command;
                    }
                }));
        updatables.add(new Ultrasone());

        while(true) {
            for(Updatable updatable : updatables) {
                updatable.update();
            }
            BoeBot.wait(1);
        }
    }
}
