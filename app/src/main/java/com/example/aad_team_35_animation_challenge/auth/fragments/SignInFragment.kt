package com.example.aad_team_35_animation_challenge.auth.fragments


import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup

import com.example.aad_team_35_animation_challenge.R
import com.example.aad_team_35_animation_challenge.auth.AuthActivity
import com.example.aad_team_35_animation_challenge.auth.AuthActivity.Companion.showMessage
import com.example.aad_team_35_animation_challenge.auth.listeners.OnSignInListener
import com.example.aad_team_35_animation_challenge.databinding.FragmentSignInBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment(), OnSignInListener {

    private lateinit var mBinding: FragmentSignInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentSignInBinding.inflate(inflater)

        return mBinding.root
    }


    override fun signIn() {
        val email = mBinding.edtEmail.text.toString()
        val password = mBinding.edtPassword.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            val authActivity = activity as AuthActivity

            if (authActivity.isEmailValid(email)) {
                authActivity.onAuth(VISIBLE)
                Handler().postDelayed({
                    val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                    val regEmail = preferences.getString(getString(R.string.registered_address),
                            getString(R.string.empty_string))
                    val regPassword = preferences.getString(getString(R.string.registered_password),
                            getString(R.string.empty_string))

                    if (email == regEmail && password == regPassword) {
                        val editor = preferences.edit()
                        editor.putBoolean(getString(R.string.user_logged_in), true)
                        editor.apply()
                        authActivity.onSuccess()
                    } else {
                        authActivity.onFailure()
                        showMessage(mBinding.edtEmail, getString(R.string.invalid_credentials))
                    }
                }, 750L)
            } else
                showMessage(mBinding.edtEmail, getString(R.string.error_invalid_address))
        } else
            showMessage(mBinding.edtEmail, getString(R.string.error_empty_field))

    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignInFragment.
         */
        fun newInstance(): SignInFragment {

            return SignInFragment()
        }
    }

}
