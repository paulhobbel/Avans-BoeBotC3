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

    private ArrayList<GridButton> buttons = new ArrayList<>();
    //private GridButton[][] newButtons;
    private ArrayList<GridLine> lines = new ArrayList<>();
    private ArrayList<GridButton> routeButtons = new ArrayList<>();

    private Route route = new Route();
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
        for(GridButton button : this.routeButtons) {
            button.setType(GridButton.GridButtonType.BASIC);
        }
        this.routeButtons.clear();
        this.lines.clear();
        this.route.resetRawDirections();

        this.repaint();
    }

    public void undoRoute() {
        if(this.routeButtons.size() > 0) {
            this.routeButtons.get(this.routeButtons.size() - 1).setType(GridButton.GridButtonType.BASIC);
            this.routeButtons.remove(this.routeButtons.size() - 1);
            this.route.removeLastRawDirection();
            if(this.routeButtons.size() > 1) {
                this.routeButtons.get(this.routeButtons.size() - 1).setType(GridButton.GridButtonType.END);
            }
        }

        if(this.lines.size() > 0) {
            this.lines.remove(this.lines.size() - 1);

        }

        this.repaint();
    }

    public Route getRoute() {
        return this.route;
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
        for(int i = 0; i < this.buttons.size(); i++) {
            GridButton button = this.buttons.get(i);

            if(button.collides(e.getPoint())) {
                if(this.routeButtons.size() == 0) {
                    button.setType(GridButton.GridButtonType.START);
                    this.route.addRawDirection(Route.RawDirection.NORTH);
                    this.routeButtons.add(button);
                } else {
                    int width = this.getWidth() / this.cols;
                    int height = this.getHeight() / this.rows;

                    GridButton lastButton = this.routeButtons.get(this.routeButtons.size() - 1);

                    // NORTH
                    if(((lastButton.getY() - button.getY()) == height) && ((lastButton.getX() - button.getX()) == 0)) {
                        //if height is positive then: last direction - this direction
                        this.addDirection(lastButton, button, Route.RawDirection.NORTH);
                    }

                    // EAST
                    if(lastButton.getX() - button.getX() == -width && lastButton.getY() - button.getY() == 0) {
                        //if width is negative then: last direction + this direction
                        this.addDirection(lastButton, button, Route.RawDirection.EAST);
                    }

                    // SOUTH
                    if(lastButton.getY() - button.getY() == -height && lastButton.getX() - button.getX() == 0) {
                        //if height is negative then: last direction + this direction
                        this.addDirection(lastButton, button, Route.RawDirection.SOUTH);
                    }

                    // WEST
                    if(lastButton.getX() - button.getX() == width && lastButton.getY() - button.getY() == 0) {
                        //if width is positive then: last direction - this direction
                        this.addDirection(lastButton, button, Route.RawDirection.WEST);
                    }
                }
                this.repaint();
            }
        }
    }

    private void addDirection(GridButton lastButton, GridButton currentButton, Route.RawDirection direction) {
        currentButton.setType(GridButton.GridButtonType.END);
        this.lines.add(new GridLine(lastButton.getPoint(), currentButton.getPoint()));
        if(this.routeButtons.size() > 1) {
            lastButton.setType(GridButton.GridButtonType.POINT);
        }
        this.route.addRawDirection(direction);
        this.routeButtons.add(currentButton);
    }
}
