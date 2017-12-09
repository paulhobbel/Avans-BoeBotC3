package boebot.output;

import TI.*;
import boebot.Updatable;
import boebot.Robot;

/**
 * General class to send notifcations.
 *
 * @author Paul Hobbel
 * @author Nick Kerremans
 * @version 07-12-2017 (version 1.0)
 */
public class Notification extends Updatable
{
    private Robot robot;

    private LedContext ledContext;
    private SoundContext soundContext;

    /**
     * Notification constructor.
     */
    public Notification(Robot robot) {
        this.robot = robot;

        this.ledContext = new LedContext();
        this.soundContext = new SoundContext();
    }

    /**
     * Get the LedContext.
     */
    public LedContext getLedContext() {
        return this.ledContext;
    }

    /**
     * Get the SoundContext.
     */
    public SoundContext getSoundContext() {
        return this.soundContext;
    }
    
    /**
     * Method to update the notification?
     */
    public void update() {

    }

    public class LedContext extends Updatable {
        private Led currentLed;
        
        /**
         * Set the current led state.
         * 
         * @param led current led
         */
        public void setLed(Led led) {
            this.currentLed = led;
        }
        
        /**
         * Update the currentLed.
         */
        public void update() {
            if(this.currentLed != null) {
                this.currentLed.update(this);
            }
        }
    }

    public class SoundContext extends Updatable {
        private Sound currentSound;
        
        /**
         * Set the current sound state.
         * 
         * @param sound set sound
         */
        public void setSound(Sound sound) {
            this.currentSound = sound;
        }

        /**
         * Update the currentSound.
         */
        public void update() {
            if(this.currentSound != null) {
                this.currentSound.update(this);
            }
        }
    }
}
