package com.jswiftdev.wizard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jswiftdev.wizard.commons.Indicator;

import java.util.HashMap;

/**
 * Created by james on 17/10/2017.
 */

public class AnimIndicator extends Indicator {
    private int arrayId;

    private String[] optionsArray;
    private HashMap<String, TextView> tvLists;
    private LinearLayout linearLayout;
    private Drawable backCircle;

    public AnimIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.wizard);
        numberOfPages = typedArray.getInt(R.styleable.wizard_numberOfPages, 1);
        activePage = typedArray.getInt(R.styleable.wizard_activePage, 1);
        circleColor = typedArray.getColor(R.styleable.wizard_circleColor, Color.BLACK);
        activeCircleColor = typedArray.getColor(R.styleable.wizard_activeCircleColor, Color.BLACK);
        lineColor = typedArray.getColor(R.styleable.wizard_lineColor, Color.GRAY);
        textColor = typedArray.getColor(R.styleable.wizard_textColor, Color.WHITE);
        lineWidth = typedArray.getFloat(R.styleable.wizard_lineWidth, 10);
        textSize = typedArray.getFloat(R.styleable.wizard_textSize, 10);
        circleRadius = typedArray.getFloat(R.styleable.wizard_circleRadius, 20);
        arrayId = typedArray.getResourceId(R.styleable.wizard_optionsArray, R.array.options_default);
        typedArray.recycle();

        paint = new Paint();

        tvLists = new HashMap<>();
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        backCircle = getResources().getDrawable(R.drawable.back_circle);
        optionsArray = getResources().getStringArray(arrayId);
    }

    private TextView getTextView(String text) {
        TextView tv = new TextView(getContext());
        tv.setText(text);
        return tv;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);

        float width = getWidth();
        float height = getHeight() / 2;

        float singleDiv = width / optionsArray.length;

        float x;

        Log.i("riven : ","total width : "+getWidth());
        Log.i("riven : ","single width : "+singleDiv);

        for (int i = 1; i <= optionsArray.length; i++) {
            x = i * singleDiv;

            paint.setTextSize(textSize);
            canvas.drawText(optionsArray[i], x, height, paint);

            Log.i("riven : ",optionsArray[i]);
        }
    }
}
