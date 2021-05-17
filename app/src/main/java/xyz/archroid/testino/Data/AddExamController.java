package xyz.archroid.testino.Data;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Status;

public class AddExamController {

    private TestinoAPI.addExamCallback addExamCallback;


    public AddExamController(TestinoAPI.addExamCallback addExamCallback) {
        this.addExamCallback = addExamCallback;
    }

    public void start(String name, String desc, String startTime, String username, String duration) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Status> call = testinoAPI.addExam(name, desc, startTime, username, duration);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.isSuccessful()) {
                    addExamCallback.onResponse(true);
                } else {
                    try {
                        addExamCallback.onFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                addExamCallback.onFailure(t.getMessage());

            }
        });
    }
}
