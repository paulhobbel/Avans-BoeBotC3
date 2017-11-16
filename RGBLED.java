import TI.*;
import java.awt.Color;

/**
 * Write a description of class RGBLED here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RGBLED
{
    private static PWM red = new PWM(10, 255);
    private static PWM green = new PWM(11, 255);
    private static PWM blue = new PWM(12, 255);
    
    public static void main(String args[]) {
        regenboog();
    }
        
        public static void regenboog(){
        float i = 0;
        setColors(Color.getHSBColor(i, 1.0f,  0.1f));
        while(true) {
            i = i + 0.01f;
            setColors(Color.getHSBColor(i, 1.0f,  0.1f));
            if(i >= 1.0f) {
                i = 0f;
            }
            BoeBot.wait(100);
        }
    }
    
    private static void setColors(Color color) {
        red.update(255-color.getRed());
        green.update(255-color.getGreen());
        blue.update(255-color.getBlue());
    }
}
