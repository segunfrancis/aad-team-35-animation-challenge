package com.example.aad_team_35_animation_challenge.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.example.aad_team_35_animation_challenge.R
import com.example.aad_team_35_animation_challenge.auth.AuthActivity

class QuizMoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        fun logout(activity: Activity) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = prefs.edit()

            editor.putBoolean(context?.getString(R.string.user_logged_in), false)
            editor.apply()
            activity?.startActivity(Intent(activity, AuthActivity::class.java))
            activity.finish()
        }
    }
}