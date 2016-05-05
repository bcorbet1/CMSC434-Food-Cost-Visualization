package com.example.nazanin.ta05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DonateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        String[] days = createNumberArray(1, 31);
        String[] months = createNumberArray(1, 31);
        String[] years = createNumberArray(2016, 2036);

        Spinner daySpinner = initiateSpinner(R.id.daySpinner, days);
        Spinner monthSpinner = initiateSpinner(R.id.monthSpinner, months);
        Spinner yearSpinner = initiateSpinner(R.id.yearSpinner, years);
    }

    protected Spinner initiateSpinner(int id, String[] data) {
        Spinner spinner = (Spinner) findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DonateActivity.this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    public void ten(View view) {
        TextView otherText = (TextView)findViewById(R.id.otherEditText);
        otherText.setText("10.00");
    }

    public void twentyFive(View view) {
        TextView otherText = (TextView)findViewById(R.id.otherEditText);
        otherText.setText("25.00");
    }

    public void cancel(View view) {
        finish();
    }

    protected String[] createNumberArray(int start, int end) {
        String[] numbers = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            numbers[i - start] = String.valueOf(i);
        }
        return numbers;
    }
}
