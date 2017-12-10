package datastorage;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import javax.swing.*;

public class Bluetooth {
    private static SerialPort serialPort;

    /**
     * Connect with the given COM port.
     *
     * @param COM The COM Port to connect to
     * @throws SerialPortException
     */
    public static void connectToCOM(String COM) throws SerialPortException {
        if (serialPort != null && serialPort.isOpened()) {
            if (serialPort.getPortName() != COM) {
                serialPort.closePort();
            } else {
                return;
            }
        }

        serialPort = new SerialPort(COM);
        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    }

    /**
     * Get all available COM ports
     * @return all available COM ports
     */
    public static String[] getPortNames() {
        return SerialPortList.getPortNames();
    }

    /**
     * Send a protocol message over Bluetooth.
     *
     * @param protocol The protocol
     * @param function The function of the protocol
     * @param data     The data to send with the protocol
     */
    public static void sendProtocol(Protocol protocol, String function, String data) {
        try {
            serialPort.writeString(protocol.toString(function, data));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Please select a COM Port first!");
        } catch (ProtocolException|SerialPortException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
