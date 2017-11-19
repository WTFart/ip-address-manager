package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
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

        button_create_account.setOnClickListener {
           mListener.switchFragment(RegisterFragment.newInstance())
        }

    }
}
