package xyz.archroid.testino.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Exam;

public class GetExamController {
    TestinoAPI.getExamCallback getExamCallback;

    public GetExamController(TestinoAPI.getExamCallback getExamCallback) {
        this.getExamCallback = getExamCallback;
    }

    public void start(String examID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Exam> call = testinoAPI.getExam(examID);
        call.enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                if (response.isSuccessful()) {
                    getExamCallback.onResponse(true, response.body(), null);
                } else {
                    getExamCallback.onResponse(false, null, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {
                getExamCallback.onFailure(t.getMessage());
            }
        });
    }
}
