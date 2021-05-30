package xyz.archroid.testino.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import xyz.archroid.testino.R;

public class AuthActivity extends AppCompatActivity {


    private LoginFragment loginFragment = new LoginFragment();
    private WelcomeFragment welcomeFragment = new WelcomeFragment();

    private String currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // For status bar transparent color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, welcomeFragment).commit();
        currentFragment = "welcome";

    }


    private final BroadcastReceiver LoginBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_left,
                            R.anim.slide_out_left
                    )
                    .replace(R.id.frameLayout, loginFragment)
                    .addToBackStack("a")
                    .commit();
            currentFragment = "login";

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(AuthActivity.this).registerReceiver(LoginBroadcastReceiver, new IntentFilter("login_switch"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(AuthActivity.this).unregisterReceiver(LoginBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        if (currentFragment.equals("welcome")) {
            finish();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right,
                            R.anim.slide_out_right
                    )
                    .replace(R.id.frameLayout, welcomeFragment).commit();
            currentFragment = "welcome";
        }
    }
}