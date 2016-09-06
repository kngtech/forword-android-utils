package com.example.gauravnivsarkar.projectutils.views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

/**
 * Created by gauravnivsarkar on 23/08/16.
 */

public abstract class BaseTextInputLayout extends TextInputLayout {
    public BaseTextInputLayout(Context context) {
        super(context);
    }

    public BaseTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void addTextEvents();
    protected abstract void init(final Context context,AttributeSet attrs);
}
