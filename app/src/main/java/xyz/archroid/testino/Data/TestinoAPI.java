package xyz.archroid.testino.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.Model.Id;
import xyz.archroid.testino.Model.Status;
import xyz.archroid.testino.Model.Token;

public interface TestinoAPI {
    String BASE_URL = "http://192.168.1.101:5000/";


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
            @Field("duration") String duration,
            @Field("questionBankId") String questionBankId
    );

    @FormUrlEncoded
    @POST("getExams")
    Call<List<Exam>> getExams(
            @Field("creator") String username
    );

    @FormUrlEncoded
    @POST("getExam")
    Call<Exam> getExam(

            @Field("id") String examId
    );

    @FormUrlEncoded
    @POST("deleteExam")
    Call<Status> deleteExam(
            @Field("id") String examId
    );

    @FormUrlEncoded
    @POST("addQuestionBank")
    Call<Id> addQuestionBank(
            @Field("creator") String username,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("addQuestion")
    Call<Id> addQuestion(
            @Field("title") String title,
            @Field("A") String A,
            @Field("B") String B,
            @Field("C") String C,
            @Field("D") String D,
            @Field("answer") String answer,
            @Field("bankId") String bankId
    );


    interface TestNetworkCallback {
        void onResponse(Boolean isSuccessful);

        void onFailure(String cause);
    }


    interface LoginCallback {
        void onResponse(Boolean isSuccessful, String token, String error);

        void onFailure(String cause);
    }

    interface registerCallback {
        void onResponse(Boolean isSuccessful, String token, String error);

        void onFailure(String cause);
    }

    interface addExamCallback {
        void onResponse(Boolean isSuccessful);

        void onFailure(String cause);
    }

    interface getExamsCallback {
        void onResponse(Boolean isSuccessful, List<Exam> exams, String error);

        void onFailure(String cause);
    }

    interface getExamCallback {
        void onResponse(Boolean isSuccessful, Exam exam, String error);

        void onFailure(String cause);

    }

    interface deleteExamCallback {
        void onResponse(Boolean isSuccessful);
    }

    interface addQuestionBankCallback {
        void onResponse(Boolean isSuccessful, String id, String error);

        void onFailure(String cause);
    }

    interface addQuestionCallback {
        void onResponse(Boolean isSuccessful, String id, String error);

        void onFailure(String cause);
    }
}

