package xyz.archroid.testino.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import xyz.archroid.testino.Data.LoginController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Helper.SnackBarHelper;
import xyz.archroid.testino.R;

public class LoginFragment extends Fragment {

    private TextInputEditText et_username, et_password;
    private Button btn_login;
    private AppCompatButton btn_switch_register;

    private RadioButton radio_student;

    private String userType;

    private TestinoAPI.LoginCallback loginCallback;

    private CoordinatorLayout coordinatorLayout;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_pass);

        btn_login = view.findViewById(R.id.btn_login);
        btn_switch_register = view.findViewById(R.id.btn_switch_register);

        radio_student = view.findViewById(R.id.radioStudent);

        coordinatorLayout = view.findViewById(R.id.coordinator);

        btn_login.setOnClickListener(v -> {

            if (radio_student.isChecked()) {
                userType = "student";
            } else {
                userType = "teacher";
            }


            if (et_username.getText().toString().isEmpty()) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.error_username));

            }
            if (et_password.getText().toString().isEmpty()) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.error_password));
            } else {
                LoginController loginController = new LoginController(loginCallback);
                loginController.start(et_username.getText().toString(), et_password.getText().toString(), "admin");
            }

        });

        loginCallback = new TestinoAPI.LoginCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, String token, String error) {
                if (isSuccessful) {
                    PrefrenceManager.getInstance(getContext()).putToken(token);
                    PrefrenceManager.getInstance(getContext()).putUsername(Integer.parseInt(et_username.getText().toString().trim()));
                    PrefrenceManager.getInstance(getContext()).putUserType("admin");
                    startActivity(new Intent(getContext(), DashboardActivity.class));
                    getActivity().finish();
                } else {
                    SnackBarHelper.alert(getContext(),coordinatorLayout,error.trim());
                }
            }

            @Override
            public void onFailure(String cause) {
                SnackBarHelper.alert(getContext(),coordinatorLayout,cause.trim());
            }
        };

        btn_switch_register.setOnClickListener(v -> {
            LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("register_switch"));
        });

        return view;
    }
}