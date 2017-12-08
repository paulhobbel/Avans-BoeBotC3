import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;


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
    private GridDisplay grid = new GridDisplay();

    public GUI()
    {
        makeFrame();
    }

    private void makeFrame()
    {
        //frame = new JFrame("GUI");
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
       // borderContentLeft.add(Box.createHorizontalStrut(20), BorderLayout.CENTER); 
        borderContentLeft.add(bottomBar, BorderLayout.SOUTH);
        
        borderContentMiddle.add(new JLabel("BoeBot state: IDLE"), BorderLayout.NORTH);
        borderContentMiddle.add(new JButton("shit"), BorderLayout.CENTER);
        
        
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
    
    private void makeHelpScreen(){
        JOptionPane.showMessageDialog(frame, "BoeBot\n Version 1.0" );
    }
    
}
