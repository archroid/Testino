package xyz.archroid.testino.Data;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Exam;

public class GetExamsController {

    private TestinoAPI.getExamsCallback getExamsCallback;

    public GetExamsController(TestinoAPI.getExamsCallback getExamsCallback) {
        this.getExamsCallback = getExamsCallback;
    }

    public void start(String username) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<ArrayList<Exam>> call = testinoAPI.getExams(username);

        call.enqueue(new Callback<ArrayList<Exam>>() {
            @Override
            public void onResponse(Call<ArrayList<Exam>> call, Response<ArrayList<Exam>> response) {
                Log.d("TAG", "onResponse: " + response.body());
                if (response.isSuccessful()) {
                    getExamsCallback.onResponse(true, response.body(), null);
                } else {
                    try {
                        getExamsCallback.onResponse(false, null, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Exam>> call, Throwable t) {
                getExamsCallback.onFailure(t.getMessage());
            }
        });

    }
}
