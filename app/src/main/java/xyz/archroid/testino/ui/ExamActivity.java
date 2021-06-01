package xyz.archroid.testino.ui;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;
import java.util.Locale;

import xyz.archroid.testino.Data.DeleteExamController;
import xyz.archroid.testino.Data.GetExamController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.R;

public class ExamActivity extends AppCompatActivity {

    private MaterialCardView button_back;
    private ImageView imageView_icon;
    private TextView textView_examName, textView_questionCount, textView_startTime, textView_duration, textView_desc;
    private MaterialCardView button_1, button_2, button_3;

    private TestinoAPI.getExamCallback getExamCallback;

    String examId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(v -> {
            onBackPressed();
        });

        imageView_icon = findViewById(R.id.imageView_icon_exam_activity);

        textView_examName = findViewById(R.id.textView_examName_exam_activity);
        textView_questionCount = findViewById(R.id.textView_questionCount_exam_activity);
        textView_startTime = findViewById(R.id.textView_questionCount_exam_activity);
        textView_duration = findViewById(R.id.textView_duration_exam_activity);
        textView_desc = findViewById(R.id.textView_examDesc_exam_activity);

        button_1 = findViewById(R.id.button_1_exam_activity);
        button_2 = findViewById(R.id.button_2_exam_activity);
        button_3 = findViewById(R.id.button_3_exam_activity);

        getExamCallback = new TestinoAPI.getExamCallback() {

            @Override
            public void onResponse(Boolean isSuccessful, Exam exam, String error) {
                if (isSuccessful) {
                    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
                    calendar.setTimeInMillis(Long.parseLong(exam.getEXAM_STARTTIME()) * 1000);
                    String date = DateFormat.format("H:mm - yyyy/MM/dd", calendar).toString();

                    textView_examName.setText(exam.getEXAM_NAME());
//                    textView_questionCount.setText();

                    textView_startTime.setText(date);
                    textView_duration.setText(exam.getEXAM_DURATION());
                    textView_desc.setText(exam.getEXAM_DESC());
                    examId = exam.getEXAM_ID();

                }
            }

            @Override
            public void onFailure(String cause) {

            }
        };

        String examId = getIntent().getStringExtra("id");

        GetExamController getExamController = new GetExamController(getExamCallback);
        getExamController.start(examId);


        button_3.setOnClickListener(v -> {
            TestinoAPI.deleteExamCallback deleteExamCallback = isSuccessful -> {
                if (isSuccessful) {
                    Toast.makeText(ExamActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                }
            };

            DeleteExamController deleteExamController = new DeleteExamController(deleteExamCallback);
            deleteExamController.start(examId);
        });


    }
}