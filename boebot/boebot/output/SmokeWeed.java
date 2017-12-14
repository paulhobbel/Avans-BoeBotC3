package boebot.output;

import boebot.output.Notification.SoundContext;
import boebot.output.Tone;

/**
 * Write a description of class TestSoundMusic here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmokeWeed implements Sound{
    private int bpm = 100;

    public void play(SoundContext context) {
        for(int i = 0; i < 2; i++) {
            context.addToneMusic(new Tone(Tone.E4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.QUARTER, this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.E4, Tone.EIGHT, this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT, this.bpm));
        }
        context.addToneMusic(new Tone(Tone.E4, Tone.QUARTER, this.bpm));
    }
}
