package xyz.archroid.testino.Data;

import java.util.List;

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
        Call<List<Exam>> call = testinoAPI.getExams(username);
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                if (response.isSuccessful()) {
                    getExamsCallback.onResponse(true, response.body(), null);
                }
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {

            }
        });
    }
}