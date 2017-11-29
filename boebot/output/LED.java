package boebot.output;

import TI.*;
import java.awt.Color;

/**
 * Write a description of class LED here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LED
{
    private int place;

    public LED(int place) {
        this.place = place;
    }

    public void turnOn(int r, int g, int b) {
        BoeBot.rgbSet(this.place, r, g, b);
        BoeBot.rgbShow();
    }

    public void turnOn(Color color) {
        BoeBot.rgbSet(this.place, color.getR(), color.getG(), color.getB());
        BoeBot.rgbShow();
    }
    
    public void turnOff() {
        BoeBot.rgbSet(this.place, 0, 0, 0);
        BoeBot.rgbShow();
    }

    public enum Color
    {
        RED(120, 0, 0),
        BLUE(0, 0, 120),
        YELLOW(120, 120, 0),
        GREEN(0, 120, 0),
        ORANGE(120, 20, 0),
        WHITE(120, 120, 120),
        ;
        int r;
        int g;
        int b;

        Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
        
        public int getR() {
            return this.r;
        }
        
        public int getG() {
            return this.g;
        }
        
        public int getB() {
            return this.b;
        }
    }
}
