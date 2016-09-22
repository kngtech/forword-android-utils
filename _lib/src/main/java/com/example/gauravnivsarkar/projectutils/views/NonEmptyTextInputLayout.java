package com.example.gauravnivsarkar.projectutils.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.example.gauravnivsarkar.projectutils.R;
import com.example.gauravnivsarkar.projectutils.Utils;

/**
 * Created by gauravnivsarkar on 23/08/16.
 */

public class NonEmptyTextInputLayout extends BaseTextInputLayout {
    public interface EmptyState{
        void isEmpty(boolean state);
    }
    private boolean isNotEmpty =false;
    private EmptyState stateListener;

    public void setStateListener(@NonNull EmptyState stateListener) {
        this.stateListener = stateListener;
    }

    public NonEmptyTextInputLayout(Context context) {
        super(context);
        init(context,null);
    }

    public NonEmptyTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public NonEmptyTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    public void addTextEvents() {
        getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isNotEmpty = !Utils.isTextEmpty(charSequence);
                setErrorEnabled(!isNotEmpty);
                setError(isNotEmpty?null:getContext().getString(R.string.blank_error));
                if(stateListener!=null){
                    stateListener.isEmpty(!isNotEmpty);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {

//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EmailInputLayout);
//
//            if (a == null) return;
//            a.recycle();


        }
    }

}
