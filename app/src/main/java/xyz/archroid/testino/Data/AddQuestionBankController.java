package xyz.archroid.testino.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Id;

public class AddQuestionBankController {
    private TestinoAPI.addQuestionBankCallback addQuestionBankCallback;

    public AddQuestionBankController(TestinoAPI.addQuestionBankCallback addQuestionBankCallback) {
        this.addQuestionBankCallback = addQuestionBankCallback;
    }

    public void start(String name, String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Id> call = testinoAPI.addQuestionBank(username, name);

        call.enqueue(new Callback<Id>() {
            @Override
            public void onResponse(Call<Id> call, Response<Id> response) {
                if (response.isSuccessful()) {
                    addQuestionBankCallback.onResponse(true, response.body().getId(), null);
                } else {
                    addQuestionBankCallback.onResponse(false, null, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Id> call, Throwable t) {
                addQuestionBankCallback.onFailure(t.getMessage());
            }
        });
    }
}
