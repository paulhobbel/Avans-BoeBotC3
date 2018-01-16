package boebot.output;

import boebot.output.Notification.SoundContext;
import boebot.output.Tone;

/**
 * Write a description of class Mii here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mii implements Sound {
    private int bpm = 114;
    private int plays;
    
    public Mii(int plays) {
        this.plays = plays;
    }

    public void play(SoundContext context) {
        for(int i = 0; i < this.plays; i++) {
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.HALF, this.bpm));
            
            
            context.addToneMusic(new Tone(Tone.Cs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT, this.bpm));
            
            context.addToneMusic(new Tone(Tone.E5, Tone.THREE_EIGHTS, this.bpm));
            context.addToneMusic(new Tone(Tone.Ds5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            
            
            context.addToneMusic(new Tone(Tone.D5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
            
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.THREE_EIGHTS, this.bpm));
            
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.THREE_EIGHTS, this.bpm));
            
            context.addToneMusic(new Tone(Tone.Ds4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.D4, Tone.QUARTER, this.bpm));
        }
        context.addToneMusic(new Tone(Tone.Cs4, Tone.QUARTER, this.bpm));
    }
}
