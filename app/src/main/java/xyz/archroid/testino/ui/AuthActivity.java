package xyz.archroid.testino.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import xyz.archroid.testino.R;

public class AuthActivity extends AppCompatActivity {


    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();

    private String currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                )
                .replace(R.id.frameLayout, loginFragment).commit();
        currentFragment = "login";

    }

    private final BroadcastReceiver RegisterBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right,
                            R.anim.slide_out_right
                    )
                    .replace(R.id.frameLayout, registerFragment).commit();
            currentFragment = "register";

        }
    };

    private final BroadcastReceiver LoginBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_left,
                            R.anim.slide_out_left
                    )
                    .replace(R.id.frameLayout, loginFragment).commit();
            currentFragment = "login";

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(AuthActivity.this).registerReceiver(LoginBroadcastReceiver, new IntentFilter("login_switch"));
        LocalBroadcastManager.getInstance(AuthActivity.this).registerReceiver(RegisterBroadcastReceiver, new IntentFilter("register_switch"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(AuthActivity.this).unregisterReceiver(LoginBroadcastReceiver);
        LocalBroadcastManager.getInstance(AuthActivity.this).unregisterReceiver(RegisterBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        if (currentFragment.equals("login")){
            finish();
        } else{
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_left,
                            R.anim.slide_out_left
                    )
                    .replace(R.id.frameLayout, loginFragment).commit();
            currentFragment = "login";
        }
    }
}