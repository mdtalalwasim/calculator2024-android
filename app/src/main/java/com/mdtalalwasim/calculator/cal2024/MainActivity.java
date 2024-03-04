package com.mdtalalwasim.calculator.cal2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';

    private char currentSymbol;

    private double firstValue = Double.NaN;
    private double secondValue;

    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonEqual, buttonAddition,buttonSubtraction, buttonClear, buttonPercent, buttonDivision, buttonMultiplication, buttonOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = findViewById(R.id.txtInput);
        outputDisplay = findViewById(R.id.txtOutput);

        // Initialize buttons
        initializeButtons();
    }

    private void initializeButtons() {
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonDot = findViewById(R.id.btnPoint);
        buttonEqual = findViewById(R.id.btnEqual);
        buttonAddition = findViewById(R.id.btnAddition);
        buttonSubtraction = findViewById(R.id.btnSubtraction);
        buttonMultiplication = findViewById(R.id.btnMultiplication);
        buttonDivision = findViewById(R.id.btnDivision);
        buttonPercent = findViewById(R.id.btnPercent);
        buttonClear = findViewById(R.id.btnClear);
        buttonOff = findViewById(R.id.btnOff);

        // Set click listeners for buttons
        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNumericInput(((MaterialButton) view).getText().toString());
            }
        };

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDotInput();
            }
        });

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorInput(ADDITION);
            }
        });

        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorInput(SUBTRACTION);
            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorInput(MULTIPLICATION);
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorInput(DIVISION);
            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorInput(PERCENT);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEqualInput();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClearInput();
            }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void handleNumericInput(String input) {
        inputDisplay.append(input);
    }

    private void handleOperatorInput(char operator) {
        if (!inputDisplay.getText().toString().isEmpty()) {
            allCalculation();
            currentSymbol = operator;
            outputDisplay.setText(decimalFormat.format(firstValue) + operator);
            inputDisplay.setText("");
        }
    }

    private void handleDotInput() {
        String currentInput = inputDisplay.getText().toString();
        if (!currentInput.contains(".")) {
            inputDisplay.append(".");
        }
    }

    private void handleClearInput() {
        inputDisplay.setText("");
        outputDisplay.setText("");
        firstValue = Double.NaN;
        secondValue = Double.NaN;
    }

    private void handleEqualInput() {
        allCalculation();
        outputDisplay.setText(decimalFormat.format(firstValue));
        firstValue = Double.NaN;
        currentSymbol = '0';
    }

    private void allCalculation() {
        if (!Double.isNaN(firstValue)) {
            try {
                secondValue = Double.parseDouble(inputDisplay.getText().toString());

                if (currentSymbol == ADDITION) {
                    firstValue += secondValue;
                } else if (currentSymbol == SUBTRACTION) {
                    firstValue -= secondValue;
                } else if (currentSymbol == MULTIPLICATION) {
                    firstValue *= secondValue;
                } else if (currentSymbol == DIVISION) {
                    if (secondValue != 0) {
                        firstValue /= secondValue;
                    } else {
                        Toast.makeText(this, "Error: Division by zero", Toast.LENGTH_SHORT).show();
                    }
                } else if (currentSymbol == PERCENT) {
                    firstValue %= secondValue;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
