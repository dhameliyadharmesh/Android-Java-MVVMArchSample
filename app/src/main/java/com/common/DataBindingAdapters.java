package com.common;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class DataBindingAdapters {

    @BindingAdapter("app:errorText")
    public static void setTextInputLayoutError(TextInputLayout textInputLayout, MutableLiveData<Integer> errorText) {
        textInputLayout.setError((errorText.getValue() == null) ? null : textInputLayout.getContext().getString(errorText.getValue()));
    }

    @BindingAdapter("app:errorFocus")
    public static void setTextInputEditTextFocus(TextInputEditText textInputEditText, MutableLiveData<Integer> errorText) {
        if(errorText.getValue() != null){
            textInputEditText.requestFocus();
        }
    }
}
