package presentation;

import business.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GridPanel extends JPanel implements MouseListener {
    private int rows;
    private int cols;

    private ArrayList<GridButton> buttons = new ArrayList();
    private GridButton[][] newButtons;
    private ArrayList<GridLine> lines = new ArrayList();
    //private ArrayList<Route> routes = new ArrayList();
    private Route route = new Route();
    private boolean arrayMade =false;

    private Point lastPoint;

    public GridPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.newButtons = new GridButton[rows-1][cols-1];

        this.addMouseListener(this);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        for(int i = 1; i < this.cols; i++){
            g.fillRect(this.getWidth()/this.cols * i, 0, 5, this.getHeight());
        }

        for(int i = 1; i < this.rows; i++){
            g.fillRect(0, this.getHeight()/this.rows * i, this.getWidth(), 5);
        }

        if(!this.arrayMade)
            this.createButtons();

        // for(GridButton button: this.buttons){
        // button.draw(g);
        // }

        for(GridLine line: this.lines) {
            line.draw(g);
        }

        for(int y = 0; y < this.rows - 1; y++) {
            for(int x = 0; x < this.cols - 1; x++) {
                this.newButtons[y][x].draw(g);
            }
        }

    }

    @Override
    public void mouseExited(MouseEvent e){
    }

    @Override
    public void mousePressed(MouseEvent e){
    }

    @Override
    public void mouseReleased(MouseEvent e){
    }

    @Override
    public void mouseEntered(MouseEvent e){
    }

    private void clickButton(int x, int y, Point point) {
        if(x < 0 || y < 0 || x >= this.cols -1 || y >= this.rows -1) {
            System.out.println("Out of bounds i guessss");
            return;
        }

        GridButton button = this.newButtons[y][x];
        if(button.collides(point)) {
            this.lastPoint = new Point(x, y);

            //this.lastPoint = button.getPoint();
            button.setColor(Color.RED);

            if(this.lastPoint != null) {
                GridButton oldButton = this.newButtons[(int)this.lastPoint.getY()][(int)this.lastPoint.getX()];
                if(oldButton != null) {
                    Rectangle rect= new Rectangle();
                    rect.setFrameFromDiagonal(button.getPoint(), oldButton.getPoint());

                    this.lines.add(new GridLine(rect.x + 8, rect.y - 8, rect.width, rect.height));
                }
            }

            this.repaint();
        }
    }

    private void createButtons() {
        if (this.arrayMade)
            return;

        for (int y = 0; y < this.rows - 1; y++) {
            for (int x = 0; x < this.cols - 1; x++) {
                System.out.println("newButtons[" + y + "][" + x + "]");
                this.newButtons[y][x] = new GridButton((this.getWidth() / this.cols * (x + 1)) - 8, (this.getHeight() / this.rows * (y + 1)) - 8, 20, 20);
            }
        }

        // int x = 1;
        // int y = 1;
        // for(int i = 0; i < ((this.cols - 1) * (this.rows) + 1) ; i++){
        // if(x < this.cols){
        // GridButton button = new GridButton((this.getWidth()/this.cols * x)-8 , (this.getHeight()/this.rows * y) -8, 20, 20);
        // buttons.add(button);
        // x++;
        // }
        // else{
        // x = 1;
        // y++;
        // }

        // }
        this.arrayMade = true;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(this.lastPoint == null) {
            for(int y = 0; y < this.rows - 1; y++) {
                for(int x = 0; x < this.cols - 1; x++) {
                    this.clickButton(x, y, e.getPoint());
                }
            }
        } else {
            int y = (int)this.lastPoint.getY();
            int x = (int)this.lastPoint.getX();

            for(int yy = y - 1; yy <= y + 1; yy++) {
                for(int xx = x - 1; xx <= x + 1; xx++) {
                    System.out.println(xx);
                    System.out.println(yy);
                    this.clickButton(xx, yy, e.getPoint());
                }
            }
        }

        // for(GridButton button: this.newButtons){
        // if(button.collides(e.getPoint())){
        // System.out.println("pressed, goed gedaaan faggot");
        // this.route.addDirection(button, 0);
        // button.setColor(Color.RED);
        // this.repaint();
        // }
        // }
    }
}
