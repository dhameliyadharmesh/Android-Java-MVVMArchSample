package com.java.mvvmsample.ui.login;

/**
 * @Authoer Dharmesh
 * @Date 24-02-2022
 * <p>
 * Information
 **/
public class LoginModel {

    private String strEmail;
    private String strPassword;

    public LoginModel(String email, String password) {
        strEmail = email;
        strPassword = password;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrEmail() {
        return (strEmail == null) ? "" : strEmail;
    }

    public String getStrPassword() {
        return (strPassword == null) ? "" : strPassword;
    }
}
