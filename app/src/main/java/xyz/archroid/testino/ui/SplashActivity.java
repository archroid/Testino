package xyz.archroid.testino.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import xyz.archroid.testino.Helper.PrefrenceManager;
import xyz.archroid.testino.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(1200, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if (PrefrenceManager.getInstance(SplashActivity.this).getToken() == null) {
                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));
                    finish();
                }
            }
        }.start();
    }
}