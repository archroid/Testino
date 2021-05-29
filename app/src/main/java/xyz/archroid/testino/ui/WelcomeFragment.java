package xyz.archroid.testino.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.R;


public class WelcomeFragment extends Fragment {

    MaterialCardView cardView_admin, cardView_student;
    Button btn_welcome;

    String userType;


    public WelcomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);


//        UserType cardViews init
        cardView_student = view.findViewById(R.id.cardView_student_fragment_welcome);
        cardView_admin = view.findViewById(R.id.cardView_admin_fragment_welcome);
        cardView_student.setOnClickListener(v -> {
            cardView_student.setActivated(true);
            cardView_student.setStrokeColor(getResources().getColor(R.color.primary));
            cardView_admin.setActivated(false);
            cardView_admin.setStrokeColor(getResources().getColor(R.color.stork_color));
            userType = "student";
        });
        cardView_admin.setOnClickListener(v -> {
            cardView_student.setActivated(false);
            cardView_admin.setActivated(true);
            cardView_student.setStrokeColor(getResources().getColor(R.color.stork_color));
            cardView_admin.setStrokeColor(getResources().getColor(R.color.primary));
            userType = "admin";
        });
        cardView_admin.callOnClick();


//        Welcome Button init
        btn_welcome = view.findViewById(R.id.btn_welcome);
        btn_welcome.setOnClickListener(v -> {
            PrefrenceManager.getInstance(getContext()).putUserType(userType);
            LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("login_switch"));
        });


        return view;
    }
}