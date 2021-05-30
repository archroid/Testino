package xyz.archroid.testino.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.R;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ViewHolder> {


    private List<Exam> exams;
    private Context context;

    public ExamsAdapter(List<Exam> exams, Context context) {
        this.exams = exams;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_exam, parent, false);
        return new ExamsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamsAdapter.ViewHolder holder, int position) {
        final Exam exam = exams.get(position);

        holder.textView_name.setText(exam.getEXAM_NAME());
//        holder.tv_startTime.setText(exam.getEXAM_STARTTIME());
//        holder.tv_duration.setText(exam.getEXAM_DURATION() + " سوال");

    }


    @Override
    public int getItemCount() {
        return exams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name, textView_questionCount, textView_startTime;
        MaterialCardView button_1, button_2, button_3;


        ImageView imageView_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_examName_exam_item);
            textView_questionCount = itemView.findViewById(R.id.textView_questionCount_exam_item);
            textView_startTime = itemView.findViewById(R.id.textView_startTime_exam_item);
            button_1 = itemView.findViewById(R.id.button_1_exam_item);
            button_2 = itemView.findViewById(R.id.button_2_exam_item);
            button_3 = itemView.findViewById(R.id.button_3_exam_item);
            imageView_icon = itemView.findViewById(R.id.imageView_icon_exam_item);

        }
    }
}


