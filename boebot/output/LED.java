package boebot.output;

import TI.*;
import java.awt.Color;

/**
 * This class takes care of the LED's on the BoeBot board
 *
 * @author Daan van Kempen
 * @author Paul Hobbel
 * @version 0.2
 */
public class LED
{
    private int place;

    /**
     * Creates a new instance of LED.
     * 
     * @param place The place on the BoeBot board
     */
    public LED(int place) {
        if(place >= 0 && place <=5) {
            this.place = place;
        } else {
            throw new Error("Place out of bounds! Choose a value between 0 and 5!");
        }
    }

    /**
     * Turns on a LED on the BoeBot board by giving the RGB values.
     * 
     * @param red   The red value
     * @param green The green value
     * @param blue  The blue value
     * @since 0.1
     */
    public void turnOn(int red, int green, int blue) {
        this.turnOn(red, green, blue, 255);
    }
    
    /**
     * Turns on a LED on the BoeBot board by giving the RGBA values.
     * 
     * @param red   The red value
     * @param green The green value
     * @param blue  The blue value
     * @param alpha The alpha value
     * @since 0.2
     */
    public void turnOn(int red, int green, int blue, int alpha) {
        this.turnOn(new Color(red, green, blue, alpha));
    }

    /**
     * Turns on a LED on the BoeBot board by giving an awt Color and alpha value.
     * 
     * @param color An instance of awt Color
     * @param alpha The alpha value
     * @since 0.2
     */
    public void turnOn(Color color, int alpha) {
        Color convertedColor = this.convertRGBtoRGBA(color, alpha);
        this.turnOn(convertedColor);
    }
    
    /**
     * Turns on a LED on the BoeBot board by giving an awt Color.
     * 
     * @param color An instance of awt Color
     * @since 0.1
     */
    public void turnOn(Color color) {
        BoeBot.rgbSet(this.place, 255-color.getRed(), 255-color.getGreen(), 255-color.getBlue());
        BoeBot.rgbShow();
    }
    
    /**
     * Turns off a LED on the BoeBot board
     * @since 0.1
     */
    public void turnOff() {
        BoeBot.rgbSet(this.place, 0, 0, 0);
        BoeBot.rgbShow();
    }

    /**
     * Converts a regular Color into a Color with an alpha value
     * @since 0.2
     */
    private Color convertRGBtoRGBA(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    // public enum Color
    // {
        // RED(120, 0, 0),
        // BLUE(0, 0, 120),
        // YELLOW(120, 120, 0),
        // GREEN(0, 120, 0),
        // ORANGE(120, 20, 0),
        // WHITE(120, 120, 120),
        // ;
        // int r;
        // int g;
        // int b;

        // Color(int r, int g, int b) {
            // this.r = r;
            // this.g = g;
            // this.b = b;
        // }
        
        // public int getR() {
            // return this.r;
        // }
        
        // public int getG() {
            // return this.g;
        // }
        
        // public int getB() {
            // return this.b;
        // }
    // }
}
