package com.example.mobdevassignment;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //linking spinner id fields that were added to the UI
    Spinner rateSpinner;
    Spinner periodSpinner;
    Spinner freqSpinner;

    //these values are used for the input and totalVal picks up the id from activity_main.xml
    private EditText enterAmount;
    private TextView totalVal;

    int yearPeriod;
    int freq;
    double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning the spinners to their correct R.id.
        rateSpinner = findViewById(R.id.rateInput);
        periodSpinner = findViewById(R.id.periodInput);
        freqSpinner = findViewById(R.id.freqInput);

        Button calculatePayment = findViewById(R.id.calculateBtn);
        calculatePayment.setOnClickListener(this);

        rateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();
                //switch case to pick any desired option from the list of given rates
                switch (selectedClass) {
                    case "Fixed rate: 3.49%":
                        rate = 0.0349;
                        break;

                    case "Fixed rate: 2.29%":
                        rate = 0.0229;
                        break;

                    case "Fixed rate: 2.24%":
                        rate = 0.0224;
                        break;

                    case "Fixed rate: 5.24%":
                        rate = 0.0524;
                        break;

                    case "Fixed rate: 1.55%":
                        rate = 0.0155;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rate = 0.0349;
            }
        });

        periodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();
                //
                switch (selectedClass) {
                    case "5 years":
                        yearPeriod = 5;
                        break;

                    case "10 years":
                        yearPeriod = 10;
                        break;

                    case "15 years":
                        yearPeriod = 15;
                        break;

                    case "20 years":
                        yearPeriod = 20;
                        break;

                    case "25 years":
                        yearPeriod = 25;
                        break;

                    case "30 years":
                        yearPeriod = 30;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                yearPeriod = 25;
            }
        });

        freqSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();

                switch (selectedClass) {
                    case "Monthly":
                        freq = 12;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                freq = 12;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calculateBtn:

                int months = freq * yearPeriod; double c = rate / 12;

                enterAmount = findViewById(R.id.txtAmount);
                int amt = Integer.parseInt(enterAmount.getText().toString());

                double totPay = amt * c *Math.pow(1 + c, months)/(Math.pow(1 + c, months) - 1);
                String payment = new DecimalFormat("#.0#").format(totPay);

                totalVal = findViewById(R.id.totalVal);
                totalVal.setText("Your payment total will be: $" + (payment));
                break;
        }
    }
}

