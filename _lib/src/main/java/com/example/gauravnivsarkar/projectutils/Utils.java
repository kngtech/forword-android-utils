package com.example.gauravnivsarkar.projectutils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by gauravnivsarkar on 22/08/16.
 */

public class Utils {
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static final boolean isValidPassword(CharSequence target,int validLength,boolean isConfirm,CharSequence password){
        if(TextUtils.isEmpty(password)) return false;
        boolean val= !TextUtils.isEmpty(target) && target.toString().trim().length()>=validLength;
        if(isConfirm) val&=password.equals(target);
        return val;
    }

    public static final boolean isTextEmpty(CharSequence target){
        return TextUtils.isEmpty(target);
    }

    public static int dpToPixels(Context ctx, float dp) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);
        return px;
    }
}