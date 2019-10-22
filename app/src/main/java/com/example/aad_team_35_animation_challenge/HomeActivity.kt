package com.example.aad_team_35_animation_challenge

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aad_team_35_animation_challenge.app.QuizMoApp.Companion.logout
import com.example.aad_team_35_animation_challenge.app.QuizMoApp.Companion.animate
import com.example.aad_team_35_animation_challenge.databinding.ActivityHomeBinding
import com.example.aad_team_35_animation_challenge.result.ViewScoresActivity

class HomeActivity : AppCompatActivity() {

    private var mBinding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        animate(mBinding!!.homeImage, R.animator.scale_animation)

        mBinding!!.takeQuiz.setOnClickListener {
            interpolate(mBinding!!.takeQuiz)
            startActivity(Intent(this@HomeActivity, TakeQuizActivity::class.java))
        }

        mBinding!!.viewScores.setOnClickListener {
            interpolate(mBinding!!.viewScores)
            startActivity(Intent(this@HomeActivity, ViewScoresActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> logout(this)
        }
        return true
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
