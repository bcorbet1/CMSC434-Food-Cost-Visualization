package com.example.nazanin.ta05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputActivity extends AppCompatActivity {
    PieChartView _pieChart;
    EditText[] _costField;
    String[] _fieldNames = {"Breakfast", "Lunch", "Dinner", "Snacks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        _costField = new EditText[4];
        _pieChart = (PieChartView)findViewById(R.id.viewPieChart);
        _costField[0] = (EditText)findViewById(R.id.breakfastEditText);
        _costField[1] = (EditText)findViewById(R.id.lunchEditText);
        _costField[2] = (EditText)findViewById(R.id.dinnerEditText);
        _costField[3] = (EditText)findViewById(R.id.snacksEditText);


//        View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    EditText editText = (EditText) v;
//                    String text = editText.getText().toString();
//                    String formatted = String.format("%.2f", text);
//                    editText.setText(formatted);
//                }
//            }
//        };

        TextWatcher listener = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {
                refreshCosts();
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };

        for (EditText field : _costField) {
            field.addTextChangedListener(listener);
//            field.setOnFocusChangeListener(focusListener);
        }
    }

    protected void refreshCosts() {
        double sum = 0;
        double[] costs = new double[_costField.length];
        for (int i = 0; i < _costField.length; i++) {
            String text = _costField[i].getText().toString().replace("\\$", "");
            try {
                costs[i] = Double.parseDouble(text);
            } catch (NumberFormatException e) {
                costs[i] = 0;
            }
        }
        _pieChart.setData(costs, _fieldNames);
    }

    public void cancel(View view) {
        finish();
    }

    public void save(View view) {
        startActivity(new Intent(InputActivity.this, SummaryActivity.class));
    }
}
