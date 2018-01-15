package business;

import datastorage.Bluetooth;
import datastorage.Protocol;

public class ProtocolHelper {
    /**
     * Send log with level info to BoeBot.
     *
     * @param log The message to be send
     */
    public static void sendLog(String log) {
        sendLog("INFO", log);
    }

    /**
     * Send log to BoeBot.
     *
     * @param level   The level (info, warn, debug, Exception)
     * @param message The message to be send
     */
    public static void sendLog(String level, String message) {
        Bluetooth.sendProtocol(Protocol.LOG, level, message);
    }

    /**
     * Send route to BoeBot.
     *
     * @param route The route to be send
     */
    public static void sendRoute(Route route) {
        Bluetooth.sendProtocol(Protocol.ROUTE, "SET", route.toString());
    }

    /**
     * Send command to BoeBot.
     *
     * @param command The command to be send
     */
    public static void sendCommand(int command) {
        sendCommand(command + "");
    }

    /**
     * Send command to BoeBot.
     *
     * @param command The command to be send
     */
    public static void sendCommand(String command) {
        Bluetooth.sendProtocol(Protocol.COMMAND, "EXEC", command);
    }

    /**
     * Play music on BoeBot
     *
     * @param track The selected track will be played.
     */
    public static void playMusic(String track){
        Bluetooth.sendProtocol(Protocol.MUSIC, "PLAY", track);
    }

    public static void pauseMusic(){
        Bluetooth.sendProtocol(Protocol.MUSIC, "PAUSE", "");

    }
}
