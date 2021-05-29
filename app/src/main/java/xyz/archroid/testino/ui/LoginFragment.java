package xyz.archroid.testino.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import xyz.archroid.testino.Data.LoginController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.Helper.SnackBarHelper;
import xyz.archroid.testino.R;

public class LoginFragment extends Fragment {

    private TextInputEditText editText_username, editText_password;
    private Button btn_login;

    private String userType;

    private TestinoAPI.LoginCallback loginCallback;

    private CoordinatorLayout coordinatorLayout;


    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);


        editText_username = view.findViewById(R.id.editText_username_login_fragment);
        editText_password = view.findViewById(R.id.editText_password_login_fragment);

        btn_login = view.findViewById(R.id.btn_login);

        coordinatorLayout = view.findViewById(R.id.coordinator);


        btn_login.setOnClickListener(v -> {

            userType = PrefrenceManager.getInstance(getContext()).getUserType();

            if (editText_username.getText().toString().isEmpty()) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.login_error));
            } else if (editText_password.getText().toString().isEmpty()) {
                SnackBarHelper.alert(getContext(), coordinatorLayout, getString(R.string.login_error));
            } else {
                LoginController loginController = new LoginController(loginCallback);
                loginController.start(editText_username.getText().toString(), editText_password.getText().toString(), userType);
            }

        });

        loginCallback = new TestinoAPI.LoginCallback() {
            @Override
            public void onResponse(Boolean isSuccessful, String token, String error) {
                if (isSuccessful) {
                    PrefrenceManager.getInstance(getContext()).putToken(token);
                    PrefrenceManager.getInstance(getContext()).putUsername(Integer.parseInt(editText_username.getText().toString().trim()));
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