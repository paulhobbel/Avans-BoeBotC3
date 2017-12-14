import jssc.*;
import jssc.SerialPort.*;
import java.io.*;

public class MakeConnection{
    private SerialPort serialPort;
    String initial = null;
    public MakeConnection()
    {
        serialPort = new SerialPort("COM5");
    }

    public void connection(){      
        if(serialPort.isOpened())
            return;
        try{
            serialPort.openPort();
            serialPort.setParams(115200, 8, 1, 0);
            serialPort.addEventListener(new SerialPortEventListener(){
                    public void serialEvent(SerialPortEvent event){
                        TextClassStuff.addLog(test());
                    }
                });
        }
        catch(SerialPortException ex){
            System.out.println(ex);
        }
    }

    public String test(){
        try{
            byte[] buffer = serialPort.readBytes();
            initial = serialPort.readString();
            if(!(initial == null))
                for(int count = 0; count < initial.length(); count++)
                    System.out.print(buffer[count]);           
        }catch(SerialPortException ex){
            System.out.println(ex);
        }
        return initial;
    }

    public void sendString(String i){
        if(!serialPort.isOpened())
            return;
        try{
            serialPort.writeString(i);
            //byte buffer[] = serialPort.readBytes(i.length());

            //for(int count = 0; count < s.length(); count++)
            //    System.out.print(buffer[count] + "-");
        }catch(SerialPortException ex){
            System.out.println(ex);
        }
    }
}