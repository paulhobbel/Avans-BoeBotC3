package boebot.hardware;

import TI.*;
import boebot.Updatable;
import boebot.Command;

/**
 * Write a description of class Bluetooth here.
 *
 * @author Paul Hobbel
 * @author Thomas Mandemaker
 * @author Daan van Kempen
 * @author Tim de Booij
 * @author Nick Kerremans
 * @author Boudewijn Groeneboer
 * @version 06-12-2017
 */
public class Bluetooth extends Updatable
{
    private SerialConnection conn;
    private BluetoothListener listener;
    
    public Bluetooth(){
        this.conn = new SerialConnection(115200);
    }
    
    public void setListener(BluetoothListener listener) {
        this.listener = listener;
    }
    
    public void update() {
        if(this.conn.available() > 0){
            int data =conn.readByte();
            this.conn.writeByte(data);
            
            if(this.listener != null) {
                Command command = Command.forCodeAndID(data, 1);
                this.listener.onCommand(command);
            }
            
            System.out.println("data: " + data);
        }
    }
    
    public interface BluetoothListener {
        public void onCommand(Command command);
    }

    // public void signal(){
    // SerialPort serialPort = new SerialPort("COM4");
    // try {
    // serialPort.openPort();
    // serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    // serialPort.writeString("HelloWorld");

    // byte[] buffer =serialPort.readBytes(10);
    // for (int count = 0; count < 10; count++) 
    // System.out.print(buffer[count] + "-");

    // serialPort.closePort();           
    // }

    // catch (SerialPortException  ex) {
    // System.out.println(ex);
    // }
    // } 
}
