package com.example.aad_team_35_animation_challenge.auth

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.aad_team_35_animation_challenge.R
import com.example.aad_team_35_animation_challenge.auth.customs.FlexibleLayout.Companion.ORDER_SIGN_IN_STATE
import com.example.aad_team_35_animation_challenge.auth.customs.FlexibleLayout.Companion.ORDER_SIGN_UP_STATE
import com.example.aad_team_35_animation_challenge.auth.fragments.SignInFragment
import com.example.aad_team_35_animation_challenge.auth.fragments.SignUpFragment
import com.example.aad_team_35_animation_challenge.auth.listeners.OnAuthListener
import com.example.aad_team_35_animation_challenge.auth.listeners.OnButtonSwitchedListener
import com.example.aad_team_35_animation_challenge.databinding.ActivityAuthBinding
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity(), OnAuthListener {

    private var mBinding: ActivityAuthBinding? = null
    private var isLogin = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        val topSignInFragment = SignInFragment.newInstance()
        val topSignUpFragment = SignUpFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.sign_in_fragment, topSignInFragment)
                .replace(R.id.sign_up_fragment, topSignUpFragment)
                .commit()

        mBinding!!.signInFragment.rotation = -90f

        mBinding!!.button.setOnSignUpListener(topSignUpFragment)
        mBinding!!.button.setOnLoginListener(topSignInFragment)

        mBinding!!.button.setOnButtonSwitched(object : OnButtonSwitchedListener {
            override fun onButtonSwitched(isLogin: Boolean) {
                mBinding!!.root
                        .setBackgroundColor(ContextCompat.getColor(
                                this@AuthActivity,
                                if (isLogin) R.color.colorPrimary else R.color.colorAccent))
            }
        })

        mBinding!!.signInFragment.visibility = INVISIBLE
    }

    override fun onResume() {
        super.onResume()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        if (preferences.getBoolean(getString(R.string.user_logged_in), false))
            onSuccess()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        mBinding!!.signInFragment.pivotX = (mBinding!!.signInFragment.width / 2).toFloat()
        mBinding!!.signInFragment.pivotY = mBinding!!.signInFragment.height.toFloat()
        mBinding!!.signUpFragment.pivotX = (mBinding!!.signUpFragment.width / 2).toFloat()
        mBinding!!.signUpFragment.pivotY = mBinding!!.signUpFragment.height.toFloat()
    }

    fun switchFragment(v: View) {
        if (isLogin) {
            mBinding!!.signInFragment.visibility = VISIBLE
            mBinding!!.signInFragment.animate().rotation(0f).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            mBinding!!.signUpFragment.visibility = INVISIBLE
                            mBinding!!.signUpFragment.rotation = 90f
                            mBinding!!.wrapper.setDrawOrder(ORDER_SIGN_IN_STATE)
                        }
                    })
        } else {
            mBinding!!.signUpFragment.visibility = VISIBLE
            mBinding!!.signUpFragment.animate().rotation(0f).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            mBinding!!.signInFragment.visibility = INVISIBLE
                            mBinding!!.signInFragment.rotation = -90f
                            mBinding!!.wrapper.setDrawOrder(ORDER_SIGN_UP_STATE)
                        }
                    })
        }

        isLogin = !isLogin
        mBinding!!.button.startAnimation()
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onAuth(visibility: Int) {
        mBinding!!.progress.visibility = visibility
    }

    override fun onSuccess() {
        //  TODO: Uncomment the code below and replace the SplashScreenActivity with
        //   the appropriate activity

//        startActivity(Intent(this, SplashScreenActivity::class.java))

        finish()
    }

    override fun onFailure() {
        mBinding!!.progress.visibility = GONE
    }

    companion object {

        fun showMessage(v: View, msg: String) {
            Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
