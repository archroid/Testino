package xyz.archroid.testino.Data;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Token;

public class RegisterController {
    private TestinoAPI.registerCallback registerCallback;

    public RegisterController(TestinoAPI.registerCallback registerCallback) {
        this.registerCallback = registerCallback;
    }
    public void start(String username, String password, String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Token> call = testinoAPI.register(email,password,username);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    registerCallback.onResponse(true, response.body().getToken(), null);
                } else {
                    try {
                        registerCallback.onResponse(false, null, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                registerCallback.onFailure(t.getMessage());
            }
        });
    }
}


