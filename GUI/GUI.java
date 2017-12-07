
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
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
    private JPanel content;

    public GUI()
    {
        makeFrame();
    }

    private void makeFrame()
    {
        frame = new JFrame("GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        content = new JPanel(new GridLayout(2 , 2));
        content.add(new JLabel("Grid    "));
        
        
        
        makeMenuBar(frame);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() - 80;
        double height = screenSize.getHeight() - 80;
        frame.setSize((int)width, (int)height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        setContentPane(content);
        frame.setVisible(true);
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
