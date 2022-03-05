package com.java.mvvmsample.ui;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.common.BaseViewModel;
import com.common.validator.EmailValidator;
import com.common.validator.PasswordValidator;
import com.google.gson.JsonObject;
import com.java.mvvmsample.R;
import com.java.mvvmsample.ui.user.UserRepository;

import timber.log.Timber;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class LoginViewModel extends BaseViewModel {

    public String strEmail;
    public String strPassword;

    private MutableLiveData<Integer> errEmailData;
    private MutableLiveData<Integer> errPasswordData;

    private MutableLiveData<JsonObject> loginApiData;
    private MutableLiveData<LoginModel> formData;
    private final UserRepository userRepository;

    // View Observables
    private MutableLiveData<Integer> loginBtnData;
    private MutableLiveData<Boolean> fullNestedData;
    private MutableLiveData<Integer> progBarData;

    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;

        loginBtnData = new MutableLiveData<>();
        progBarData = new MutableLiveData<>(View.GONE);
        fullNestedData = new MutableLiveData<>(true);
    }

    public MutableLiveData<Integer> getErrEmailData() {
        return errEmailData = (errEmailData == null) ? new MutableLiveData<>() : errEmailData;
    }

    public MutableLiveData<Integer> getErrPasswordData() {
        return errPasswordData = (errPasswordData == null) ? new MutableLiveData<>() : errPasswordData;
    }

    public MutableLiveData<JsonObject> getLoginApiData() {
        return loginApiData = (loginApiData == null) ? new MutableLiveData<>() : loginApiData;
    }

    public MutableLiveData<LoginModel> getFormData() {
        return formData = (formData == null) ? new MutableLiveData<LoginModel>() : formData;
    }

    public MutableLiveData<Integer> getLoginBtnData() {
        return loginBtnData = (loginBtnData == null) ? new MutableLiveData<>() : loginBtnData;
    }

    public MutableLiveData<Boolean> getFullNestedData() {
        return fullNestedData = (fullNestedData == null) ? new MutableLiveData<>() : fullNestedData;
    }

    public MutableLiveData<Integer> getProgBarData() {
        return progBarData;
    }

    public void performValidation() {

        errEmailData.setValue(null);
        errPasswordData.setValue(null);

        EmailValidator emailValidator = new EmailValidator(strEmail);
        if (emailValidator.isEmailEmpty()) {
            errEmailData.setValue(R.string.email_required);
        } else if (emailValidator.isEmailInValid()) {
            errEmailData.setValue(R.string.email_invalid);
        } else {
            PasswordValidator passwordValidator = new PasswordValidator(strPassword);
            if (passwordValidator.isPasswordEmpty()) {
                errPasswordData.setValue(R.string.password_required);
            } else {
                LoginModel loginModel = new LoginModel(strEmail, strPassword);
                setFullNestedData(false);
                setLoginBtnData(View.GONE);
                setProgBarData(View.VISIBLE);
                formData.setValue(loginModel);
            }
        }
    }

    private void setLoginBtnData(int visibility) {
        loginBtnData.setValue(visibility);
    }

    private void setFullNestedData(boolean isEnabled) {
        fullNestedData.setValue(isEnabled);
    }

    private void setProgBarData(int visibility) {
        progBarData.setValue(visibility);
    }

    public void login(LoginModel loginModel) {
        userRepository.loginUser(loginApiData, loginModel);
    }

    public void onLoginResponseObserved(JsonObject jsonObject) {
        setFullNestedData(true);
        setLoginBtnData(View.VISIBLE);
        setProgBarData(View.GONE);
        if (jsonObject == null) {
            Timber.d("ZIG Error");
            getToastData().setValue(R.string.api_failed);
        } else {
            Timber.d("ZIG Error %s", jsonObject.toString());
            getToastData().setValue(R.string.api_succeed);
        }
    }
}
