package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_register.*

import com.wtfart.ipaddressmanager.util.firebase.Auth

class RegisterFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = RegisterFragment()
    }

    private lateinit var mListener: AuthActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as AuthActivity
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_register, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_create_account.setOnClickListener {
            val email = edittext_input_email.text.toString()
            val password = edittext_input_password.text.toString()
            val passwordConfirmation = edittext_input_confirm_password.text.toString()

            if (password == passwordConfirmation) {
                try {
                    Auth.registerUser(mListener, email, password) {
                        Toast.makeText(mListener, getString(R.string.register_success),Toast.LENGTH_LONG).show()
                        mListener.onBackPressed()
                    }
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(mListener, getString(R.string.error_input), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(mListener, getString(R.string.register_error), Toast.LENGTH_LONG).show()
            }
        }
    }
}
