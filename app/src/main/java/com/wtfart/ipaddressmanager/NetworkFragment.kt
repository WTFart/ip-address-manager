package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wtfart.ipaddressmanager.model.Network
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class NetworkFragment : Fragment() {

    companion object {

        private val NETWORK_INDEX_KEY = "NETWORK_INDEX"

        @JvmStatic
        fun newInstance(network: Network): NetworkFragment {
            val args = Bundle()
            args.putSerializable(NETWORK_INDEX_KEY, network)
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNetwork = arguments.getSerializable(NETWORK_INDEX_KEY) as Network

        mCidrListFragment = CidrListFragment.newInstance(mNetwork)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_networks, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mListener.action_bar.title = mNetwork.name

        childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mCidrListFragment)
                .commit()
    }
}
