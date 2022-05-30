package com.bsi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel mainPanel;
    private JButton sumButton;
    private JLabel firstLabel;
    private JLabel secondLabel;
    private JTextField firstNumber;
    private JTextField secondNumber;
    private JButton resetButton;
    private JLabel titlePanel;
    private JLabel sumLabel;
    private JLabel sumField;

    public App() {
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int first, second;

                try {
                    first = Integer.parseInt(App.this.firstNumber.getText());
                    second = Integer.parseInt(App.this.secondNumber.getText());
                    sumField.setText(String.valueOf(first + second));
                } catch (NumberFormatException ex) {
                    // add exception handling
                    JOptionPane.showMessageDialog(App.this.mainPanel, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
