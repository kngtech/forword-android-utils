package com.example.gauravnivsarkar.projectutils.views;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gauravnivsarkar.projectutils.R;


/**
 * Created by Gaurav on 4/4/16.
 * Snackbar variation for longer messages
 */
public class RetrySnackBar {

    public static Snackbar make(@StringRes int fontNameStringRes, View view, CharSequence text, int duration) {

        Snackbar snackbar = Snackbar.make(view, text, duration);
        View snackbarView = snackbar.getView();
        TextView textView = (TextView)snackbar.getView().findViewById(R.id.snackbar_text);
//        if(fontNameStringRes>0)0
//        textView.setTypeface(FontManager.getTypeface(snackbarView.getContext(), fontNameStringRes));
        textView.setEllipsize(null);
        textView.setMaxLines(5);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbarView.getLayoutParams();
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        snackbarView.setLayoutParams(params);

        return snackbar;
    }

    public static Snackbar make(@StringRes int fontNameStringRes, View view, @StringRes int textStringRes, int duration) {
        return make(fontNameStringRes, view, view.getResources().getString(textStringRes), duration);
    }
}
