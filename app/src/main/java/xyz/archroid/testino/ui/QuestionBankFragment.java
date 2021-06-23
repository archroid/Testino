package xyz.archroid.testino.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import xyz.archroid.testino.Adapter.QuestionBankAdapter;
import xyz.archroid.testino.Data.AddQuestionBankController;
import xyz.archroid.testino.Data.GetQuestionBanksController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Model.QuestionBank;
import xyz.archroid.testino.R;


public class QuestionBankFragment extends Fragment {

    private RecyclerView recyclerView;

    private TestinoAPI.getQuestionBanksCallback getQuestionBanksCallback;
    private TestinoAPI.addQuestionBankCallback addQuestionBankCallback;

    private MaterialCardView cardView_noQBank;
    private MaterialCardView button_addQBank;

    public QuestionBankFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_bank, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        cardView_noQBank = view.findViewById(R.id.cardView_noQBank_QBank_fragment);
        button_addQBank = view.findViewById(R.id.button_addQBank);


        getQuestionBanksCallback = new TestinoAPI.getQuestionBanksCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, List<QuestionBank> questionBanks, String error) {
                if (questionBanks != null) {
                    cardView_noQBank.setVisibility(View.GONE);
                    QuestionBankAdapter questionBankAdapter = new QuestionBankAdapter(questionBanks, getContext());
                    recyclerView.setAdapter(questionBankAdapter);
                } else {
                    cardView_noQBank.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(String cause) {
                Toast.makeText(getContext(), cause, Toast.LENGTH_SHORT).show();
            }
        };

        GetQuestionBanksController getQuestionBanksController = new GetQuestionBanksController(getQuestionBanksCallback);
        getQuestionBanksController.start(String.valueOf(PrefrenceManager.getInstance(getContext()).getUsername()));

        button_addQBank.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater dialogInflater = requireActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_qbank, null);
            builder.setView(dialogView)
                    .setPositiveButton("ایجاد", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            addQuestionBankCallback = new TestinoAPI.addQuestionBankCallback() {
                                @Override
                                public void onResponse(Boolean isSuccessful, String id, String error) {

                                }

                                @Override
                                public void onFailure(String cause) {

                                }
                            };

                            AddQuestionBankController addQuestionBankController = new AddQuestionBankController(addQuestionBankCallback);
                            TextInputEditText editText_qBankName_qBank_dialog = dialogView.findViewById(R.id.editText_qBankName_qBank_dialog);
                            addQuestionBankController.start(editText_qBankName_qBank_dialog.getText().toString(),String.valueOf(PrefrenceManager.getInstance(getContext()).getUsername()));

                        }
                    })
                    .setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    }).create();
            builder.show();



        });


        return view;
    }
}