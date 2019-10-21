package com.example.aad_team_35_animation_challenge.onboarding.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.aad_team_35_animation_challenge.onboarding.fragments.OnboardingFragmentOne;
import com.example.aad_team_35_animation_challenge.onboarding.fragments.OnboardingFragmentTwo;

public class OnboardingAdapter extends FragmentPagerAdapter {


    public OnboardingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OnboardingFragmentOne();
            case 1:
                return new OnboardingFragmentTwo();
                default:
                    return null;
        }

    }


    @Override
    public int getCount() {
        return 2;
    }
}
