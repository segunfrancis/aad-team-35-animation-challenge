package com.example.aad_team_35_animation_challenge.auth.fragments


import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aad_team_35_animation_challenge.R
import com.example.aad_team_35_animation_challenge.auth.AuthActivity
import com.example.aad_team_35_animation_challenge.auth.AuthActivity.Companion.showMessage
import com.example.aad_team_35_animation_challenge.auth.listeners.OnSignUpListener
import com.example.aad_team_35_animation_challenge.databinding.FragmentSignUpBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment(), OnSignUpListener {

    private lateinit var mBinding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentSignUpBinding.inflate(inflater)

        return mBinding.root
    }

    override fun signUp() {
        val email = mBinding.edtEmail.text.toString()
        val name = mBinding.edtFullName.text.toString()
        val password = mBinding.edtPassword.text.toString()
        val cPassword = mBinding.edtPasswordConfirm.text.toString()

        if (name.isNotEmpty()
                && email.isNotEmpty()
                && password.isNotEmpty()
                && cPassword.isNotEmpty()) {
            val authActivity = (activity as AuthActivity)
            if (authActivity.isEmailValid(email)) {
                if (password == cPassword) {
                    authActivity.onAuth(VISIBLE)

                    Handler().postDelayed({
                        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                        val regEmail = preferences.getString(getString(R.string.registered_address),
                                getString(R.string.empty_string))
                        if (email != regEmail) {

                            val editor = preferences.edit()
                            editor.putString(getString(R.string.registered_address), email)
                            editor.putString(getString(R.string.registered_name), name)
                            editor.putString(getString(R.string.registered_password), password)
                            editor.putBoolean(getString(R.string.user_logged_in), true)
                            editor.apply()

                            authActivity.onSuccess()
                        } else {
                            authActivity.onFailure()
                            showMessage(mBinding.edtEmail, getString(R.string.error_address_taken))
                        }
                    }, 750L)
                } else
                    showMessage(mBinding.edtPassword, getString(R.string.password_mismatched))
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
         * @return A new instance of fragment SignUpFragment.
         */
        fun newInstance(): SignUpFragment {

            return SignUpFragment()
        }
    }

}
