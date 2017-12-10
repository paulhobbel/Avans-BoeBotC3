package boebot.output;

import TI.*;
import java.util.ArrayList;

import boebot.Updatable;
import boebot.Constants;

import boebot.hardware.Speaker;

/**
 * General class to send notifcations.
 *
 * @author Paul Hobbel
 * @author Nick Kerremans
 * @version 07-12-2017 (version 1.0)
 */
public class Notification
{
    private LedContext ledContext;
    private SoundContext soundContext;

    /**
     * Notification constructor.
     */
    public Notification() {
        this.ledContext = new LedContext();
        this.soundContext = new SoundContext();
    }
    
    public void playSound(Sound sound) {
        this.soundContext.setSound(sound);
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

    public class SoundContext {
        private Sound currentSound;
        private Speaker speaker;
        
        public SoundContext() {
            this.speaker = new Speaker(Constants.SPEAKER_PIN);
            Thread speakerThread = new Thread(this.speaker);
            speakerThread.start();
        }
        
        /**
         * Set the current sound state.
         * 
         * @param sound set sound
         */
        public void setSound(Sound sound) {
            this.speaker.removeAllTones();
            this.currentSound = sound;
            this.currentSound.play(this);
        }
        
        /**
         * Add a new tone to play.
         * 
         * @param tone The tone to play
         */
        public void addTone(Tone tone) {
            this.speaker.addTone(tone);
        }
        
        /**
         * Add a set of tones to play.
         * 
         * @param tones The set of tones to play
         */
        public void addTones(ArrayList<Tone> tones) {
            this.speaker.addTones(tones);
        }
    }
}
