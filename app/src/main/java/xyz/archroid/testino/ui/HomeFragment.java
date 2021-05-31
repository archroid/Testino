package xyz.archroid.testino.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import xyz.archroid.testino.Adapter.ExamsAdapter;
import xyz.archroid.testino.Data.GetExamsController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.R;


public class HomeFragment extends Fragment {

    private TestinoAPI.getExamsCallback getExamCallback;
    private RecyclerView recyclerView;
    private ExamsAdapter examsAdapter;

    private MaterialCardView button_addExam;
    private TextView textView_username;

    private MaterialCardView cardView_noExam;

    private String userType;

    public HomeFragment() {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        userType = PrefrenceManager.getInstance(getContext()).getUserType();

        textView_username = view.findViewById(R.id.textView_username_home_fragment);
        recyclerView = view.findViewById(R.id.recyclerView);

        cardView_noExam = view.findViewById(R.id.cardView_noExam_home_fragment);

        button_addExam = view.findViewById(R.id.button_addExam);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if (!userType.equals("admin")){
            button_addExam.setVisibility(View.GONE);
        }

        button_addExam.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddExamActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });


        getExamCallback = new TestinoAPI.getExamsCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, List<Exam> exams, String error) {
                if (isSuccessful) {
                    if (exams != null) {
                        cardView_noExam.setVisibility(View.GONE);
                        examsAdapter = new ExamsAdapter(exams, getContext());
                        recyclerView.setAdapter(examsAdapter);
                    } else {
                        cardView_noExam.setVisibility(View.VISIBLE);
                    }

                } else {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(String cause) {
                Toast.makeText(getContext(), cause, Toast.LENGTH_SHORT).show();

            }
        };

        GetExamsController getExamsController = new GetExamsController(getExamCallback);
        getExamsController.start(String.valueOf(PrefrenceManager.getInstance(getContext()).getUsername()));


        return view;
    }
}