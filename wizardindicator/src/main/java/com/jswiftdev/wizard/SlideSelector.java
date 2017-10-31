package com.jswiftdev.wizard;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by james on 22/10/2017.
 */

public class SlideSelector extends LinearLayout implements OnClickListener {
    private int textPadding = 0;
    private int textBackGroundColor = Color.TRANSPARENT;
    private int textColor = Color.BLACK;
    private CharSequence[] optionsArrayId;
    private int drawableId = R.drawable.back_circle;
    private int activeTextColor = Color.WHITE;
    private int inActiveTextColor = Color.GRAY;

    private Drawable marker;
    private LinearLayout linearLayout;
    private FrameLayout ballMarker;

    private int mode = 3;
    public static final int MODE_TAB = 1;
    public static final int MODE_TAB_INVERSE = 2;
    public static final int MODE_BUBBLE = 3;

    /**
     * provide callback for actions done on the view group
     */
    private SelectionChanges selectionChanges;

    private int selectedIndex = 0;


    public SlideSelector(Context context) {
        super(context);
        init(null);
    }

    public SlideSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SlideSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setSelectionChanges(SelectionChanges selectionChanges) {
        this.selectionChanges = selectionChanges;
    }

    private void init(AttributeSet attributeSet) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base, this);
        linearLayout = view.findViewById(R.id.textCarrier);
        ballMarker = view.findViewById(R.id.ballCarrier);

        if (attributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.chooser);
            textPadding = typedArray.getInt(R.styleable.chooser_textPadding, 0);
            mode = typedArray.getInt(R.styleable.chooser_mode, 3);
            textBackGroundColor = typedArray.getColor(R.styleable.chooser_textBackgroundColor, Color.TRANSPARENT);
            textColor = typedArray.getColor(R.styleable.chooser_tColor, Color.WHITE);
            activeTextColor = typedArray.getColor(R.styleable.chooser_activeTextColor, Color.WHITE);
            inActiveTextColor = typedArray.getColor(R.styleable.chooser_inActiveTextColor, Color.WHITE);
            optionsArrayId = typedArray.getTextArray(R.styleable.chooser_choicesArrayId);
            drawableId = typedArray.getResourceId(R.styleable.chooser_drawableId, Color.WHITE);
            typedArray.recycle();
        }


        if (optionsArrayId == null)
            optionsArrayId = getResources().getStringArray(R.array.options_default);


        for (CharSequence text : optionsArrayId) {
            TextView textView = getTextView(text);
            linearLayout.addView(textView);
        }

        marker = getResources().getDrawable(drawableId);
        marker.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);

        ballMarker.setBackgroundDrawable(marker);

        switch (mode) {
            case MODE_TAB_INVERSE:
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ballMarker.getLayoutParams();
                params.gravity = Gravity.BOTTOM;
                ballMarker.setLayoutParams(params);
                break;
        }
    }


    /**
     * generate textviews and return them
     *
     * @param text String to be shown
     * @return TextView
     */
    private TextView getTextView(CharSequence text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);

        LinearLayout.LayoutParams params = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        params.weight = 1;

        textView.setPadding(textPadding, textPadding, textPadding, textPadding);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);

        textView.setTextColor(textColor);
        textView.setTag(text);
        textView.setOnClickListener(this);
        textView.setBackgroundColor(textBackGroundColor);

        switch (mode) {
            case MODE_TAB:
            case MODE_TAB_INVERSE:
                textView.setText(textView.getText().toString().toUpperCase());
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case MODE_BUBBLE:
                break;
        }

        return textView;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        select(linearLayout.getChildAt(selectedIndex));
    }

    @Override
    public void onClick(View v) {
        select(v);
    }

    /**
     * set the default selected item
     * CAUTION : this method fails silently
     *
     * @param index for the choice
     */
    public void setSelectedIndex(int index) {
        if (index < linearLayout.getChildCount()) {
            this.selectedIndex = index;
        } else {
            this.selectedIndex = 0;
            try {
                throw new Exception("selected index exceeds available choices");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void select(final View v) {
        float halfBallWidth = ballMarker.getWidth() / 2;
        float halfViewWidth = v.getWidth() / 2;
        float offsetForBallLocation = halfViewWidth - halfBallWidth;

        selectedIndex = linearLayout.indexOfChild(v) + 1;

        ballMarker.animate()
                .translationX(v.getX() + offsetForBallLocation)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        for (int i = 0; i < linearLayout.getChildCount(); i++) {
                            if (linearLayout.getChildAt(i) instanceof TextView) {
                                ((TextView) linearLayout.getChildAt(i)).setTextColor(inActiveTextColor);
                            }
                        }
                        ((TextView) v).setTextColor(activeTextColor);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


        if (selectionChanges != null)
            selectionChanges.onSelectedIndexChanged(selectedIndex, v.getTag().toString());
    }


    public interface SelectionChanges {
        void onSelectedIndexChanged(int position, String selectedItem);
    }
}
