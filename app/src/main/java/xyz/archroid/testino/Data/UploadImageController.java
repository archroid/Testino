package xyz.archroid.testino.Data;

import java.io.File;
import java.util.Objects;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.archroid.testino.Model.Status;

public class UploadImageController {

    private TestinoAPI.uploadImageCallback uploadImageCallback;

    public UploadImageController(TestinoAPI.uploadImageCallback uploadImageCallback) {
        this.uploadImageCallback = uploadImageCallback;
    }

    public void start(File file , String fileType) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestinoAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestinoAPI testinoAPI = retrofit.create(TestinoAPI.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        Call<Status> call = testinoAPI.uploadImage(body , fileType);

        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.isSuccessful()) {
                    uploadImageCallback.onResponse(true, Objects.requireNonNull(response.body()).getStatus(), null);
                } else {
                    uploadImageCallback.onResponse(false, null, Objects.requireNonNull(response.errorBody()).toString());
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                uploadImageCallback.onFailure(t.getMessage());
            }
        });


    }
}
