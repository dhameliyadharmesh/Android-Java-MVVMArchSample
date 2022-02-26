package com.java.mvvmsample.ui.user;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.java.mvvmsample.ui.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Authoer Dharmesh
 * @Date 26-02-2022
 * <p>
 * Information
 **/
public class UserRepository {

    private UserServices userServices;

    public UserRepository(UserServices userServices) {
        this.userServices = userServices;
    }

    public void loginUser(MutableLiveData<JsonObject> loginCallObservable, LoginModel loginModel) {
        Call<JsonObject> call = userServices.loginUser(loginModel.getStrEmailAddress(), loginModel.getStrPassword());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                loginCallObservable.postValue(response.body());
//                List<Results> myheroList = response.body();
//                String[] oneHeroes = new String[myheroList.size()];
//
//                for (int i = 0; i < myheroList.size(); i++) {
//                    oneHeroes[i] = myheroList.get(i).getName();
//                }
//
//                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loginCallObservable.postValue(null);
//                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}
