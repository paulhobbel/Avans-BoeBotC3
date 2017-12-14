import jssc.*;

/**
 * Write a description of class Bluetooth here.
 *
 * @author Nick Kerremans
 * @author Paul Hobbel
 * @version 08-12-2017 (verion 1.0)
 */
public class Bluetooth
{
    private SerialPort serialPort;

    public Bluetooth(){
        SerialPort serialPort = new SerialPort("COM11");
    }

    public void connection(){      
        if(serialPort.isOpened())
            return;
        try{
            serialPort.openPort();
            serialPort.setParams(115200, 8, 1, 0);

        }
        catch(SerialPortException ex){
            System.out.println(ex);
        }
    }

    /**
     * Send log with level info to GUI.
     * 
     * @param log The message to be send
     */
    public void sendLog(String log) {
        this.sendLog("INFO", log);
    }

    /**
     * Send log to GUI.
     * 
     * @param level The level (info, warn, debug, Exception)
     * @param log The message to be send
     */
    public void sendLog(String level, String message) {
        this.sendProtocol(Protocol.LOG, level, message);
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
            this.serialPort.writeString(protocol.toString(function, data));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
