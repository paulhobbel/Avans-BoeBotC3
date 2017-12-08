import javax.swing.*;
import javax.*;
import java.awt.*;
import java.awt.event.*;
import jssc.*;

public class SwingTest{
    private GridBagConstraints c = new GridBagConstraints();
    private Container pane;
    private MakeConnection make = new MakeConnection();
    public SwingTest(){
        this.pane = pane;
        make.connection();
    }
    private void addStuffToPain(Container pane){
        this.pane = pane;
        
        pane.setLayout(new GridBagLayout());
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
        createLayout(3, 3, breakButton);
        createLayout(1, 1, powerButton);
        createLayout(1, 0, upButton);
        createLayout(1, 2, downButton);
        createLayout(2, 1, rightButton);
        createLayout(0, 1, leftButton);       
    }
    
    private void createLog(){
        
    }
    private void createLayout(int x, int y, JButton button){        
        c.gridx = x;
        c.gridy = y;
        this.pane.add(button, c);
    }
    
    private void createGUI(){
        JFrame frame = new JFrame("SwingTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        TextClassStuff.createGUI();
        addStuffToPain(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    public void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                createGUI();
            }
        });
    }
}