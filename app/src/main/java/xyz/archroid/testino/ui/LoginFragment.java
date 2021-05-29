package xyz.archroid.testino.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import xyz.archroid.testino.Data.LoginController;
import xyz.archroid.testino.Data.TestinoAPI;
import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.R;

public class LoginFragment extends Fragment {

    private TextInputEditText editText_username, editText_password;
    private Button btn_login;

    private String userType;

    private TestinoAPI.LoginCallback loginCallback;


    private RelativeLayout layout_error;
    private TextView textView_error;


    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editText_username = view.findViewById(R.id.editText_username_login_fragment);
        editText_password = view.findViewById(R.id.editText_password_login_fragment);

        layout_error = view.findViewById(R.id.layout_error);
        textView_error = view.findViewById(R.id.textView_error);

        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(v -> {

            userType = PrefrenceManager.getInstance(getContext()).getUserType();

            if (editText_username.getText().toString().isEmpty()) {
                textView_error.setText(R.string.login_error);
                layout_error.setVisibility(View.VISIBLE);

            } else if (editText_password.getText().toString().isEmpty()) {
                textView_error.setText(R.string.login_error);
                layout_error.setVisibility(View.VISIBLE);

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
                    textView_error.setText(error.trim());
                    layout_error.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String cause) {
                textView_error.setText(cause.trim());
                layout_error.setVisibility(View.VISIBLE);
            }
        };


        return view;
    }
}