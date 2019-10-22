package com.example.aad_team_35_animation_challenge

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aad_team_35_animation_challenge.app.QuizMoApp.Companion.animate

import com.example.aad_team_35_animation_challenge.databinding.ActivitySplashScreenBinding
import com.example.aad_team_35_animation_challenge.onboarding.OnBoardingActivity


class SplashScreenActivity : AppCompatActivity() {

    private var mBinding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        animate(mBinding!!.txtAppName, R.animator.alpha)
        animate(mBinding!!.imgSplash, R.animator.scale_animation)

        Handler().postDelayed({
            startActivity(
                    Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            )

            finish()
        }, 5000)
    }
}
