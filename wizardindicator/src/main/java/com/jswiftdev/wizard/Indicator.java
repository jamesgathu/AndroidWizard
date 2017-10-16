package com.jswiftdev.wizard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Indicator extends View {
    private int numberOfPages;
    private int circleColor;
    private int activeCircleColor;
    private int lineColor;
    private int textColor;
    private float lineWidth;
    private float circleRadius;
    private float textSize;
    private int activePage;

    private Paint paint;

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Indicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Indicator setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Indicator setActivePage(int activePage) {
        this.activePage = activePage;
        invalidate();
        return this;
    }


    private void init(AttributeSet attrs) {
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
        typedArray.recycle();

        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setAntiAlias(true);

        float height = getHeight();
        float width = getWidth();

        //draw line
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        canvas.drawLine(0, height / 2, width, height / 2, paint);

        //get the division for the circles
        float singleWidth = width / (numberOfPages + 1);

        //draw circles
        paint.setTextSize(textSize);
        float x = 0;
        float relatedRatio = 3;
        for (int i = 1; i <= numberOfPages; i++) {
            x = singleWidth * i;


            if (i == activePage) {
                paint.setColor(activeCircleColor);
                canvas.drawCircle(x, height / 2, circleRadius, paint);
            } else {
                paint.setColor(circleColor);
                canvas.drawCircle(x, height / 2, circleRadius, paint);
            }

            paint.setColor(textColor);
            canvas.drawText(String.valueOf(i), 0, 1, x - textSize / relatedRatio,
                    (height / 2) + textSize / relatedRatio, paint);
        }
    }
}
