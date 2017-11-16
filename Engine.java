import TI.*;
/**
 * Write a description of class Motor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Engine
{
    private static Servo servoLinks = new Servo(14);
    private static Servo servoRechts = new Servo(15);
        public static void emergencyBreak(){
        servoLinks.update(1500);
        servoRechts.update(1500);
    }
}
