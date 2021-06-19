package xyz.archroid.testino.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Status;

public class DeleteQuestionBankController {
    TestinoAPI.deleteQuestionBankCallback deleteQuestionBankCallback;

    public DeleteQuestionBankController(TestinoAPI.deleteQuestionBankCallback deleteQuestionBankCallback) {
        this.deleteQuestionBankCallback = deleteQuestionBankCallback;
    }

    public void start(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        Call<Status> call = testinoAPI.deleteQuestionBank(id);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                deleteQuestionBankCallback.onResponse(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                deleteQuestionBankCallback.onResponse(false);

            }
        });

    }
}
