package com.example.belajarmengajarreal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.example.belajarmengajarreal.R;
import com.example.belajarmengajarreal.utils.FirebaseClient;

public class SplashScreen extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Logo animation
        ImageView logo = findViewById(R.id.logo);
        logo.setAlpha(0f); logo.setScaleX(0.9f); logo.setScaleY(0.9f);
        logo.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(1000)
                .start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isLoggedIn = FirebaseClient.user() != null;

                if (isLoggedIn) {
                    goToMainActivity();
                } else {
                    goToLoginActivity();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
