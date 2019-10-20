package com.example.aad_team_35_animation_challenge

import android.animation.AnimatorInflater
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.databinding.DataBindingUtil
import com.example.aad_team_35_animation_challenge.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var mBinding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        scaleAnimation(mBinding!!.homeImage)

        mBinding!!.takeQuiz.setOnClickListener {
            interpolate(mBinding!!.takeQuiz)
            startActivity(Intent(this@HomeActivity, SplashScreenActivity::class.java))
        }

        mBinding!!.viewCourse.setOnClickListener {
            interpolate(mBinding!!.viewCourse)
            //startActivity(Intent(this@HomeActivity, SplashScreenActivity::class.java))
        }
    }

    private fun scaleAnimation(view: View) {
        val scale = AnimatorInflater.loadAnimator(view.context, R.animator.scale_animation)
        scale.apply {
            setTarget(view)
            interpolator = BounceInterpolator()
            start()
        }
    }

    private fun interpolate(view: View) {
        val vpa = view.animate()
        vpa.apply {
            duration = 50
            scaleX(1.2f)
            scaleY(1.2f)
            interpolator = BounceInterpolator()
            start()
        }
    }
}
