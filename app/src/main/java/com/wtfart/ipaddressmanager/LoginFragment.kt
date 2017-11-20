package com.wtfart.ipaddressmanager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_login.*

import com.wtfart.ipaddressmanager.util.firebase.Auth
import com.wtfart.ipaddressmanager.util.firebase.Database

class LoginFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    private val mStartMainActivityTime = 2000L

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
            startActivity(Intent(mListener, MainActivity::class.java))
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

        button_login.setOnClickListener {
            val email = edittext_input_email.text.toString()
            val password = edittext_input_password.text.toString()

            try {
                Auth.loginUser(mListener, email, password) {
                    Database.retrieveIpAddresses(Auth.getUid())
                    Database.retrieveIpAddressesRanges()

                    mHandler.postDelayed(mStartMainActivityRunnable, mStartMainActivityTime)
                }
            } catch (e: IllegalArgumentException) {
                Toast.makeText(mListener, getString(R.string.login_error_empty_input), Toast.LENGTH_LONG).show()
            }
        }
        button_create_account.setOnClickListener {
           mListener.switchFragment(RegisterFragment.newInstance())
        }
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mStartMainActivityRunnable)
    }
}
