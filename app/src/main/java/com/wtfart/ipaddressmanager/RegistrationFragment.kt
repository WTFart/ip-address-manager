package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_registration.*

import com.wtfart.ipaddressmanager.util.firebase.Auth

class RegistrationFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

    private val BACK_TO_LOGIN_DELAY = 2000L

    private lateinit var mListener: AuthActivity

    private lateinit var mHandler: Handler
    private lateinit var mBackToLoginRunnable: Runnable

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as AuthActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler = Handler()

        mBackToLoginRunnable = Runnable {
            mListener.dismissProgressDialog()
            mListener.onBackPressed()
            Toast.makeText(
                    mListener,
                    getString(R.string.shared_registration_successful),
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_registration, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        edittext_input_password_confirmation.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE) {
                register()
            }
            false
        }
        button_create_account.setOnClickListener {
            register()
        }
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mBackToLoginRunnable)
    }

    private fun register() {
        val password = edittext_input_password.text.toString()
        val passwordConfirmation = edittext_input_password_confirmation.text.toString()

        if (password == passwordConfirmation) {
            val email = edittext_input_email.text.toString()

            mListener.showProgressDialog()
            try {
                Auth.registerUser(mListener, email, password) { task ->
                    if (task.isSuccessful) {
                        mHandler.postDelayed(mBackToLoginRunnable, BACK_TO_LOGIN_DELAY)
                    } else {
                        mListener.dismissProgressDialog()
                        Toast.makeText(
                                mListener,
                                getString(R.string.registration_failed),
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: IllegalArgumentException) {
                mListener.dismissProgressDialog()
                Toast.makeText(
                        mListener,
                        getString(R.string.shared_error_empty_input),
                        Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                    mListener,
                    getString(R.string.registration_error_password_mismatch),
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}
