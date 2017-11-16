import TI.*;
/**
 * Write a description of class FadingLED here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FadingLED
{
    private static int i = 255;
    private static PWM lampje = new PWM(5, i);
    private static PWM lampje2 = new PWM(1, i);
    private static boolean ronde= false;
        
    public static void main(String args[]) {
        lampje2.start();
        if(ronde) {
            i++;
        }
        else {
            i--;
        }
        
        lampje.update(i);
        lampje2.update(i);
        
        if(i == 0 || i == 255) {
            ronde = !ronde;        
        }
    }
}
