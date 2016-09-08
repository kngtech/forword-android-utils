package com.example.gauravnivsarkar.projectutils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.example.gauravnivsarkar.projectutils.R;
import com.example.gauravnivsarkar.projectutils.Utils;

/**
 * Created by gauravnivsarkar on 22/08/16.
 */

public class PasswordInputLayout extends BaseTextInputLayout {

    public interface PasswordState{
        void isPasswordValid(boolean state,CharSequence current);
    }

    private boolean isConfirmPassword=false,isValidPassword=false;
    private CharSequence passwordText;
    private int validLength=-1;
    private PasswordState passwordState;


    public void setPasswordState(@NonNull PasswordState passwordState) {
        this.passwordState = passwordState;
    }

    public void setPasswordText(@NonNull CharSequence passwordText) {
        if(isConfirmPassword)
        this.passwordText = passwordText;
    }

    public PasswordInputLayout(Context context) {
        super(context);
        init(context,null);
    }

    public PasswordInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public PasswordInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(final Context context,AttributeSet attrs){
        if(!isInEditMode()){
           if(attrs!=null) {
               TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PasswordInputLayout);
               if (a != null) {
                   isConfirmPassword = a.getBoolean(R.styleable.PasswordInputLayout_isConfirmPassword, false);
                   a.recycle();
               }
           }
            validLength=context.getResources().getInteger(R.integer.valid_password_length);
            if(validLength<=0){
                throw new IllegalStateException("Password length cannot be less than zero.");
            }

        }
    }

    /**
     * Call this method from the activity to add the events.This is technically not a part of the lifecycle
     */
    public void addTextEvents(){
        getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isValidPassword= Utils.isValidPassword(charSequence,validLength);
                Log.d(PasswordInputLayout.class.getCanonicalName(),"isValidPassword1"+isValidPassword);
                if(isConfirmPassword){
                    if(!TextUtils.isEmpty(passwordText)){
                        isValidPassword&=passwordText.toString().contentEquals(charSequence);
                    }else{
                        isValidPassword=false;
                    }
                }
                setErrorEnabled(!isValidPassword);
                setError(isValidPassword?null:getContext().getString(R.string.pasword_error));
                if(passwordState!=null)
                passwordState.isPasswordValid(isValidPassword,!isConfirmPassword?charSequence:null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
