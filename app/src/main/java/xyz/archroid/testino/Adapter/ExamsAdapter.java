package xyz.archroid.testino.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

        holder.tv_exam_name.setText(exam.getEXAM_NAME());
        holder.tv_exam_desc.setText(exam.getEXAM_DESC());
        holder.tv_questions.setText("");
        holder.tv_startTime.setText(exam.getEXAM_STARTTIME());
        holder.tv_duration.setText(exam.getEXAM_DURATION());

    }


    @Override
    public int getItemCount() {
        return exams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_exam_name;
        TextView tv_exam_desc;
        TextView tv_questions;
        TextView tv_startTime;
        TextView tv_duration;
        Button btn_examAction;
        ImageView iv_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_exam_name = itemView.findViewById(R.id.tv_examName);
            tv_exam_desc = itemView.findViewById(R.id.tv_examDesc);
            tv_questions = itemView.findViewById(R.id.tv_questions);
            tv_startTime = itemView.findViewById(R.id.tv_starttime);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            btn_examAction = itemView.findViewById(R.id.btn_examAction);
//            iv_icon = itemView.findViewById(R.id.iv_icon);

        }
    }
}


