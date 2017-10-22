package com.jswiftdev.wizardsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jswiftdev.wizard.SlideSelector;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlideSelector slideTest = findViewById(R.id.slider_selector);
        slideTest.setSelectionChanges(new SlideSelector.SelectionChanges() {
            @Override
            public void onSelectedIndexChanged(String selectedItem) {
                Log.i("riven: ",selectedItem);
            }
        });

        slideTest.setSelectedIndex(4);
    }
}

