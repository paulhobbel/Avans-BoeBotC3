package boebot.output;

import boebot.output.Notification.SoundContext;
import boebot.output.Tone;

/**
 * Write a description of class MerryChristmas here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MerryChristmas implements Sound{
    private int bpm = 138;

    public void play(SoundContext context) {
        for(int i = 0; i < 2; i++) {
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
            
            context.addToneMusic(new Tone(Tone.A4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.QUARTER, this.bpm));
            
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER, this.bpm));
            
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.E5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.E5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.F5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT, this.bpm));
            
            context.addToneMusic(new Tone(Tone.C5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.QUARTER, this.bpm));
            
            context.addToneMusic(new Tone(Tone.D5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.HALF, this.bpm));
        }
    }
}
