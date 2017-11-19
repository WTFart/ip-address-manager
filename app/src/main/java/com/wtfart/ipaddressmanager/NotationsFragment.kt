package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class NotationsFragment: Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = NotationsFragment()
    }

    private lateinit var mListener: MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        
        mListener = context as MainActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_notations, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
