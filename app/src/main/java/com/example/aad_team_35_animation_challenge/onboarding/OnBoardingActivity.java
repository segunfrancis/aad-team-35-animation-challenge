package com.example.aad_team_35_animation_challenge.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.aad_team_35_animation_challenge.R;
import com.example.aad_team_35_animation_challenge.onboarding.Adapter.OnboardingAdapter;

public class OnBoardingActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);


        viewPager = findViewById(R.id.viewPager);

        OnboardingAdapter adapter = new OnboardingAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
