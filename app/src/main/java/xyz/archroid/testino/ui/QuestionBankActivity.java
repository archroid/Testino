package xyz.archroid.testino.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.R;

public class QuestionBankActivity extends AppCompatActivity {

    private TextView textView_toolbarTitle, textView_name, textView_questionCount;
    private MaterialCardView button_back;
    private Button button_1;
    private RecyclerView recyclerView_questions;
    private MaterialCardView cardView_noQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(v -> {
            onBackPressed();
        });


        textView_toolbarTitle = findViewById(R.id.textView_toolbarTitle);
        textView_name = findViewById(R.id.textView_name_questionBank_activity);
        textView_questionCount = findViewById(R.id.textView_questionCount_questionBank_activity);

        button_1 = findViewById(R.id.button_1_questionBank_activity);
        recyclerView_questions = findViewById(R.id.recyclerView_questions_qBank_activity);
        cardView_noQuestion = findViewById(R.id.cardView_noQuestion_qBank_activity);




    }
}