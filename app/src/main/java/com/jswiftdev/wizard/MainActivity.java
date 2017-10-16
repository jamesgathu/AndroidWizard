package com.jswiftdev.wizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jswiftdev.wizard.views.WizardIndicator;

public class MainActivity extends AppCompatActivity {
    private WizardIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicator = (WizardIndicator)findViewById(R.id.indicator);
    }
}
