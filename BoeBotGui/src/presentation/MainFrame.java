package presentation;

import business.ProtocolHelper;
import com.sun.org.apache.xpath.internal.SourceTree;
import datastorage.Bluetooth;
import datastorage.Protocol;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel contentLeft;
    private JPanel contentMiddle;

    private static TerminalFrame terminal = new TerminalFrame();

    public MainFrame() {

        JPanel content = new JPanel(new GridLayout(1 , 2));

        this.contentLeft = new JPanel(new BorderLayout());
        this.contentMiddle = new JPanel(new BorderLayout());
     //   this.contentRight = new JPanel(new BorderLayout());


        content.add(this.contentLeft);
        content.add(this.contentMiddle);
       // content.add(this.contentRight);


        // borderContentRight.add(new JLabel("Test"), BorderLayout.CENTER);
        //  borderContentRight.add(stuff.createGUI();
        this.makeMenuBar();
        this.makeLeftContent();
        this.makeMiddleContent();
        //this.makeRightContent();


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth() - 900;
        int height = (int)screenSize.getHeight() - 300;
        this.setSize(width, height);

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

        toolsMenu.add(musicPlayer);

        JMenuItem helpAboutMenuItem = new JMenuItem("About the BoeBot...");
        helpAboutMenuItem.addActionListener(e -> makeHelpScreen());
        helpMenu.add(helpAboutMenuItem);

        String[] portNames = Bluetooth.getPortNames();
        for(int i = 0; i < portNames.length; i++) {
            final String portName = portNames[i];

            JMenuItem item = new JMenuItem(portName);

            item.addActionListener(event -> {
                this.initBluetooth(portName);
            });

            optionsCOMSubMenu.add(item);
        }



//        for(int i =0; i <= 20; i++){
//            item = new JMenuItem("COM " + i);
//            setCom.add(item);
//        }

        menuBar.add(optionsMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);

        // JMenuItem quitItem = new JMenuItem("Quit");
        // quitItem.addActionListener(e -> quit());
        // fileMenu.add(quitItem);
    }

    private void makeLeftContent() {
        JPanel bottomBar = new JPanel(new FlowLayout());
        GridPanel gridPanel = new GridPanel(11, 9);

        //this.contentLeft.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));
       // bottomBar.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));
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

        this.contentLeft.add(gridPanel, BorderLayout.CENTER);
        this.contentLeft.add(bottomBar, BorderLayout.SOUTH);
    }

    private void makeMiddleContent() {
        StatusPanel statusPanel = new StatusPanel();
        CommandPanel commandPanel = new CommandPanel();

            //this.contentMiddle.add(new JLabel("BoeBot state: IDLE"), BorderLayout.NORTH);
        this.contentMiddle.add(statusPanel, BorderLayout.NORTH);
        this.contentMiddle.add(commandPanel);
       // this.contentMiddle.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));
       // statusPanel.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.black));

    }

    private void initBluetooth(String portName){
        try {
            Bluetooth.connectToCOM(portName);
            Bluetooth.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if(event.isRXCHAR() && event.getEventValue() > 0) {
                        Protocol protocol = Bluetooth.readProtocol(event.getEventValue());
                        if (protocol != null) {
                            switch (protocol) {
                                case LOG:
                                    terminal.addLog(protocol.getFunction(), protocol.getData());
                                    break;
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
        JOptionPane.showMessageDialog(this, "BoeBot\nVersion 3.0" );
    }
}