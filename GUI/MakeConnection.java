import jssc.*;

public class MakeConnection{
    private SerialPort serialPort;

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

        }
        catch(SerialPortException ex){
            System.out.println(ex);
        }
    }

    public void sendInt(int i){
        if(!serialPort.isOpened())
            return;
        try{
            serialPort.writeInt(i);
            
            //byte buffer[] = serialPort.readBytes(i.length());

            //for(int count = 0; count < s.length(); count++)
            //    System.out.print(buffer[count] + "-");
        }catch(SerialPortException ex){
            System.out.println(ex);
        }
    }
}