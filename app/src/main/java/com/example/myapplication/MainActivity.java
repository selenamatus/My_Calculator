package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    StringBuilder input = new StringBuilder();  // To store user input
    boolean isNewInput = true;  // Flag to check if a new input should be started

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textViewResult);
    }

    @SuppressLint("SetTextI18n")
    public void FuncButton(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (isNewInput) {
            result.setText("");
            input.setLength(0);  // Clear the input StringBuilder
            isNewInput = false;
        }

        // Handle digit and operator buttons
        if (isNumeric(buttonText) || buttonText.equals(".")) {
            // Append digit or decimal point to the input
            input.append(buttonText);
            result.append(buttonText);
        } else if (isOperator(buttonText)) {
            // Append operator to the input
            input.append(" ").append(buttonText).append(" ");
            result.append(buttonText);
        } else if (buttonText.equals("=")) {
            // Evaluate the expression and display the result
            try {
                String expression = input.toString();
                double calculationResult = evaluateExpression(expression);
                result.setText(String.valueOf(calculationResult));
                isNewInput = true;
            } catch (Exception e) {
                result.setText("Error");
                isNewInput = true;
            }
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private double evaluateExpression(String expression) {
        // Use a more advanced expression evaluation method if needed.
        // For simplicity, we'll use basic parsing here.
        String[] tokens = expression.split(" ");
        double operand1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double operand2 = Double.parseDouble(tokens[2]);

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Cannot divide by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
