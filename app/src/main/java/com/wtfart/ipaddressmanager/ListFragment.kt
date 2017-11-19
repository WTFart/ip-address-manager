package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class ListFragment :Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()
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
    ) = inflater?.inflate(R.layout.fragment_cidr_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}