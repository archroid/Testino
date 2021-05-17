package xyz.archroid.testino.Data;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Token;

public class LoginController {

    private TestinoAPI.LoginCallback loginCallback;

    public LoginController(TestinoAPI.LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
    public void start(String email, String password, String userType) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Token> call = testinoAPI.login(email, password, userType);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    loginCallback.onResponse(true, response.body().getToken(), null);
                } else {
                    try {
                        loginCallback.onResponse(false, null, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                loginCallback.onFailure(t.getMessage());
            }
        });


    }



}
