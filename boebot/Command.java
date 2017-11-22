package boebot;

/**
 * Enumeration class Commands - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Command
{
    FORWARDS(16),
    BACKWARDS(17),
    RIGHT(18),
    LEFT(19),
    
    BREAK(20),
    UNKNOWN(-1);
    
    private int code;
    private int id;
    
    Command(int code) {
        this.code = code;
        this.id = 1;
    }
    
    Command(int code, int id) {
        this.code = code;
        this.id = id;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public int getID() {
        return this.id;
    }
    
    public static Command forCodeAndID(int code, int id) {
        for(Command command : values()) {
            if(command.getCode() == code && command.getID() == id) {
                return command;
            }
        }
        
        return UNKNOWN;
    }
}
