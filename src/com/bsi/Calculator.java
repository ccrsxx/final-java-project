package com.bsi;

import javax.swing.*;

public class Calculator {
    // list of all components
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

    // a method to show message dialogs
    void showMessageError(String message) {
        JOptionPane.showMessageDialog(mainPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // a method to toggle all the inputs state
    void toggleAllInputs(boolean state) {
        JComponent[] inputs = {firstInput, secondInput, plusRadio, minusRadio, divideRadio, timesRadio};
        for (JComponent input : inputs) {
            input.setEnabled(state);
        }
    }

    // main calculator class
    public Calculator() {
        // group all the radio buttons, which are used to select the operator
        JRadioButton[] operators = {plusRadio, minusRadio, divideRadio, timesRadio};

        for (JRadioButton radioButton : operators) {
            operatorGroup.add(radioButton);
        }

        // set all input fields to be disabled by default at the beginning
        toggleAllInputs(false);

        // sumButton event listener, which will calculate the sum
        sumButton.addActionListener(e -> {
            // initialize all the needed variables
            String selectedOperator = "";
            int firstNumber, secondNumber;
            int calculationResult = 0;

            // check if any of the input fields is empty, if yes, show error message
            if (firstInput.getText().isEmpty() || secondInput.getText().isEmpty()) {
                showMessageError("Please enter two numbers");
                return;
            }

            // check if any of the operators is selected by looping through all the radio buttons
            for (JRadioButton operator : operators) {
                if (operator.isSelected()) {
                    selectedOperator = operator.getText();
                    break;
                }
            }

            // check if any of the operators is selected, if not, show error message
            if (selectedOperator.isEmpty()) {
                showMessageError("Please select an operator");
                return;
            }

            // check if the selected operator is valid int, if not, show error message
            try {
                firstNumber = Integer.parseInt(firstInput.getText());
                secondNumber = Integer.parseInt(secondInput.getText());
            } catch (NumberFormatException ex) {
                showMessageError("Please enter a valid number");
                return;
            }

            // calculate the result based on the selected operator
            switch (selectedOperator) {
                case "+" -> calculationResult = firstNumber + secondNumber;
                case "-" -> calculationResult = firstNumber - secondNumber;
                case "/" -> calculationResult = firstNumber / secondNumber;
                case "*" -> calculationResult = firstNumber * secondNumber;
            }

            // convert the result to string, add thousands separator and show it in the sumField
            sumField.setText(String.format("%,d", calculationResult));
        });

        // resetButton event listener, which will reset all the input fields and the sumField
        resetButton.addActionListener(e -> {
            firstInput.setText("");
            operatorGroup.clearSelection();
            secondInput.setText("");
            sumField.setText("");
        });

        // toggleActiveButton event listener, which will toggle all the input fields state
        toggleActiveButton.addActionListener(e -> toggleAllInputs(!firstInput.isEnabled()));
    }

    // main method to start the calculator
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Calculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
