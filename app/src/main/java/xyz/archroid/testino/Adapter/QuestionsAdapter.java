package xyz.archroid.testino.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import xyz.archroid.testino.Model.Question;
import xyz.archroid.testino.R;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<Question> questions;
    private Context context;

    public QuestionsAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new QuestionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Question question = questions.get(position);

        String questionNum = String.valueOf(position + 1);
        holder.textView_questionNum.setText(questionNum);
        holder.textView_examTitle.setText(question.getQUESTION_TITLE());

        holder.textView_question_A.setText(question.getQUESTION_A());
        holder.textView_question_B.setText(question.getQUESTION_B());
        holder.textView_question_C.setText(question.getQUESTION_C());
        holder.textView_question_D.setText(question.getQUESTION_D());

        switch (question.getQUESTION_ANSWER()) {
            case "a":
                holder.cardView_question_A.setCardBackgroundColor(context.getResources().getColor(R.color.accent));
                holder.textView_question_A.setTextColor(context.getResources().getColor(R.color.white));
                holder.textView_question_ANum.setTextColor(context.getResources().getColor(R.color.white));
                break;
            case "b":
                holder.cardView_question_B.setCardBackgroundColor(context.getResources().getColor(R.color.accent));
                holder.textView_question_B.setTextColor(context.getResources().getColor(R.color.white));
                holder.textView_question_BNum.setTextColor(context.getResources().getColor(R.color.white));
                break;
            case "c":
                holder.cardView_question_C.setCardBackgroundColor(context.getResources().getColor(R.color.accent));
                holder.textView_question_C.setTextColor(context.getResources().getColor(R.color.white));
                holder.textView_question_CNum.setTextColor(context.getResources().getColor(R.color.white));
                break;
            case "d":
                holder.cardView_question_D.setCardBackgroundColor(context.getResources().getColor(R.color.accent));
                holder.textView_question_D.setTextColor(context.getResources().getColor(R.color.white));
                holder.textView_question_DNum.setTextColor(context.getResources().getColor(R.color.white));
                break;

        }

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_questionNum, textView_examTitle;

        private TextView textView_question_ANum, textView_question_BNum, textView_question_CNum, textView_question_DNum;
        private TextView textView_question_A, textView_question_B, textView_question_C, textView_question_D;
        private MaterialCardView cardView_question_A, cardView_question_B, cardView_question_C, cardView_question_D;

        public ViewHolder(View view) {
            super(view);

            textView_questionNum = view.findViewById(R.id.textView_questionNum_question_item);
            textView_examTitle = view.findViewById(R.id.textView_examTitle_question_item);

            textView_question_ANum = view.findViewById(R.id.textView_question_ANum_question_item);
            textView_question_BNum = view.findViewById(R.id.textView_question_BNum_question_item);
            textView_question_CNum = view.findViewById(R.id.textView_question_CNum_question_item);
            textView_question_DNum = view.findViewById(R.id.textView_question_DNum_question_item);

            textView_question_A = view.findViewById(R.id.textView_question_A_question_item);
            textView_question_B = view.findViewById(R.id.textView_question_B_question_item);
            textView_question_C = view.findViewById(R.id.textView_question_C_question_item);
            textView_question_D = view.findViewById(R.id.textView_question_D_question_item);

            cardView_question_A = view.findViewById(R.id.cardView_question_A_question_item);
            cardView_question_B = view.findViewById(R.id.cardView_question_B_question_item);
            cardView_question_C = view.findViewById(R.id.cardView_question_C_question_item);
            cardView_question_D = view.findViewById(R.id.cardView_question_D_question_item);

        }
    }
}
