package com.fbvictorhugo.checkpoint.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.example.fbvictorhugo.checkpoint.R;

/**
 * By fbvictorhugo on 07/06/16.
 */
public class HourTextView extends AppCompatTextView {

    private String mHour;
    private String mPrediction;
    private int mColor;
    private int mColorDefault;

    public HourTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public HourTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typed = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HourTextView, defStyleAttr, 0);
        try {
            mHour = typed.getString(R.styleable.HourTextView_htv_hour);
            mPrediction = typed.getString(R.styleable.HourTextView_htv_prediction);
        } finally {
            typed.recycle();
        }

        mColor = getResources().getColor(R.color.primary);
        mColorDefault = getResources().getColor(R.color.checkpoint_disable);
        setText(mPrediction);
    }

    public void setHour(@NonNull String hour) {
        if (TextUtils.isEmpty(hour)) {
            clearHour();
            return;
        }
        mHour = hour;
        setText(mHour);
        setTextColor(mColor);
        if (getCompoundDrawables()[0] != null) {
            getCompoundDrawables()[0].setTint(mColor);
        }
    }

    private void clearHour() {
        setPrediction(mPrediction);
    }

    private void setPrediction(@NonNull String hour) {
        mPrediction = hour;
        setText(mPrediction);
        setTextColor(mColorDefault);
        if (getCompoundDrawables()[0] != null) {
            getCompoundDrawables()[0].setTint(mColorDefault);
        }
    }

    public boolean hasHour() {
        return !TextUtils.isEmpty(mHour);
    }

}
