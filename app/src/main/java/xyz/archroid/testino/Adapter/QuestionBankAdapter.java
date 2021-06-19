package xyz.archroid.testino.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import xyz.archroid.testino.Data.DeleteQuestionBankController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Model.QuestionBank;
import xyz.archroid.testino.R;
import xyz.archroid.testino.ui.QuestionBankActivity;

public class QuestionBankAdapter extends RecyclerView.Adapter<QuestionBankAdapter.ViewHolder> {

    private List<QuestionBank> questionBankList;
    private Context context;

    public QuestionBankAdapter(List<QuestionBank> questionBankList, Context context) {
        this.questionBankList = questionBankList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_bank, parent, false);
        return new QuestionBankAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionBank questionBank = questionBankList.get(position);

        holder.textView_name.setText(questionBank.getQUESTION_BANK_NAME());

        TestinoAPI.deleteQuestionBankCallback deleteQuestionBankCallback = new TestinoAPI.deleteQuestionBankCallback() {
            @Override
            public void onResponse(Boolean isSuccessful) {
                Toast.makeText(context, "Question Bank deleted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String cause) {

            }
        };


        holder.button_3.setOnClickListener(v -> {
            DeleteQuestionBankController deleteQuestionBankController = new DeleteQuestionBankController(deleteQuestionBankCallback);
            deleteQuestionBankController.start(questionBank.getQUESTION_BANK_ID());
        });

        holder.button_1.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionBankActivity.class);
            intent.putExtra("id", questionBank.getQUESTION_BANK_ID());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //TODO set questionBank Count


    }

    @Override
    public int getItemCount() {
        return questionBankList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_name, textView_questionCount;
        MaterialCardView button_1, button_3;
        ShapeableImageView imageView_button_1, imageView_button_3;

        public ViewHolder(View view) {
            super(view);

            textView_name = view.findViewById(R.id.textView_name_questionBank_item);
            textView_questionCount = view.findViewById(R.id.textView_questionCount_questionBank_item);

            button_1 = view.findViewById(R.id.button_1_questionBank_item);
            button_3 = view.findViewById(R.id.button_3_questionBank_item);

            imageView_button_1 = view.findViewById(R.id.imageView_button_1);
            imageView_button_3 = view.findViewById(R.id.imageView_button_3);
        }
    }
}
