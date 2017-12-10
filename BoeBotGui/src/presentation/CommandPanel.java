package presentation;

import business.ProtocolHelper;
import datastorage.Protocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommandPanel extends JPanel{
    public CommandPanel() {
        this.setLayout(new GridBagLayout());

        this.initPanel();
    }

    private void initPanel() {
        JButton breakButton = new JButton("Break");
        JButton powerButton = new JButton("Power");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton rightButton = new JButton("Right");
        JButton leftButton = new JButton("Left");

        breakButton.addActionListener(event-> ProtocolHelper.sendCommand(21));
        powerButton.addActionListener(event -> ProtocolHelper.sendCommand(20));
        upButton.addActionListener(event -> ProtocolHelper.sendCommand(16));
        downButton.addActionListener(event -> ProtocolHelper.sendCommand(17));
        rightButton.addActionListener(event -> ProtocolHelper.sendCommand(18));
        leftButton.addActionListener(event -> ProtocolHelper.sendCommand(19));

        this.addButtonToGrid(breakButton,3, 3);
        this.addButtonToGrid(powerButton,1, 1);
        this.addButtonToGrid(upButton,1, 0);
        this.addButtonToGrid(downButton,1, 2);
        this.addButtonToGrid(rightButton,2, 1);
        this.addButtonToGrid(leftButton,0, 1);
    }

    private void addButtonToGrid(JButton button, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        this.add(button, c);
    }
}
