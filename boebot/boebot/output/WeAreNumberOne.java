package boebot.output;

import boebot.output.Notification.SoundContext;
import boebot.output.Tone;

/**
 * Write a description of class Mii here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WeAreNumberOne implements Sound {
    private int bpm = 136;
    private int plays;
    
    public WeAreNumberOne(int plays) {
        this.plays = plays;
    }

    public void play(SoundContext context) {
        for(int i = 0; i < this.plays; i++)
        {
            if(i != 0)
            {
                context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT, this.bpm));
                context.addToneMusic(new Tone(Tone.C5, Tone.QUARTER, this.bpm));
            }
            context.addToneMusic(new Tone(Tone.F4, Tone.THREE_EIGHTS, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.F4, Tone.THREE_EIGHTS, this.bpm));

            
            context.addToneMusic(new Tone(Tone.F4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.Ds5, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.Cs5, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.EIGHT, this.bpm));
        }
    }
}
