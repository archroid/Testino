package xyz.archroid.testino.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import xyz.archroid.testino.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment = new HomeFragment();
    private QuestionBankFragment questionBankFragment = new QuestionBankFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, homeFragment).commit();
                    return true;
                case R.id.students:
                    return true;
                case R.id.questions:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameLayout, questionBankFragment).commit();
                    return true;
                case R.id.settings:
                    return true;

            }
            return false;
        });


    }
}