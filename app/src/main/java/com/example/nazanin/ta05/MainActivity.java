package com.example.nazanin.ta05;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void donate(View view) {
        startActivity(new Intent(MainActivity.this, DonateActivity.class));
    }

    public void enterFoodCost(View view) {
        startActivity(new Intent(MainActivity.this, InputActivity.class));
    }

    public void compareFoodCost(View view) {
        startActivity(new Intent(MainActivity.this, CompareActivity.class));
    }
}
