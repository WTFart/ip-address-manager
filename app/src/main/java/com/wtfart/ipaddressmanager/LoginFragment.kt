package com.wtfart.ipaddressmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_login.*

import com.wtfart.ipaddressmanager.util.firebase.Auth

class LoginFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    private lateinit var mListener: AuthActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as AuthActivity
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_login, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_login.setOnClickListener {
            try {
                val email = edittext_input_email.text.toString()
                val password = edittext_input_password.text.toString()

                Auth.logInUser(mListener, email, password) {
                    startActivity(Intent(mListener, MainActivity::class.java))
                    mListener.finish()
                    mListener.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            } catch (e: IllegalArgumentException) {
                Toast.makeText(mListener, getString(R.string.login_error_empty_input), Toast.LENGTH_LONG).show()
            }
        }
        button_create_account.setOnClickListener {
           mListener.switchFragment(RegisterFragment.newInstance())
        }

    }
}
