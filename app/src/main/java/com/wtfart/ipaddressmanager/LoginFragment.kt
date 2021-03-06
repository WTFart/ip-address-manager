package com.wtfart.ipaddressmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_login.*

import com.wtfart.ipaddressmanager.util.firebase.Auth
import com.wtfart.ipaddressmanager.util.firebase.Database

class LoginFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    private val START_MAIN_ACTIVITY_DELAY = 4000L

    private lateinit var mListener: AuthActivity

    private lateinit var mHandler: Handler
    private lateinit var mStartMainActivityRunnable: Runnable

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as AuthActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler = Handler()

        mStartMainActivityRunnable = Runnable {
            mListener.startActivity(Intent(mListener, MainActivity::class.java))
            mListener.dismissProgressDialog()
            mListener.finish()
            mListener.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_login, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        edittext_input_password.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }
        button_login.setOnClickListener {
            login()
        }
        button_create_account.setOnClickListener {
            edittext_input_email.setText("")
            edittext_input_password.setText("")
            mListener.switchFragment(RegistrationFragment.newInstance())
        }
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mStartMainActivityRunnable)
    }

    private fun login() {
        val email = edittext_input_email.text.toString()
        val password = edittext_input_password.text.toString()

        mListener.showProgressDialog()
        try {
            Auth.loginUser(mListener, email, password) { task ->
                if (task.isSuccessful) {
                    Database.retrieveDatabase(Auth.getUid())

                    mHandler.postDelayed(mStartMainActivityRunnable, START_MAIN_ACTIVITY_DELAY)
                } else {
                    mListener.dismissProgressDialog()
                    Toast.makeText(
                            mListener,
                            getString(R.string.login_failed),
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
    }
}
