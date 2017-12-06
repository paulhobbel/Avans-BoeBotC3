package boebot;

/**
 * 
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 06-12-2017
 */
public class Constants
{
    /**
     * The pin of the left servo
     */
    public static final int SERVO_LEFT_PIN = 14;
    
    /**
     * The pin of the right servo
     */
    public static final int SERVO_RIGHT_PIN = 15;
    
    
    /**
     * The pin of the remote IR.
     */
    public static final int REMOTE_PIN = 0;
    
    
    /**
     * The pin of the ultrasone trigger
     */
    public static final int ULTRASONE_TRIGGER_PIN = 3;
    
    /**
     * The pin of the ultrasone echo
     */
    public static final int ULTRASONE_ECHO_PIN = 4;
    
    /**
     * The distance which is needed to switch to CollisionState
     */
    public static final int COLLISION_DISTANCE = 15;
}
