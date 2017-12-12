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
    //private GridButton[][] newButtons;
    private ArrayList<GridLine> lines = new ArrayList();
    private ArrayList<GridButton> route = new ArrayList();
    private ArrayList<Route.RawDirection> directions = new ArrayList<>();
    //private Route route = new Route();
    private boolean arrayMade = false;

    //private Point lastPoint;

    public GridPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.addMouseListener(this);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        for(int i = 1; i < this.cols; i++){
            g.fillRect(this.getWidth()/this.cols * i, 0, 6, this.getHeight());

        }

        for(int i = 1; i < this.rows; i++){
            g.fillRect(0, this.getHeight()/this.rows * i, this.getWidth(), 6);

        }

        for(GridLine line: this.lines) {
            line.draw(g);
        }

        if(!this.arrayMade)
            this.createButtons();

        for(GridButton button: this.buttons){
            button.draw(g);
        }



    }

    public void resetRoute() {
        for(GridButton button : this.route) {
            button.setType(GridButton.GridButtonType.BASIC);
        }
        this.route.clear();
        this.lines.clear();

        this.repaint();
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

//    private void clickButton(int x, int y, Point point) {
//        if(x < 0 || y < 0 || x >= this.cols -1 || y >= this.rows -1) {
//            System.out.println("Out of bounds i guessss");
//            return;
//        }
//
//        for(int i = 0; i < this.buttons.size(); i++) {
//            GridButton button = this.buttons.get(i);
//
//            if(button.collides(point)) {
//                // Check if is first point
//                //if(this.route.size() == 0) {
//                    button.setType(GridButton.GridButtonType.START);
//                //}
//
//                this.repaint();
//            }
//        }
//
////        GridButton button = this.newButtons[y][x];
////        if(button.collides(point)) {
////            this.lastPoint = new Point(x, y);
////
////            //this.lastPoint = button.getPoint();
////            button.setColor(Color.RED);
////
////            if(this.lastPoint != null) {
////                GridButton oldButton = this.newButtons[(int)this.lastPoint.getY()][(int)this.lastPoint.getX()];
////                if(oldButton != null) {
////                    Rectangle rect= new Rectangle();
////                    rect.setFrameFromDiagonal(button.getPoint(), oldButton.getPoint());
////
////                    this.lines.add(new GridLine(rect.x + 8, rect.y - 8, rect.width, rect.height));
////                }
////            }
////
////            this.repaint();
////        }
//    }

    private void createButtons() {
        if (this.arrayMade)
            return;

        for (int y = 0; y < this.rows - 1; y++) {
            for (int x = 0; x < this.cols - 1; x++) {
                this.buttons.add(new GridButton((this.getWidth() / this.cols * (x + 1)) - 13, (this.getHeight() / this.rows * (y + 1)) - 13, 30, 30));
            }
        }

        this.arrayMade = true;
    }

    @Override
    public void mouseClicked(MouseEvent e){
//        if(this.lastPoint == null) {
//            for(int y = 0; y < this.rows - 1; y++) {
//                for(int x = 0; x < this.cols - 1; x++) {
//                    this.clickButton(x, y, e.getPoint());
//                }
//            }
//        } else {
//            int y = (int)this.lastPoint.getY();
//            int x = (int)this.lastPoint.getX();
//
//            for(int yy = y - 1; yy <= y + 1; yy++) {
//                for(int xx = x - 1; xx <= x + 1; xx++) {
//                    System.out.println(xx);
//                    System.out.println(yy);
//                    this.clickButton(xx, yy, e.getPoint());
//                }
//            }
//        }

        for(int i = 0; i < this.buttons.size(); i++) {
            GridButton button = this.buttons.get(i);

            if(button.collides(e.getPoint())) {
                if(this.route.size() == 0) {
                    button.setType(GridButton.GridButtonType.START);
                    this.directions.add(Route.RawDirection.NORTH);
                    this.route.add(button);
                } else {
                    int width = this.getWidth() / this.cols;
                    int height = this.getHeight() / this.rows;

                    GridButton lastButton = this.route.get(this.route.size() - 1);

                    //System.out.println(Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)));

                    //System.out.println(Math.sqrt(Math.pow(button.getX() - lastButton.getX(), 2) + Math.pow(button.getY() - lastButton.getY(), 2)));

                    // NORTH
                    if(((lastButton.getY() - button.getY()) == height) && ((lastButton.getX() - button.getX()) == 0)) {
                        //if height is positive then: last direction - this direction
                        button.setType(GridButton.GridButtonType.END);
                        this.lines.add(new GridLine(lastButton.getPoint(), button.getPoint()));
                        if(this.route.size() > 1) {
                            lastButton.setType(GridButton.GridButtonType.POINT);
                        }
                        this.directions.add(Route.RawDirection.NORTH);
                        this.route.add(button);
                    }

                    // EAST
                    if(lastButton.getX() - button.getX() == -width && lastButton.getY() - button.getY() == 0) {
                        //if width is negative then: last direction + this direction
                        button.setType(GridButton.GridButtonType.END);
                        this.lines.add(new GridLine(lastButton.getPoint(), button.getPoint()));
                        if(this.route.size() > 1) {
                            lastButton.setType(GridButton.GridButtonType.POINT);
                        }
                        this.directions.add(Route.RawDirection.EAST);
                        this.route.add(button);
                    }

                    // SOUTH
                    if(lastButton.getY() - button.getY() == -height && lastButton.getX() - button.getX() == 0) {
                        //if height is negative then: last direction + this direction
                        button.setType(GridButton.GridButtonType.END);
                        this.lines.add(new GridLine(lastButton.getPoint(), button.getPoint()));
                        if(this.route.size() > 1) {
                            lastButton.setType(GridButton.GridButtonType.POINT);
                        }
                        this.directions.add(Route.RawDirection.SOUTH);
                        this.route.add(button);
                    }

                    // WEST
                    if(lastButton.getX() - button.getX() == width && lastButton.getY() - button.getY() == 0) {
                        //if width is positive then: last direction - this direction
                        button.setType(GridButton.GridButtonType.END);
                        this.lines.add(new GridLine(lastButton.getPoint(), button.getPoint()));
                        if(this.route.size() > 1) {
                            lastButton.setType(GridButton.GridButtonType.POINT);
                        }
                        this.directions.add(Route.RawDirection.WEST);
                        this.route.add(button);
                    }
                }
                this.repaint();
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
