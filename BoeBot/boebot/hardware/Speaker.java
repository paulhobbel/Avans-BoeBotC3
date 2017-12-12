package boebot.hardware;

import TI.*;
import java.util.ArrayList;

import boebot.Updatable;

import boebot.output.Tone;

/**
 * A hardware class to interact with the speaker.
 *
 * @author Paul Hobbel
 * @author Nick Kerremans
 * @author Daan van Kempen
 * @version 09-12-2017
 */
public class Speaker implements Runnable {
    private int pin;
    private ArrayList<Tone> tones = new ArrayList<>();

    /**
     * Speaker Constructor
     * 
     * @param pin Pin the speaker is connected with
     */
    public Speaker(int pin) {
        this.pin = pin;
    }

    /**
     * Add a new tone to play.
     * 
     * @param tone The tone to play
     */
    public void addTone(Tone tone) {
        this.tones.add(tone);
    }

    /**
     * Add a set of tones to play.
     * 
     * @param tones The set of tones to play
     */
    public void addTones(ArrayList<Tone> tones) {
        this.tones.addAll(tones);
    }

    /**
     * Will remove all current tones.
     */
    public void removeAllTones() {
        this.tones.clear();
    }

    /**
     * The run method
     * 
     * This will play all available tones
     * and stop playing till new tones are added.
     */
    public void run() {
        while(true) {
            if(this.tones.size() > 0) {
                Tone tone = this.tones.get(0);
                int freq = tone.getFrequency();
                if(freq != 0) {
                    BoeBot.freqOut(this.pin, (int)((tone.getFrequency() - 27) / 86.4 * 100), tone.getDuration());
                } else {
                    BoeBot.wait(tone.getDuration());
                }
                this.tones.remove(0);
            }
            BoeBot.wait(1);
        }
    }
}
