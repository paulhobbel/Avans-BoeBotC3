package datastorage;

import java.util.regex.Pattern;

/**
 * Shared Protocol used for Bluetooth communication between the BoeBot and the GUI
 *
 * @author Nick Kerremans
 * @author Paul Hobbel
 * @version 10-12-2017 (Version 1.0)
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
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     * Method to set the data.
     */
    public void setData(String data) {
        this.data = data;
    }

    public String[] getFunctions() { return this.functions; }

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
     * @param message The message to be converted
     *               A message should be formatted like this
     *               PROTOCOL\FUNCTION\DATA
     * @throws ProtocolException
     */
    public static Protocol convertMessage(String message) throws ProtocolException {
        String[] splittedMessage = message.split(Pattern.quote("\\"));
        // 0 - Protocol
        // 1 - Function
        // 2 - Data

        if(splittedMessage.length != 3) {
            throw new ProtocolException("Unsupported message, expected 3 parts, got " + splittedMessage.length + "!");
        }

        Protocol protocol = Protocol.valueOf(splittedMessage[0]);
        if(protocol == null) {
            throw new ProtocolException("Protocol " + splittedMessage[0] + " not supported!");
        }

        if(!protocol.isFunctionSupported(splittedMessage[1])) {
            throw new ProtocolException("Function " + splittedMessage[1] + " not supported by " + protocol + "!");
        }

        protocol.setFunction(splittedMessage[1]);
        protocol.setData(splittedMessage[2]);

        return protocol;
    }

    /**
     * Convert the Protocol object to a readable string.
     *
     * @param function The function to be used
     * @param data The data to be send
     * @return a converted protocol object
     * @throws ProtocolException
     */
    public String toString(String function, String data) throws ProtocolException {
        if(!this.isFunctionSupported(function)) {
            throw new ProtocolException("Function " + function + " not supported by " + this + "!");
        }

        return this + "\\" + function + "\\" + data;
    }

    public String toSendString() {
        return this + "\\" + this.function + "\\" + this.data;
    }
}
