package boebot.output;

public class Tone
{
    public static final int B5 = 987;
    public static final int As5 = 932;
    public static final int A5 = 880;
    public static final int Gs5 = 830;
    public static final int G5 = 783;
    public static final int Fs5 = 739;
    public static final int F5 = 698;
    public static final int E5 = 659;
    public static final int Ds5 = 622;
    public static final int D5 = 587;
    public static final int Cs5 = 554;
    public static final int C5 = 523;
    public static final int B4 = 493;
    public static final int As4 = 466;
    public static final int A4 = 440;
    public static final int Gs4 = 415;
    public static final int G4 = 391;
    public static final int Fs4 = 369;
    public static final int F4 = 349;
    public static final int E4 = 329;
    public static final int Ds4 = 311;
    public static final int D4 = 293;
    public static final int Cs4 = 277;
    public static final int C4 = 261;
    public static final int B3 = 246;
    public static final int As3 = 233;
    public static final int A3 = 220;
    public static final int Gs3 = 207;
    public static final int G3 = 195;
    public static final int Fs3 = 184;
    public static final int F3 = 174;
    public static final int Ds3 = 155;

    public static final int PAUSE = 0;
    public static final Tone LITTLE_WAIT = new Tone(0, 20);
    public static final Tone SONG_WAIT = new Tone(0, 1000);

    public static final int WHOLE = 1;
    public static final int THREE_QUARTERS = 34;
    public static final int HALF = 2;
    public static final int THREE_EIGHTS = 38;
    public static final int QUARTER = 4;
    public static final int THREE_SIXTEENTHS = 316;
    public static final int EIGHT = 8;
    public static final int SIXTEENTH = 16;

    private int freq;
    private int duration;

    public Tone(int freq)
    {
        this.freq = freq;
        this.duration = 1000;
    }

    /**
     * Tone Constructor
     * 
     * @param freq The frequency of this tone
     * @param duration The duration of this tone
     */
    public Tone(int freq, int duration)
    {
        this.freq = freq;
        this.duration = duration;
    }

    /**
     * Tone Constructor
     * 
     * @param freq The frequency of this tone
     * @param length The duration of this tone
     * @param bpm The pbm of this tone
     */
    public Tone(int freq, int length, int bpm)
    {
        this.freq = freq;
        this.duration = duration;
        switch(length) {
            case 1:
            this.duration = 60000 / bpm * 4 - 20;
            //BoeBot.wait(20);
            break;

            case 34:
            this.duration = 60000 / bpm * 3 - 20;
            break;

            case 2:
            this.duration = 60000 / bpm * 2 - 20;
            break;

            case 38:
            this.duration = 60000 / bpm / 2 * 3 - 20;
            break;

            case 4:
            this.duration = 60000 / bpm * 1 - 20;
            break;

            case 316:
            this.duration = 60000 / bpm / 4 * 3 - 20;
            break;

            case 8:
            this.duration = 60000 / bpm / 2 - 20;
            break;

            case 16:
            this.duration = 60000 / bpm / 4 - 20;
            break;
        }
    }

    /**
     * Gets the frequency of this tone.
     */
    public int getFrequency() {
        return this.freq;
    }

    /**
     * Gets the duration of this tone.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Gets the frequency of this tone.
     */
    public void setFrequency(int freq) {
        this.freq = freq;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
