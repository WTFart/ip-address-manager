package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wtfart.ipaddressmanager.model.NetworkRepository

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class NotationsFragment: Fragment() {

    companion object {

        private val NOTATION_KEY = "string"

        @JvmStatic
        fun newInstance(i: Int): NotationsFragment {
            val args = Bundle()
            args.putInt(NOTATION_KEY, i)
            val fragment = NotationsFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var mListener: MainActivity
    private lateinit var mCidrListFragment: CidrListFragment
    private var mNetworkIndex: Int = -1

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        
        mListener = context as MainActivity
        mCidrListFragment = CidrListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNetworkIndex = arguments.getInt(NOTATION_KEY)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_notations, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mCidrListFragment)
                .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mCidrListFragment.setCidrNotations(NetworkRepository.repository.networks[mNetworkIndex].cidrNotations.toTypedArray())
    }
}
