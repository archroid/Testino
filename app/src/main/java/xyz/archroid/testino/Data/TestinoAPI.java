package xyz.archroid.testino.Data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xyz.archroid.testino.Model.Status;
import xyz.archroid.testino.Model.Token;

public interface TestinoAPI {
    String BASE_URL = "http://192.168.1.104:5000/";


    @GET("ping")
    Call<Status> testNetwork();

    @FormUrlEncoded
    @POST("login")
    Call<Token> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("userType") String userType
    );

    @FormUrlEncoded
    @POST("register")
    Call<Token> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username

    );

    @FormUrlEncoded
    @POST("addExam")
    Call<Status> addExam(
            @Field("name") String name,
            @Field("desc") String desc,
            @Field("startTime") String startTime,
            @Field("creator") String username,
            @Field("duration") String duration
    );

    @FormUrlEncoded
    @GET("getExams")
    Call<> getExams(
            @Field("creator") String username
    );


    interface TestNetworkCallback {
        void onResponse(Boolean isSuccessful);

        void onFailure(String cause);
    }



    interface LoginCallback{
        void onResponse(Boolean isSuccessful, String token, String error);

        void onFailure(String cause);
    }

    interface registerCallback{
        void onResponse(Boolean isSuccessful, String token,String error);

        void onFailure(String cause);
    }
    interface addExamCallback {
        void onResponse(Boolean isSuccessful);

        void onFailure(String cause);
    }
}

