package boebot.output;

public class Tone
{
    public final static Tone A = new Tone(4,4);

    private int freq;
    private int duration;

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

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
