package datastorage;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

import javax.swing.*;
import java.util.Arrays;

public class BluetoothReader implements SerialPortEventListener {
    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            //byte[] test = Bluetooth.readBytes(event.getEventValue());
            //System.out.println(Arrays.toString(test));
            //System.out.println(Bluetooth.readString(event.getEventValue()));
            Protocol protocol = Bluetooth.readProtocol(event.getEventValue());
            if(protocol != null) {
                JOptionPane.showMessageDialog(null,
                        "Got an verified protocol message:\n\tProtocol: " + protocol +
                                 "\n\tFunction: " + protocol.getFunction() +
                                 "\n\tData: " + protocol.getData());
            }
        }
    }
}
