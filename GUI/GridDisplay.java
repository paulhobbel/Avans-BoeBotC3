import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Write a description of class GridDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GridDisplay extends JPanel
{
    private int x;
    private int xx;
    private int y;
    private int yy;
    
    public GridDisplay() {


        
    
    }
    
    
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        //g.drawLine(20, 0, this.getWidth(), this.getWidth());
        
        for(int i = 1; i < 9; i++){
            
            //g.drawLine(this.getWidth()/9 * i, 0, this.getWidth()/9 * i, this.getHeight());
            g.fillRect(this.getWidth()/9 * i, 0, 5, this.getHeight());
        
        }
        
        for(int i = 1; i < 11; i++){
            
            //g.drawLine(this.getWidth()/9 * i, 0, this.getWidth()/9 * i, this.getHeight());
            g.fillRect(0, this.getHeight()/11 * i, getWidth(), 5);
        
        }
    }
}