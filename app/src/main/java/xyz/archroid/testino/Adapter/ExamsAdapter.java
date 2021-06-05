package xyz.archroid.testino.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import xyz.archroid.testino.Data.DeleteExamController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Model.Exam;
import xyz.archroid.testino.R;
import xyz.archroid.testino.ui.ExamActivity;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ViewHolder> {


    private List<Exam> exams;
    private Context context;
    private String userType;

    public ExamsAdapter(List<Exam> exams, Context context) {
        this.exams = exams;
        this.context = context;
        userType = PrefrenceManager.getInstance(context).getUserType();
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

        //convert timestamp to human time
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(exam.getEXAM_STARTTIME()) * 1000);
        String date = DateFormat.format("H:mm - yyyy/MM/dd", calendar).toString();

        Glide.with(context).load(exam.getEXAM_ICON_URL()).into(holder.imageView_icon);
        holder.textView_startTime.setText(date);

        if (userType.equals("admin")) {
            holder.button_1.setOnClickListener(v -> {

                Intent intent = new Intent(context, ExamActivity.class);
                intent.putExtra("id", exam.getEXAM_ID());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            });
            holder.button_2.setOnClickListener(v -> {
                //TODO Exam Answers Activity
                Toast.makeText(context, "exam answers", Toast.LENGTH_SHORT).show();

            });
            holder.button_3.setOnClickListener(v -> {
                TestinoAPI.deleteExamCallback deleteExamCallback = isSuccessful -> {
                    if (isSuccessful) {
                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                    }
                };

                DeleteExamController deleteExamController = new DeleteExamController(deleteExamCallback);
                deleteExamController.start(exam.getEXAM_ID());

            });
        } else {
            holder.button_3.setVisibility(View.GONE);


        }

    }


    @Override
    public int getItemCount() {
        return exams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name, textView_questionCount, textView_startTime;
        MaterialCardView button_1, button_2, button_3;
        ShapeableImageView imageView_button_1, imageView_button_2, imageView_button_3;


        ImageView imageView_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_examName_exam_item);
            textView_questionCount = itemView.findViewById(R.id.textView_questionCount_exam_item);
            textView_startTime = itemView.findViewById(R.id.textView_startTime_exam_item);
            button_1 = itemView.findViewById(R.id.button_1_exam_item);
            button_2 = itemView.findViewById(R.id.button_2_exam_item);
            button_3 = itemView.findViewById(R.id.button_3_exam_item);
            imageView_button_1 = itemView.findViewById(R.id.imageView_button_1);
            imageView_button_2 = itemView.findViewById(R.id.imageView_button_2);
            imageView_button_3 = itemView.findViewById(R.id.imageView_button_3);
            imageView_icon = itemView.findViewById(R.id.imageView_icon_exam_item);

        }
    }
}


