package presentation;

import com.sun.deploy.util.ArrayUtil;
import datastorage.Protocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class TerminalFrame extends JFrame {
    private JPanel panel;

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField textField;

    private JComboBox<Protocol> protocolComboBox;
    private JMenu protocolMenu;
    private Protocol currentProtocol;

    public TerminalFrame(){
        super("Terminal");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());

        this.textArea = new JTextArea(10, 40);
        this.textArea.setEditable(false);
        this.scrollPane = new JScrollPane(textArea);

        this.textField = new JTextField(40);
        //this.textField.addActionListener(e -> this.addLog(""));

        this.protocolComboBox = new JComboBox<>(Protocol.values());
        this.protocolMenu = new JMenu();

        for(Protocol protocol : Protocol.values()) {
            JMenuItem item = new JMenuItem(protocol.toString());
            this.protocolMenu.add(item);
        }

        this.createFrame();
        this.add(this.panel);
        this.pack();
    }

    public void createFrame(){
        this.panel.add(this.scrollPane, BorderLayout.CENTER);
        this.panel.add(this.textField, BorderLayout.SOUTH);
        this.panel.add(this.protocolComboBox, BorderLayout.SOUTH);
//        this.panel.add(this.protocolMenu, BorderLayout.SOUTH);
    }
    public void addLog(String level, String text) {
        LocalDateTime date = LocalDateTime.now();

        textArea.append(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ["+level+"]: " + text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
