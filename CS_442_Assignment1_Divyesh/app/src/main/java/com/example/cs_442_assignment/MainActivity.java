package com.example.cs_442_assignment;

//Name: Divyeshkumar Bhupendrabhai Patel
//Email Id: dpatel184@hawk.iit.edu
//Campus Id: A20495602

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button go, clear;
    EditText total_bill, number_of_people;
    TextView tip_amount, total_with_tip, total_per_person, overage;
    RadioGroup radioGroup;
    RadioButton r1, r2, r3, r4;
    DecimalFormat df = new DecimalFormat("0.00");
    double totalWithTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = findViewById(R.id.go);
        clear = findViewById(R.id.clear);
        total_bill = findViewById(R.id.total_bill);
        number_of_people = findViewById(R.id.number_of_people);
        tip_amount = findViewById(R.id.tip_amount);
        total_with_tip = findViewById(R.id.total_with_tip);
        total_per_person = findViewById(R.id.total_per_person);
        overage = findViewById(R.id.overage);
        radioGroup = findViewById(R.id.radio);

        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);

        disable();

        total_bill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String total = total_bill.getText().toString().trim();
                if (!total.isEmpty()) {
                    r1.setEnabled(true);
                    r2.setEnabled(true);
                    r3.setEnabled(true);
                    r4.setEnabled(true);

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Radio();


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!number_of_people.getText().toString().isEmpty()) {
                    int perperson = Integer.parseInt(number_of_people.getText().toString());
                    double amountPerPerson = (totalWithTip / perperson);
                    df.setRoundingMode(RoundingMode.UP);
                    total_per_person.setText("$" + df.format(amountPerPerson));
                    double tt = Double.parseDouble(df.format(amountPerPerson)) * Double.parseDouble(number_of_people.getText().toString());
                    double ttt = tt - (Double.parseDouble(df.format(totalWithTip)));
                    overage.setText("$" + df.format(ttt));
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_bill.setText("");
                clear();
                tip_amount.setText("");
                total_with_tip.setText("");
                number_of_people.setText("");
                total_per_person.setText("");
                overage.setText("");
            }
        });
    }

    //Method for radio button click
    void Radio() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                String radionum = radioButton.getText().toString();
                float radionumber;
                if (radionum.equals("12%")) {
                    radionumber = 12;
                } else if (radionum.equals("15%")) {
                    radionumber = 15;
                } else if (radionum.equals("18%")) {
                    radionumber = 18;
                } else
                    radionumber = 20;
                if (!total_bill.getText().toString().isEmpty()) {
                    //  int totalbill = Integer.parseInt(total_bill.getText().toString());
                    double totalbill = Float.parseFloat(total_bill.getText().toString());
                    double tip = totalbill * radionumber / 100;
                    df.format(tip);
                    tip_amount.setText("$" + String.valueOf(df.format(tip)));
                    totalWithTip = totalbill + tip;
                    total_with_tip.setText("$" + String.valueOf(df.format(totalWithTip)));
                }

            }
        });
    }

    //Method to make radio button disable
    void disable() {
        r1.setEnabled(false);
        r2.setEnabled(false);
        r3.setEnabled(false);
        r4.setEnabled(false);
    }

    //method to clear all data fields
    void clear() {

        if (r1.isChecked()) {
            r1.setChecked(false);
        }
        if (r2.isChecked()) {
            r2.setChecked(false);
        }
        if (r3.isChecked()) {
            r3.setChecked(false);
        }
        if (r4.isChecked()) {
            r4.setChecked(false);
        }
        disable();

    }
}