package xyz.archroid.testino.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import xyz.archroid.testino.R;

public class AddExamActivity extends AppCompatActivity {

    private MaterialCardView button_back;
    private LinearProgressIndicator progressIndicator_addExam;
    private TextView textView_progress;

    private AddExamFirstFragment addExamFirstFragment = new AddExamFirstFragment();

    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);


        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(v -> {
            onBackPressed();
        });

        progressIndicator_addExam = findViewById(R.id.progressIndicator_addExam_Activity);
        textView_progress = findViewById(R.id.textView_progress_addExam_Activity);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, addExamFirstFragment).commit();
        currentFragment = "1";


    }
}