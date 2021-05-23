package xyz.archroid.testino.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import xyz.archroid.testino.Data.RegisterController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Helper.SnackBarHelper;
import xyz.archroid.testino.R;

public class RegisterFragment extends Fragment {


    public RegisterFragment() {
    }

    private TestinoAPI.registerCallback registerCallback;

    private TextInputEditText et_email, et_username, et_password;
    private Button btn_register, btn_switch_login;

    private String username;

    private CoordinatorLayout coordinatorLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        et_email = view.findViewById(R.id.et_email);
        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_pass);

        btn_register = view.findViewById(R.id.btn_register);
        btn_switch_login = view.findViewById(R.id.btn_switch_login);

        coordinatorLayout = view.findViewById(R.id.coordinator);

        btn_register.setOnClickListener(v -> {

            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            username = et_username.getText().toString();

            // Check if data format is ok
            if (
                    email.isEmpty() ||
                            email.lastIndexOf('@') <= 0 ||
                            !email.contains(".") ||
                            email.contains(" ") ||
                            email.lastIndexOf('.') < email.lastIndexOf('@') ||
                            email.split("@").length > 2 ||
                            email.isEmpty()
            ) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.error_email));

            } else if (username.isEmpty()) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.error_emptyField));
            } else if (password.isEmpty() || password.length() < 10 || password.contains(" ") || password.contains(".")) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.error_emptyField));
            } else {
                PrefrenceManager.getInstance(getContext()).putUsername(Integer.parseInt(username));
                PrefrenceManager.getInstance(getContext()).putUserType(null);
                RegisterController registerController = new RegisterController(registerCallback);
                registerController.start(
                        username,
                        password,
                        email
                );


            }
        });

        btn_switch_login.setOnClickListener(v -> {
            LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("login_switch"));

        });


        registerCallback = new TestinoAPI.registerCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, String token, String error) {
                if (isSuccessful) {
                    PrefrenceManager.getInstance(getContext()).putToken(token);
                    PrefrenceManager.getInstance(getContext()).putUsername(Integer.parseInt(username));
                    PrefrenceManager.getInstance(getContext()).putUserType("admin");
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                } else {
                    SnackBarHelper.alert(getContext(), coordinatorLayout, error.trim());
                }
            }

            @Override
            public void onFailure(String cause) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, cause.trim());

            }
        };

        return view;
    }

}