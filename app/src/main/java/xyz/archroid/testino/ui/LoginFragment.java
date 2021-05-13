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

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import xyz.archroid.testino.Helper.SnackBarHelper;
import xyz.archroid.testino.R;

public class LoginFragment extends Fragment {

    private TextInputEditText et_studentCode, et_password;
    private Button btn_login;
    private AppCompatButton  btn_switch_register;

//    private PorsooApi.LoginCallback loginCallback;

    private CoordinatorLayout coordinatorLayout;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        et_studentCode = view.findViewById(R.id.et_studentCode);
        et_password = view.findViewById(R.id.et_pass);

        btn_login = view.findViewById(R.id.btn_login);
        btn_switch_register = view.findViewById(R.id.btn_switch_register);

        coordinatorLayout = view.findViewById(R.id.coordinator);

        btn_login.setOnClickListener(v -> {

            if (et_studentCode.getText().toString().isEmpty()) {
                et_studentCode.setError(getResources().getString(R.string.erremail));
                SnackBarHelper.alert(getContext(),coordinatorLayout,getString(R.string.error));

            }
            if (et_password.getText().toString().isEmpty()) {
                et_password.setError(getResources().getString(R.string.errpass));
                SnackBarHelper.alert(getContext(),coordinatorLayout,getString(R.string.error));
            } else {

//                LoginController loginController = new LoginController(loginCallback);
//                loginController.start(et_email.getText().toString(), et_password.getText().toString());
            }

        });

//        loginCallback = new PorsooApi.LoginCallback() {
//            @Override
//            public void onResponse(Boolean isSuccessful, String token, String error) {
//                if (isSuccessful) {
//                    PreferenceManager.getInstance(getContext()).putAccessToken(token);
//                    startActivity(new Intent(getContext(), MainActivity.class));
//                    getActivity().finish();
//                } else {
//                    SnackBarHelper.alert(getContext(),coordinatorLayout,error.trim());
//                }
//            }
//
//            @Override
//            public void onFailure(String cause) {
//                SnackBarHelper.alert(getContext(),coordinatorLayout,cause.trim());
//            }
//        };


        btn_switch_register.setOnClickListener(v -> {
            LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(new Intent("register_switch"));
        });

        return view;
    }
}