package com.example.nazanin.ta05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
    }

    public void home(View view) {
        startActivity(new Intent(SummaryActivity.this, MainActivity.class));
    }
}
