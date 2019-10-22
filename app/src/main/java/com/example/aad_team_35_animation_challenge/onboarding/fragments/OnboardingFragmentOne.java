package com.example.aad_team_35_animation_challenge.onboarding.fragments;


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


public class OnboardingFragmentOne extends Fragment {
    TextView nextTV;
    ViewPager viewPager;
    ImageView bulb2, image1;

    public OnboardingFragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_fragment_one, container, false);
        nextTV = view.findViewById(R.id.textViewNext);
        bulb2 = view.findViewById(R.id.bulb2);
        viewPager = getActivity().findViewById(R.id.viewPager);
        image1 = view.findViewById(R.id.imageView);


        nextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        startAnim();
        startImageAnim();

        return view;
    }

    private void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.onboard_anim);
        nextTV.startAnimation(animation);


    }

    private void startImageAnim() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce_bulb);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.image_anim);
        bulb2.startAnimation(animation);
        image1.startAnimation(animation1);


    }

}
