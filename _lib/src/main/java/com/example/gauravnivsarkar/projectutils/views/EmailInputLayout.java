package com.example.gauravnivsarkar.projectutils.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.example.gauravnivsarkar.projectutils.R;
import com.example.gauravnivsarkar.projectutils.Utils;

/**
 * Created by gauravnivsarkar on 22/08/16.
 *
 */

public class EmailInputLayout extends BaseTextInputLayout {
    /**
     * Interface to propogate state of email validity
     */
    public interface EmailState{
        void isValid(boolean state);
    }

    /**
     * add a string resource email_error to get desired email error text
     */
    private boolean validEmail;
    private EmailState emailState;

    public EmailInputLayout(Context context) {
        super(context);
    }

    public EmailInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public EmailInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    protected void init(final Context context, AttributeSet attrs){
        if (!isInEditMode()) {

//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EmailInputLayout);
//
//            if (a == null) return;
//            a.recycle();


        }
    }


    /**
     * Call this method from the activity to add the events.This is technically not a part of the lifecycle
     */

    public void addTextEvents(){
        getEditText().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validEmail= Utils.isValidEmail(charSequence);
                setErrorEnabled(!validEmail);
                setError(validEmail?null:getContext().getString(R.string.email_error));
                if(emailState!=null)
                emailState.isValid(validEmail);
                Log.d("ProjectUtils","Is email error?"+!validEmail);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setEmailState(@NonNull EmailState emailState) {
        this.emailState = emailState;
    }
}
