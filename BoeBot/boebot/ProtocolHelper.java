package boebot;

import boebot.hardware.Bluetooth;
import boebot.hardware.Bluetooth.BluetoothListener;
import boebot.hardware.Bluetooth.Protocol;

/**
 * Write a description of class ProtocolHelper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProtocolHelper implements BluetoothListener
{
    private Bluetooth bluetooth;
    private ProtocolRouteListener routeListener;
    
    public ProtocolHelper() {
        this.bluetooth = new Bluetooth();
        this.bluetooth.setListener(this);
    }
    
    public void setRouteListener(ProtocolRouteListener listener) {
        this.routeListener = listener;
    }
    
    public void sendRoute(Route route) {
        this.bluetooth.sendProtocol(Protocol.ROUTE, "GET", route.toString());
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
        this.bluetooth.sendProtocol(Protocol.LOG, level, message);
    }
    
    @Override
    public void onMessage(String message) {
        try {
            Protocol protocol = Protocol.convertMessage(message);
            
            System.out.println(protocol);
            
            switch(protocol) {
                case COMMAND:
                    this.handleCommandMessages(protocol.getFunction(), protocol.getData());
                    break;
                case ROUTE:
                    this.handleRouteMessages(protocol.getFunction(), protocol.getData());
                    break;
            }
        } catch(Exception e) { // TODO: Make a ProtocolException sometime
            this.sendLog("ERROR", e.getMessage());
        }
    }
    
    private void handleCommandMessages(String function, String data) {
        if(function.equals("EXEC")) {
            
        }
    }
    
    private void handleRouteMessages(String function, String data) {
        switch(function) {
            case "SET":
                if(this.routeListener != null) {
                    String[] directions = data.replace("[", "").replace("]", "").replace("\n", "").split(", ");
                    
                    Route route = Route.fromStringArray(directions);
                    this.routeListener.onRoute(route);
                }
                break;
            case "GET":
                // Return ze route...
                break;
            case "PROGRESS":
                // Return ze progress...
                break;
        }
    }
    
    public interface ProtocolRouteListener {
        public void onRoute(Route route);
    }
}