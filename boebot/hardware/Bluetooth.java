package boebot.hardware;

import jssc.SerialPort;
import jssc.SerialPortException;

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
public class Bluetooth
{
    public void signal(){
        SerialPort serialPort = new SerialPort("COM4");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.writeString("HelloWorld");
            
            byte[] buffer =serialPort.readBytes(10);
            for (int count = 0; count < 10; count++) 
                System.out.print(buffer[count] + "-");
            
            serialPort.closePort();           
        }
        
        catch (SerialPortException  ex) {
            System.out.println(ex);
        }
    } 
}
