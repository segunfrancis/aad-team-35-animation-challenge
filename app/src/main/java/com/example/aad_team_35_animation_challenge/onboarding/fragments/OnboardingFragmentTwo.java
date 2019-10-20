package com.example.aad_team_35_animation_challenge.onboarding.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aad_team_35_animation_challenge.R;
import com.example.aad_team_35_animation_challenge.auth.AuthActivity;
import com.example.aad_team_35_animation_challenge.onboarding.OnBoardingActivity;


public class OnboardingFragmentTwo extends Fragment {
    TextView getStarted;
    TextView back;
    ViewPager viewPager;
    ImageView bulb, Image2;


    public OnboardingFragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_fragment_two, container, false);
        getStarted = view.findViewById(R.id.textViewGetStarted);
        back = view.findViewById(R.id.textViewBack);
        bulb = view.findViewById(R.id.bulb);
        Image2 = view.findViewById(R.id.imageView2);


        viewPager = getActivity().findViewById(R.id.viewPager);


        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        startAnim();
        startImageAnim();

        return view;
    }

    private void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.onboard_anim);
        getStarted.startAnimation(animation);
        back.startAnimation(animation);


    }

    private void startImageAnim() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.image_anim);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.image_anim);
        bulb.startAnimation(animation);

        Image2.startAnimation(animation1);


    }

}
