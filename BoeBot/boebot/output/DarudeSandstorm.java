package boebot.output;

import boebot.output.Notification.SoundContext;
import boebot.output.Tone;

/**
 * Write a description of class DarudeSandstorm here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DarudeSandstorm implements Sound{
    private int bpm = 136;
    private int plays;
    
    public DarudeSandstorm(int plays) {
        this.plays = plays;
    }

    public void play(SoundContext context) {
        for(int j = 0; j < this.plays; j++) {
            for(int i = 0; i < 4; i++)
                context.addToneMusic(new Tone(Tone.B4, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
            for(int i = 0; i < 6; i++)
                context.addToneMusic(new Tone(Tone.B4, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
            for(int i = 0; i < 6; i++)
                context.addToneMusic(new Tone(Tone.E5, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.E5, Tone.EIGHT, this.bpm));
            for(int i = 0; i < 6; i++)
                context.addToneMusic(new Tone(Tone.D5, Tone.SIXTEENTH, this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT, this.bpm));
            for(int i = 0; i < 2; i++)
                context.addToneMusic(new Tone(Tone.A4, Tone.SIXTEENTH, this.bpm));
            for(int z = 0; z < 2; z++) {
                for(int i = 0; i < 4; i++)
                    context.addToneMusic(new Tone(Tone.B4, Tone.SIXTEENTH, this.bpm));
                context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
                for(int i = 0; i < 6; i++)
                    context.addToneMusic(new Tone(Tone.B4, Tone.SIXTEENTH, this.bpm));
                context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
                for(int i = 0; i < 2; i++)
                    context.addToneMusic(new Tone(Tone.E5, Tone.SIXTEENTH, this.bpm));
            }
        }
    }
}
