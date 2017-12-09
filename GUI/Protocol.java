import java.util.regex.Pattern;

/**
 * Shared Protocol used for bluetooth communication between the BoeBot and the GUI
 *
 * @author Nick Kerremans
 * @author Paul Hobbel
 * @version 08-12-2017 (Version 1.0)
 */
public enum Protocol {
    LOG("INFO","WARN","DEBUG","ERROR"),

    COMMAND("EXEC"),

    ROUTE("SET","GET","PROGRESS");

    private String[] functions;
    private String function;
    private String data;

    /**
     * Protocol Constructor
     * 
     * @param functions The functions this protocol supports
     */
    Protocol(String... functions) {
        this.functions = functions;
    }

    /**
     * Check to see if the function is supported or not.
     * 
     * @param function The given function to check.
     */
    private boolean isFunctionSupported(String function) {
        for(int i = 0; i < this.functions.length; i++) {
            if(this.functions[i].equals(function)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to set the function.
     */
    private void setFunction(String function) {
        this.function = function;
    }

    /**
     * Method to set the data.
     */
    private void setData(String data) {
        this.data = data;
    }

    /**
     * Get the function.
     */
    public String getFunction(){
        return this.function;
    }

    /**
     * Get the data.
     */
    public String getData(){
        return this.data;
    }

    /**
     * Convert a protocol message into a protocol.
     * 
     * @param mesage The message to be converted
     *               A message should be formatted like this
     *               PROTOCOL\FUNCTION\DATA
     */
    public static Protocol convertMessage(String message) throws Exception {
        String[] splitsedMessage = message.split(Pattern.quote("\\"));
        // 0 - Protocol
        // 1 - Function
        // 2 - Data

        if(splitsedMessage.length != 3) {
            throw new Exception("ProtocolException: Unsupported message, expected 3 parts, got " + splitsedMessage.length + "!");
        }

        Protocol protocol = Protocol.valueOf(splitsedMessage[0]);
        if(protocol == null) {
            throw new Exception("ProtocolException: Protocol " + splitsedMessage[0] + " not supported!");
        }

        if(!protocol.isFunctionSupported(splitsedMessage[1])) {
            throw new Exception("ProtocolException: Function " + splitsedMessage[1] + " not supported by " + protocol + "!");
        }

        protocol.setFunction(splitsedMessage[1]);
        protocol.setData(splitsedMessage[2]);

        return protocol;
    }

    public String toString(String function, String data) throws Exception {
        if(!this.isFunctionSupported(function)) {
            throw new Exception("ProtocolException: Function " + function + " not supported by " + this + "!");
        }

        return this + "\\" + function + "\\" + data;
    }
}
