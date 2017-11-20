package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.model.NetworkRepository

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class NetworkFragment : Fragment() {

    companion object {

        private val NETWORK_INDEX_KEY = "NETWORK_INDEX"

        @JvmStatic
        fun newInstance(networkIndex: Int): NetworkFragment {
            val args = Bundle()
            args.putInt(NETWORK_INDEX_KEY, networkIndex)
            val fragment = NetworkFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var mListener: MainActivity
    private lateinit var mCidrListFragment: CidrListFragment

    private lateinit var mNetwork: Network

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        
        mListener = context as MainActivity
        mCidrListFragment = CidrListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNetwork = NetworkRepository.repository.networks[arguments.getInt(NETWORK_INDEX_KEY)]
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_networks, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mCidrListFragment)
                .commit()
    }

    override fun onStart() {
        super.onStart()

        mCidrListFragment.setCidrNotations(mNetwork.cidrNotations.toTypedArray())
    }
}
