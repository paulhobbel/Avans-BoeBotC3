package boebot.hardware;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;

import TI.*;
import boebot.Updatable;
import boebot.Command;

/**
 * A hardware class to interact with the Bluetooth module.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 07-12-2017
 */
public class Bluetooth extends Updatable
{
    private SerialConnection conn;
    private BluetoothListener listener;

    private ArrayList<Byte> buffer = new ArrayList<>();

    /**
     * Constructor of Bluetooth.
     */
    public Bluetooth(){
        this.conn = new SerialConnection(115200);
    }

    /**
     * Set a Bluetooth listener.
     * 
     * @param listener The listener
     */
    public void setListener(BluetoothListener listener) {
        this.listener = listener;
    }

    /**
     * Send a protocol message over Bluetooth.
     * 
     * @param protocol The protocol
     * @param function The function of the protocol
     * @param data The data to send with the protocol
     */
    public void sendProtocol (Protocol protocol, String function, String data) {
        try {
            this.sendString(protocol.toString(function, data));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method converts the Bluetooth buffer to a string.
     */
    public void update() {
        if(this.conn.available() > 0){
            System.out.println("[DEBUG]: Got byte");
            this.buffer.add((byte)this.conn.readByte());
        } else if(this.buffer.size() > 0) {
            // Convert buffer to ascii
            System.out.println("[DEBUG]: Converting buffer to ascii");
            byte[] bufferArray = new byte[this.buffer.size()];
            for(int i = 0; i < this.buffer.size(); i++) {
                System.out.println("[DEUBG]: buffer[" + i + "] = " + this.buffer.get(i));
                bufferArray[i] = this.buffer.get(i);
            }

            try {
                String protocolMessage = new String(bufferArray, "UTF-8");

                System.out.println("[DEBUG]: Got protocol message: " + protocolMessage);
                if(this.listener != null) {
                    //this.listener.onProtocolMessage(Protocol.convertMessage(protocolMessage));
                    this.listener.onMessage(protocolMessage);
                }
            } catch(UnsupportedEncodingException ex) {
                System.out.println(ex);
            }

            this.buffer.clear();
        }
    }

    /**
     * Send a string over Bluetooth.
     * 
     * @param string The string to send.
     */
    private void sendString (String string) {
        char[] charArray = new String(string + "\n").toCharArray();
        for(int i = 0; i < charArray.length; i++){
            this.conn.writeByte(charArray[i]); 
        }
    }

    public interface BluetoothListener {
        public void onMessage(String message);
    }
    
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
}
