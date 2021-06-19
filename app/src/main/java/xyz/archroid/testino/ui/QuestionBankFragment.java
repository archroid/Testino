package xyz.archroid.testino.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.archroid.testino.Adapter.QuestionBankAdapter;
import xyz.archroid.testino.Data.GetQuestionBanksController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Model.QuestionBank;
import xyz.archroid.testino.R;


public class QuestionBankFragment extends Fragment {

    private RecyclerView recyclerView;

    TestinoAPI.getQuestionBanksCallback getQuestionBanksCallback;

    public QuestionBankFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_bank, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        getQuestionBanksCallback = new TestinoAPI.getQuestionBanksCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, List<QuestionBank> questionBanks, String error) {
                if (questionBanks != null) {
                    QuestionBankAdapter questionBankAdapter = new QuestionBankAdapter(questionBanks, getContext());
                    recyclerView.setAdapter(questionBankAdapter);
                }

            }

            @Override
            public void onFailure(String cause) {
                Toast.makeText(getContext(), cause, Toast.LENGTH_SHORT).show();
            }
        };

        GetQuestionBanksController getQuestionBanksController = new GetQuestionBanksController(getQuestionBanksCallback);
        getQuestionBanksController.start(String.valueOf(PrefrenceManager.getInstance(getContext()).getUsername()));


        return view;
    }
}