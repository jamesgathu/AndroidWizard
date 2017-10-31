package com.jswiftdev.wizardsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jswiftdev.wizard.LineIndicator;
import com.jswiftdev.wizard.SlideSelector;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LineIndicator lineIndicator = findViewById(R.id.indicator);
        SlideSelector slideSelector = findViewById(R.id.bubble_selector);
        SlideSelector inverseTab = findViewById(R.id.inverse_tab);
        SlideSelector tab = findViewById(R.id.tab);


        SlideSelector.SelectionChanges selectionChanges = new SlideSelector.SelectionChanges() {
            @Override
            public void onSelectedIndexChanged(int position, String selectedItem) {
                lineIndicator.setActivePage(position);
            }
        };

        slideSelector.setSelectionChanges(selectionChanges);
        inverseTab.setSelectionChanges(selectionChanges);
        tab.setSelectionChanges(selectionChanges);
    }
}

