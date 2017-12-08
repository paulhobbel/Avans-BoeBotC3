package boebot;

import boebot.hardware.Engine;

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
    
    public static final Engine ENGINE_LEFT = new Engine(SERVO_LEFT_PIN, true);
    public static final Engine ENGINE_RIGHT = new Engine(SERVO_RIGHT_PIN, false);

    //----------------------------------------------------\\

    
    /**
     * The pin of the remote IR.
     */
    public static final int REMOTE_PIN = 0;
    
    //----------------------------------------------------\\
    
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
    
    //----------------------------------------------------\\
    
    /**
     * The analog pin of the right light sensor
     */
    public static final int LIGHT_RIGHT_PIN = 0;
    
    /**
     * The analog pin of the middle light sensor
     */
    public static final int LIGHT_MIDDLE_PIN = 1;
    
    /**
     * The analog pin of the left light sensor
     */
    public static final int LIGHT_LEFT_PIN = 2;
    
    //----------------------------------------------------\\
    
    /**
     * Linefollower constant: How much it corrects right away for an error
     */
    public static final double KP = 0.075;
    
    /**
     * Linefollower constant: Prevents overreaction over small errors
     */
    public static final double KI = 0.002;
    
    /**
     * Linefollower constant: How much it will react when it tries to predict the next error 
     */
    public static final double KD = 0.02;
    
    /**
     * Linefollower constant: The base speed moving in a straight line
     */
    public static final int TP = 70;
    
    /**
     * Linefollower constant: Dampning the integral
     */
    public static final double DAMP = 0.8;
    
    /**
     * Linefollower constant: Acceleration of the Engines. 0 is none
     */
    public static final double ACCELERATION = 0;
    
    /**
     * Linefollower constant: Accuracy of the linedetection
     */
    public static final double ACCURACY = 400;
    
    /**
     * Light value of a white surface
     */
    public static final int WHITE = 3900;
    
    /**
     * Light value of a black surface
     */
    public static final int BLACK = 2400;
}
