package xyz.archroid.testino.Data;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Question;

public class GetQuestionsController {
    TestinoAPI.getQuestionsCallback getQuestionsCallback;

    public GetQuestionsController(TestinoAPI.getQuestionsCallback getQuestionsCallback) {
        this.getQuestionsCallback = getQuestionsCallback;
    }

    public void start(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<List<Question>> call = testinoAPI.getQuestions(id);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful()) {
                    getQuestionsCallback.onResponse(true, response.body(), null);
                } else {
                    getQuestionsCallback.onResponse(false, null, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                getQuestionsCallback.onFailure(t.getMessage());
            }
        });

    }
}
