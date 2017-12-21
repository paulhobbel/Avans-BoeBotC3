package boebot.output;

import boebot.output.Notification.SoundContext;
import boebot.output.Tone;

/**
 * Write a description of class ThomasTheTrain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ThomasTheTrain implements Sound {
    private int bpm = 100;
    private int plays;
    
    public ThomasTheTrain(int plays) {
        this.plays = plays;
    }

    public void play(SoundContext context) {
        context.addToneMusic(new Tone(Tone.Gs3, Tone.EIGHT,this.bpm));
        context.addToneMusic(new Tone(Tone.Ds4, Tone.SIXTEENTH,this.bpm));
        context.addToneMusic(new Tone(Tone.Ds4, Tone.SIXTEENTH,this.bpm));
        context.addToneMusic(new Tone(Tone.Ds3, Tone.EIGHT,this.bpm));
        context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
        context.addToneMusic(new Tone(Tone.Gs3, Tone.EIGHT,this.bpm));
        context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
        context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
        context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT,this.bpm));

        for(int i = 0; i < this.plays; i++)
        {
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.B4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.C5, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.D5, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.E5, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.HALF,this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.HALF,this.bpm));

            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.F4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.QUARTER,this.bpm));

            context.addToneMusic(new Tone(Tone.Gs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.F4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.F4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.A4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT,this.bpm));

            context.addToneMusic(new Tone(Tone.Fs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT,this.bpm));

            context.addToneMusic(new Tone(Tone.Fs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT,this.bpm));

            context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.F4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.Fs4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.As4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.F4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.G4, Tone.QUARTER,this.bpm));
            context.addToneMusic(new Tone(Tone.Gs4, Tone.EIGHT,this.bpm));

            context.addToneMusic(new Tone(Tone.Ds4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Ds4, Tone.SIXTEENTH,this.bpm));
            context.addToneMusic(new Tone(Tone.Ds3, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.Gs3, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.Ds4, Tone.EIGHT,this.bpm));
            context.addToneMusic(new Tone(Tone.PAUSE, Tone.EIGHT,this.bpm));
        }
    }
}
