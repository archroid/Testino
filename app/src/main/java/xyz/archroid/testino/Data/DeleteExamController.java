package xyz.archroid.testino.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Status;

public class DeleteExamController {

    TestinoAPI.deleteExamCallback deleteExamCallback;

    public DeleteExamController(TestinoAPI.deleteExamCallback deleteExamCallback) {
        this.deleteExamCallback = deleteExamCallback;
    }

    public void start(String examID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Status> call = testinoAPI.deleteExam(examID);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.isSuccessful()) {
                    deleteExamCallback.onResponse(true);
                } else {
                    deleteExamCallback.onResponse(false);
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                deleteExamCallback.onResponse(false);

            }
        });
    }
}
