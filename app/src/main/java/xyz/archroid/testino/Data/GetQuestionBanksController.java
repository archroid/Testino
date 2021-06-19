package xyz.archroid.testino.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.QuestionBank;

public class GetQuestionBanksController {

    TestinoAPI.getQuestionBanksCallback getQuestionBanksCallback;

    public GetQuestionBanksController(TestinoAPI.getQuestionBanksCallback getQuestionBanksCallback) {
        this.getQuestionBanksCallback = getQuestionBanksCallback;
    }

    public void start(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);

        Call<List<QuestionBank>> call = testinoAPI.getQuestionBanks(username);
        call.enqueue(new Callback<List<QuestionBank>>() {
            @Override
            public void onResponse(Call<List<QuestionBank>> call, Response<List<QuestionBank>> response) {
                if (response.isSuccessful()) {
                    getQuestionBanksCallback.onResponse(true, response.body(), null);
                } else {
                    getQuestionBanksCallback.onResponse(false, null, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<QuestionBank>> call, Throwable t) {
                getQuestionBanksCallback.onFailure(t.getCause().toString());
            }
        });


    }
}
