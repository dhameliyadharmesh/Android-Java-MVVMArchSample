package com.java.mvvmsample.ui;

import android.util.Patterns;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class LoginModel {

    private String strEmailAddress;
    private String strPassword;

    public LoginModel(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;
    }

    public String getStrEmailAddress() {
        return (strEmailAddress == null) ? "" : strEmailAddress;
    }

    public String getStrPassword() {
        return (strPassword == null) ? "" : strPassword;
    }
}
