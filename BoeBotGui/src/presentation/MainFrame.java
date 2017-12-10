package presentation;

import com.sun.org.apache.xpath.internal.SourceTree;
import datastorage.Bluetooth;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel contentLeft;
    private JPanel contentMiddle;
    private JPanel contentRight;

    public MainFrame() {


        JPanel content = new JPanel(new GridLayout(1 , 3));

        this.contentLeft = new JPanel(new BorderLayout());
        this.contentMiddle = new JPanel(new BorderLayout());
        this.contentRight = new JPanel(new BorderLayout());


        content.add(this.contentLeft);
        content.add(this.contentMiddle);
        content.add(this.contentRight);




        // borderContentRight.add(new JLabel("Test"), BorderLayout.CENTER);
        //  borderContentRight.add(stuff.createGUI();
        this.makeMenuBar();

        this.makeLeftContent();
        this.makeMiddleContent();
        this.makeRightContent();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth() - 80;
        int height = (int)screenSize.getHeight() - 80;
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
        JMenu helpMenu = new JMenu("Help");


        JMenu optionsCOMSubMenu = new JMenu("COM settings");
        optionsMenu.add(optionsCOMSubMenu);




        JMenuItem helpAboutMenuItem = new JMenuItem("About the BoeBot...");
        helpAboutMenuItem.addActionListener(e -> makeHelpScreen());
        helpMenu.add(helpAboutMenuItem);

        String[] portNames = Bluetooth.getPortNames();
        for(int i = 0; i < portNames.length; i++) {
            final String portName = portNames[i];

            JMenuItem item = new JMenuItem(portName);

            item.addActionListener(event -> {
                try {
                    Bluetooth.connectToCOM(portName);
                } catch (SerialPortException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            });

            optionsCOMSubMenu.add(item);
        }



//        for(int i =0; i <= 20; i++){
//            item = new JMenuItem("COM " + i);
//            setCom.add(item);
//        }

        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);

        // JMenuItem quitItem = new JMenuItem("Quit");
        // quitItem.addActionListener(e -> quit());
        // fileMenu.add(quitItem);
    }

    private void makeLeftContent() {
        JPanel bottomBar = new JPanel(new FlowLayout());
        GridPanel gridPanel = new GridPanel(11, 9);

        JButton resetButton = new JButton("reset");
        JButton undoButton = new JButton("undo");
        JButton sendButton = new JButton("send");

        resetButton.addActionListener(e -> System.out.println("Reset: " + e));
        undoButton.addActionListener(e -> System.out.println("Undo: " + e));
        sendButton.addActionListener(e -> System.out.println("Send: " + e));

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
    }

    private void makeRightContent() {

    }

    private void makeHelpScreen() {
        JOptionPane.showMessageDialog(this, "BoeBot\nVersion 1.0" );
    }
}
