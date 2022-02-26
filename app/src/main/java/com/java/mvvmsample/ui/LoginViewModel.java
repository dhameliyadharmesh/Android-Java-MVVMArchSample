package com.java.mvvmsample.ui;

import androidx.lifecycle.MutableLiveData;

import com.common.BaseViewModel;
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

    public String EmailAddress;
    public String Password;

    public MutableLiveData<Integer> ErrorEmailAddress = new MutableLiveData<>();
    public MutableLiveData<Integer> ErrorPassword = new MutableLiveData<>();
    public MutableLiveData<JsonObject> loginCallObservable = new MutableLiveData<>();

    private MutableLiveData<LoginModel> userMutableLiveData;
    private UserRepository userRepository;

    public MutableLiveData<LoginModel> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public MutableLiveData<JsonObject> getLoginCallObservable(){
        if (loginCallObservable == null) {
            loginCallObservable = new MutableLiveData<>();
        }
        return loginCallObservable;
    }

    public void validateForm() {
        LoginModel loginModel = new LoginModel(EmailAddress, Password);
        ErrorEmailAddress.postValue(null);
        ErrorPassword.postValue(null);

        EmailValidator emailValidator = new EmailValidator(loginModel.getStrEmailAddress());
        if (emailValidator.isEmailEmpty()) {
            ErrorEmailAddress.postValue(R.string.email_required);
        } else if (emailValidator.isEmailInValid()) {
            ErrorEmailAddress.postValue(R.string.email_invalid);
        } else {
            PasswordValidator passwordValidator = new PasswordValidator(loginModel.getStrPassword());
            if (passwordValidator.isPasswordEmpty()) {
                ErrorPassword.postValue(R.string.password_required);
            } else {
                userMutableLiveData.postValue(loginModel);
            }
        }
    }

    public void login(LoginModel loginModel){
        userRepository.loginUser(loginCallObservable,loginModel);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
