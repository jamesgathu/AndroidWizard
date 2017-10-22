package com.jswiftdev.wizard.commons;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public abstract class Indicator extends View {
    protected int numberOfPages;
    protected int circleColor;
    protected int activeCircleColor;
    protected int lineColor;
    protected int textColor;
    protected float lineWidth;
    protected float circleRadius;
    protected float textSize;
    protected int activePage;
    protected Paint paint;

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Indicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * provide the total number of the pages that you wish to be shown in the indicator
     *
     * @param numberOfPages total number of pages
     * @return instance of this class
     */
    public Indicator setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }


    /**
     * provide the page number of the currently selected page
     *
     * @param activePage page number
     * @return instance of this class
     */
    public Indicator setActivePage(int activePage) {
        this.activePage = activePage;
        invalidate();
        return this;
    }

    /**
     * provide the color of the horizontal color shown on the indicator
     *
     * @param lineColor color
     * @return instance of this class
     */
    public Indicator setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    protected abstract void init(AttributeSet attrs);
}
