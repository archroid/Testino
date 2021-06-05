package xyz.archroid.testino.Data;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.Model.Id;
import xyz.archroid.testino.Model.Question;
import xyz.archroid.testino.Model.Status;
import xyz.archroid.testino.Model.Token;

public interface TestinoAPI {
    String BASE_URL = "http://192.168.1.108:5000/";


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
    Call<Id> addExam(
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

    @FormUrlEncoded
    @POST("getQuestions")
    Call<List<Question>> getQuestions(
            @Field("id") String id
    );

    @Multipart
    @POST("uploadImage")
    Call<Status> uploadImage(
            @Part MultipartBody.Part image,
            @Part("fileType") String fileType
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

    interface getQuestionsCallback {
        void onResponse(Boolean isSuccessful, List<Question> questions, String error);

        void onFailure(String cause);
    }


    interface uploadImageCallback{
        void onResponse(Boolean isSuccessful, String status,String error);

        void onFailure(String cause);
    }
}

