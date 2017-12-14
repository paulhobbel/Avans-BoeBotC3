import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;

import presentation.*;

/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class GUI extends JFrame 
{
    private JFrame frame;
    private JMenuBar menubar;
    private JMenu menu;
    private JMenu setCom;
    private JMenuItem item;
    private JButton rbutton;
    
    private GridDisplay grid = new GridDisplay(11, 9);
    private StatusDisplay status = new StatusDisplay();
    private MakeConnection make = new MakeConnection();
    private GridBagConstraints c = new GridBagConstraints();
    private TextClassStuff stuff = new TextClassStuff();
    
    private Container pane;

    public GUI()
    {
        makeFrame();
    }

    private void makeFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel content = new JPanel(new GridLayout(1 , 3));
        
        
        JPanel borderContentLeft = new JPanel(new BorderLayout());
        JPanel borderContentMiddle = new JPanel(new BorderLayout());
        JPanel borderContentRight = new JPanel(new BorderLayout());
        JPanel bottomBar = new JPanel(new FlowLayout());
        
        content.add(borderContentLeft);
        content.add(borderContentMiddle);
        content.add(borderContentRight);

        borderContentLeft.add(grid, BorderLayout.CENTER);        
        borderContentLeft.add(bottomBar, BorderLayout.SOUTH);

        borderContentMiddle.add(new JLabel("BoeBot state: IDLE"), BorderLayout.NORTH);
        borderContentMiddle.add(status, BorderLayout.NORTH);
        borderContentMiddle.add(commandController());

        // borderContentRight.add(new JLabel("Test"), BorderLayout.CENTER);
        //  borderContentRight.add(stuff.createGUI());

        bottomBar.add(new JButton("reset"));
        bottomBar.add(new JButton("undo"));
        bottomBar.add(new JButton("send"));

        makeMenuBar(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() - 80;
        double height = screenSize.getHeight() - 80;
        setSize((int)width, (int)height);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(content);
        setVisible(true);
    }

    private void makeMenuBar(JFrame frame)
    {    
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        menu = new JMenu("Options");
        menubar.add(menu);

        setCom = new JMenu("COM settings");
        //setCom.setMnemonic(KeyEvent.VK_B);
        //setCom.addActionListener(e -> );
        menu.add(setCom);

        menu = new JMenu("Help");   
        //setCom.addActionListener(e -> );
        menubar.add(menu);

        item = new JMenuItem("About the BoeBot...");
        item.addActionListener(e -> makeHelpScreen());
        menu.add(item);

        for(int i =0; i <= 20; i++){
            item = new JMenuItem("COM " + i);
            setCom.add(item);
        }

        // JMenuItem quitItem = new JMenuItem("Quit");
        // quitItem.addActionListener(e -> quit());
        // fileMenu.add(quitItem);
    }

    private Container commandController (){
        Container container = new Container();
        container.setLayout(new GridBagLayout());
        JButton breakButton = new JButton("Break");
        JButton powerButton = new JButton("Power");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton rightButton = new JButton("Right");
        JButton leftButton = new JButton("Left");

        breakButton.addActionListener((ActionEvent event)-> {
                make.sendString("21");
            });
        powerButton.addActionListener((ActionEvent event) -> {
                make.sendString("20");
            });
        upButton.addActionListener((ActionEvent event) -> {
                make.sendString("16");
            });
        downButton.addActionListener((ActionEvent event) -> {
                make.sendString("17");
            });
        rightButton.addActionListener((ActionEvent event) -> {
                make.sendString("18");
            });
        leftButton.addActionListener((ActionEvent event) -> {
                make.sendString("19");
            });
        createLayout(container, 3, 3, breakButton);
        createLayout(container, 1, 1, powerButton);
        createLayout(container, 1, 0, upButton);
        createLayout(container, 1, 2, downButton);
        createLayout(container, 2, 1, rightButton);
        createLayout(container, 0, 1, leftButton);       
        return container;
    }

    private void createLayout(Container container,int x, int y, JButton button){        
        c.gridx = x;
        c.gridy = y;
        container.add(button, c);
    }

    private void makeHelpScreen(){
        JOptionPane.showMessageDialog(frame, "BoeBot\n Version 1.0" );
    }

}
