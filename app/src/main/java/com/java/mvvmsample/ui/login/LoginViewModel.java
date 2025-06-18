package com.java.mvvmsample.ui.login;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.common.BaseViewModel;
import com.common.livedata.EventData;
import com.common.retrofit.Resource;
import com.common.validator.EmailValidator;
import com.common.validator.PasswordValidator;
import com.google.gson.JsonObject;
import com.java.mvvmsample.R;
import com.java.mvvmsample.ui.user.UserRepository;


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

    private MutableLiveData<EventData<Resource<JsonObject>>> loginApiData;
    private MutableLiveData<EventData<LoginModel>> formData;
    private final UserRepository userRepository;

    // View Observables
    private MutableLiveData<Integer> loginBtnData;
    private MutableLiveData<Boolean> enableFormData;
    private MutableLiveData<Integer> progBarData;

    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        loginApiData = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getErrEmailData() {
        return errEmailData = (errEmailData == null) ? new MutableLiveData<>() : errEmailData;
    }

    public MutableLiveData<Integer> getErrPasswordData() {
        return errPasswordData = (errPasswordData == null) ? new MutableLiveData<>() : errPasswordData;
    }

    public MutableLiveData<EventData<Resource<JsonObject>>> getLoginApiData() {
        return loginApiData = (loginApiData == null) ? new MutableLiveData<>() : loginApiData;
    }

    public MutableLiveData<EventData<LoginModel>> getFormData() {
        return formData = (formData == null) ? new MutableLiveData<EventData<LoginModel>>() : formData;
    }

    public MutableLiveData<Integer> getLoginBtnData() {
        return loginBtnData = (loginBtnData == null) ? new MutableLiveData<>() : loginBtnData;
    }

    public MutableLiveData<Boolean> getEnableFormData() {
        return enableFormData = (enableFormData == null) ? new MutableLiveData<>(true) : enableFormData;
    }

    public MutableLiveData<Integer> getProgBarData() {
        return progBarData = (progBarData == null) ? new MutableLiveData<>(View.GONE) : progBarData;
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
                formData.setValue(new EventData(loginModel));
            }
        }
    }

    private void setLoginBtnData(int visibility) {
        loginBtnData.setValue(visibility);
    }

    private void setEnableFormData(boolean isEnabled) {
        enableFormData.setValue(isEnabled);
    }

    private void setProgBarData(int visibility) {
        progBarData.setValue(visibility);
    }

    public void login(EventData<LoginModel> loginModel) {
        loginApiData.setValue(new EventData(Resource.loading(null)));
//        userRepository.loginUser(loginApiData, loginModel);
        userRepository.login(loginApiData, loginModel.peekContent());
    }

    public void  onLoginResponseObserved(Resource resource) {
        switch (resource.status) {
            case SUCCESS:
                setEnableFormData(true);
                setLoginBtnData(View.VISIBLE);
                setProgBarData(View.GONE);
                getToastData().setValue(new EventData<>(R.string.api_succeed));
                break;
            case LOADING:
                setEnableFormData(false);
                setLoginBtnData(View.GONE);
                setProgBarData(View.VISIBLE);
                break;
            case ERROR:
                setEnableFormData(true);
                setLoginBtnData(View.VISIBLE);
                setProgBarData(View.GONE);
                getToastData().setValue(new EventData<>(R.string.api_failed));
                break;
            case NO_INTERNET_ERROR:
                setEnableFormData(true);
                setLoginBtnData(View.VISIBLE);
                setProgBarData(View.GONE);
                getToastStrData().setValue(new EventData<>(resource.message));
                break;
        }
    }
}
