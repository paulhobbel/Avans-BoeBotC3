package presentation;

import business.ProtocolHelper;
import datastorage.Bluetooth;
import datastorage.Protocol;
import datastorage.ProtocolException;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    JPanel content;

    private static TerminalFrame terminal = new TerminalFrame();
    private static CommandPanel command = new CommandPanel();
    private JProgressBar progressBar;
    private static final int MY_MINIMUM = 0;
    private static final int MY_MAXIMUM = 100;
    private GridPanel gridPanel;

    public MainFrame() {
        this.content = new JPanel(new BorderLayout());

        this.makeMenuBar();
        this.makeContent();
        this.makeProgresBar();

        this.setSize(400, 600);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(content);
        this.setVisible(true);
    }


    private void makeMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");
        JMenu toolsMenu = new JMenu("Tools");
        JMenu helpMenu = new JMenu("Help");

        JMenu optionsCOMSubMenu = new JMenu("COM settings");
        optionsMenu.add(optionsCOMSubMenu);

        JCheckBoxMenuItem optionsTermalCheckboxItem = new JCheckBoxMenuItem("Show Terminal");
        optionsMenu.add(optionsTermalCheckboxItem);
        optionsTermalCheckboxItem.addActionListener(e -> this.terminal.setVisible(true));

        JMenu musicPlayer = new JMenu("Music Player");

        ArrayList<String> tracks = new ArrayList<>();
        tracks.add("Darude Sandstorm");
        tracks.add("Merry Christmas");
        tracks.add("Mii Song");
        tracks.add("Smoke Weed");
        tracks.add("Thomas The Train");
        tracks.add("We Are Number One");

        for(String track : tracks) {
            JMenuItem trackItem = new JMenuItem(track);

            trackItem.addActionListener(e -> ProtocolHelper.playMusic(track));

            musicPlayer.add(trackItem);
        }

        JMenuItem remote = new JMenuItem("Remote");

        JFrame remoteFrame = new JFrame();
        remoteFrame.add(command);
        remoteFrame.setSize(400, 300 );
        remoteFrame.setResizable(false);
        remote.addActionListener(e -> remoteFrame.setVisible(true));

        toolsMenu.add(musicPlayer);
        toolsMenu.add(remote);

        JMenuItem helpAboutMenuItem = new JMenuItem("About the BoeBot...");
        helpAboutMenuItem.addActionListener(e -> makeHelpScreen());
        helpMenu.add(helpAboutMenuItem);

        String[] portNames = Bluetooth.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            final String portName = portNames[i];

            JMenuItem item = new JMenuItem(portName);

            item.addActionListener(event -> {
                this.initBluetooth(portName);
            });

            optionsCOMSubMenu.add(item);
        }

        menuBar.add(optionsMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
    }

    private void makeContent() {
        JPanel bottomBar = new JPanel(new FlowLayout());
        gridPanel = new GridPanel(11, 9);

        gridPanel.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));

        JButton resetButton = new JButton("reset");
        JButton undoButton = new JButton("undo");
        JButton sendButton = new JButton("send");

        resetButton.addActionListener(e -> gridPanel.resetRoute());
        undoButton.addActionListener(e -> System.out.println("Undo: " + e));
        sendButton.addActionListener(e -> ProtocolHelper.sendRoute(gridPanel.getRoute()));

        resetButton.setPreferredSize(new Dimension(100, 50));
        undoButton.setPreferredSize(new Dimension(100, 50));
        sendButton.setPreferredSize(new Dimension(100, 50));

        bottomBar.add(resetButton);
        bottomBar.add(undoButton);
        bottomBar.add(sendButton);

        this.content.add(gridPanel, BorderLayout.CENTER);
        this.content.add(bottomBar, BorderLayout.SOUTH);

        new Timer(1, e ->
        {
            //Bluetooth.update();
        }).start();
    }

    private void makeProgresBar(){
        progressBar = new JProgressBar();
        progressBar.setMinimum(MY_MINIMUM);
        progressBar.setMaximum(MY_MAXIMUM);
        progressBar.setStringPainted(true);
        this.content.add(progressBar, BorderLayout.NORTH);
    }

    public void updateBar(int newValue) {
        newValue = 100/gridPanel.getRoute().getSize() * newValue;
        progressBar.setValue(newValue);
    }

    private void initBluetooth(String portName){
        try {

            //ArrayList<Byte> buffer = new ArrayList<>();
            //StringBuilder builder = new StringBuilder();

            Bluetooth.connectToCOM(portName);
//            Bluetooth.setEventListener(new Bluetooth.BluetoothEventListener() {
//                @Override
//                public void onProtocol(Protocol protocol) {
//                    if (protocol != null) {
//                        switch (protocol) {
//                            case LOG:
//                                terminal.addLog(protocol.getFunction(), protocol.getData());
//                                break;
//                        }
//                    }
//                }
//            });
            StringBuilder message = new StringBuilder();
            Bluetooth.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.isRXCHAR() && event.getEventValue() > 0) {
                        byte buffer[] = Bluetooth.readBytes();
                        for (byte b : buffer) {
                            if ((b == '\r' || b == '\n') && message.length() > 0) {
                                String protocolMessage = message.toString();

                                try {
                                    Protocol protocol = Protocol.convertMessage(protocolMessage);

                                    switch (protocol) {
                                        case LOG:
                                            terminal.addLog(protocol.getFunction(), protocol.getData());
                                            break;
                                    }
                                } catch (ProtocolException e) {
                                    e.printStackTrace();
                                }

                                message.setLength(0);
                            } else {
                                message.append((char) b);
                            }
                        }
                    }
                }
            });
        } catch (SerialPortException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void makeHelpScreen() {
        JOptionPane.showMessageDialog(this, "BoeBot\nVersion 3.4");
    }
}
