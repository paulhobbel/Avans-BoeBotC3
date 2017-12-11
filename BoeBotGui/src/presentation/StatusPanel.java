package presentation;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JLabel statusLabel;

    public StatusPanel() {
        this.statusLabel = new JLabel("BoeBot state: " + BoeBotState.IDLE);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        this.add(this.statusLabel);
    }

    public void setState(BoeBotState state) {
        this.statusLabel.setText("BoeBot state: " + state);
    }

    public enum BoeBotState {
        IDLE,
        OVERRIDE,
        ROUTE
    }
}
