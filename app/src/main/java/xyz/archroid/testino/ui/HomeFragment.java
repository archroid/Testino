package xyz.archroid.testino.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import xyz.archroid.testino.Adapter.ExamsAdapter;
import xyz.archroid.testino.Data.GetExamsController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.R;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CircleImageView iv_profile;
    private TextView tv_welcome;

    private FloatingActionButton btn_addExam;

    private ExamsAdapter examsAdapter;

    private TestinoAPI.getExamsCallback getExamCallback;

    public HomeFragment() {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        iv_profile = view.findViewById(R.id.iv_profile);
        tv_welcome = view.findViewById(R.id.tv_welcome);
        btn_addExam = view.findViewById(R.id.btn_addExam);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);





        getExamCallback = new TestinoAPI.getExamsCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, List<Exam> exams, String error) {
                if (isSuccessful) {
                    examsAdapter = new ExamsAdapter(exams, getContext());
                    recyclerView.setAdapter(examsAdapter);
                }
            }

            @Override
            public void onFailure(String cause) {

            }
        };

        GetExamsController getExamsController = new GetExamsController(getExamCallback);
        getExamsController.start(String.valueOf(PrefrenceManager.getInstance(getContext()).getUsername()));

        tv_welcome.setText("خوش آمدید، " + PrefrenceManager.getInstance(getContext()).getUsername());




        return view;
    }
}