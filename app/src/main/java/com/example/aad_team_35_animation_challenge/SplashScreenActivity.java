package com.example.aad_team_35_animation_challenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.aad_team_35_animation_challenge.auth.AuthActivity;
import com.example.aad_team_35_animation_challenge.onboarding.OnBoardingActivity;


public class SplashScreenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.splashImage);
        startAnim();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(
                        new Intent(SplashScreenActivity.this, OnBoardingActivity.class)
                );

                finish();
            }
        }, 1500);
    }

    private void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.onboard_anim);
        imageView.startAnimation(animation);


    }
}
