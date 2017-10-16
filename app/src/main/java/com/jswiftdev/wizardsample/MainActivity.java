package com.jswiftdev.wizardsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jswiftdev.wizard.Indicator;


public class MainActivity extends AppCompatActivity {
    private Indicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicator = findViewById(R.id.indicator);
    }
}
