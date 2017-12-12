package presentation;

import com.sun.deploy.util.ArrayUtil;
import datastorage.Bluetooth;
import datastorage.Protocol;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TerminalFrame extends JFrame {
    private JPanel panel;
    private JPanel bottomContent;

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField textField;

    private JComboBox<Protocol> protocolComboBox;
    private JComboBox<String> protocolFunctionComboBox;

    public TerminalFrame(){
        super("Terminal");
        this.panel = new JPanel(new BorderLayout());
        this.bottomContent = new JPanel(new FlowLayout());

        this.textArea = new JTextArea(10, 40);
        this.textArea.setEditable(false);
        this.scrollPane = new JScrollPane(textArea);
        this.textField = new JTextField(40);
        this.textField.addActionListener(e -> this.sendProtocol());

        this.protocolComboBox = new JComboBox<>(Protocol.values());
        this.protocolComboBox.addActionListener(e -> this.updateFunctionComboBox());

        this.protocolFunctionComboBox = new JComboBox<>();
        this.protocolFunctionComboBox.addActionListener(e -> this.updateProtocol());

        this.bottomContent.add(this.textField);
        this.bottomContent.add(this.protocolComboBox);
        this.bottomContent.add(this.protocolFunctionComboBox);

        this.panel.add(this.scrollPane, BorderLayout.CENTER);
        this.panel.add(this.bottomContent, BorderLayout.SOUTH);

        this.add(this.panel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setSize(670, 380);

        this.updateFunctionComboBox();
    }

    private void updateFunctionComboBox() {
        Protocol selectedProtocol = (Protocol) this.protocolComboBox.getSelectedItem();

        this.protocolFunctionComboBox.removeAllItems();

        for (String function : selectedProtocol.getFunctions()) {
            this.protocolFunctionComboBox.addItem(function);
        }
    }

    private void updateProtocol() {
        Protocol selectedProtocol = (Protocol) this.protocolComboBox.getSelectedItem();
        String selectedFunction = (String) this.protocolFunctionComboBox.getSelectedItem();

        selectedProtocol.setFunction(selectedFunction);

        this.textField.setText("");
    }

    private void sendProtocol() {
        String data = this.textField.getText();

        if(!data.equals("")) {
            Protocol selectedProtocol = (Protocol) this.protocolComboBox.getSelectedItem();
            selectedProtocol.setData(this.textField.getText());

            System.out.println(selectedProtocol.toSendString());
            Bluetooth.sendProtocol(selectedProtocol);
            this.textField.setText("");

            this.addLog("DEBUG", "Sent protocol message: " + selectedProtocol.toSendString());
        }
    }

    public void addLog(String level, String text) {
        LocalDateTime date = LocalDateTime.now();

        textArea.append(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " ["+level+"]: " + text + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
