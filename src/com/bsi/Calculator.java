package com.bsi;

import javax.swing.*;

public class Calculator {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel firstLabel;
    private JLabel operatorLabel;
    private JLabel secondLabel;
    private JLabel sumLabel;
    private JLabel sumField;
    private JTextField firstInput;
    private JTextField secondInput;
    private JRadioButton plusRadio;
    private JRadioButton minusRadio;
    private JRadioButton divideRadio;
    private JRadioButton timesRadio;
    private JButton sumButton;
    private JButton resetButton;
    private JButton toggleActiveButton;
    private ButtonGroup operatorGroup;

    void showMessageError(String message) {
        JOptionPane.showMessageDialog(mainPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    void toggleAllInputs(boolean active) {
        JComponent[] inputs = {firstInput, secondInput, plusRadio, minusRadio, divideRadio, timesRadio};
        for (JComponent input : inputs) {
            input.setEnabled(active);
        }
    }

    public Calculator() {
        JRadioButton[] operators = {plusRadio, minusRadio, divideRadio, timesRadio};

        for (JRadioButton radioButton : operators) {
            operatorGroup.add(radioButton);
        }

        toggleAllInputs(false);

        sumButton.addActionListener(e -> {
            String operator = "";
            int first, second;
            int result = 0;

            if (firstInput.getText().isEmpty() || secondInput.getText().isEmpty()) {
                showMessageError("Please enter two numbers");
                return;
            }

            for (JRadioButton radioButton : operators) {
                if (radioButton.isSelected()) {
                    operator = radioButton.getText();
                    break;
                }
            }

            if (operator.isEmpty()) {
                showMessageError("Please select an operator");
                return;
            }

            try {
                first = Integer.parseInt(firstInput.getText());
                second = Integer.parseInt(secondInput.getText());
            } catch (NumberFormatException ex) {
                showMessageError("Please enter a valid number");
                return;
            }

            switch (operator) {
                case "+" -> result = first + second;
                case "-" -> result = first - second;
                case "/" -> result = first / second;
                case "*" -> result = first * second;
            }

            sumField.setText(String.format("%,d", result));
        });

        resetButton.addActionListener(e -> {
            firstInput.setText("");
            operatorGroup.clearSelection();
            secondInput.setText("");
            sumField.setText("");
        });

        toggleActiveButton.addActionListener(e -> toggleAllInputs(!firstInput.isEnabled()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
