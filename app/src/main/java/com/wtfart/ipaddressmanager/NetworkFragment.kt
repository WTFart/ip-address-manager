package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_network.*

import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.util.firebase.Auth
import com.wtfart.ipaddressmanager.util.firebase.Database

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

    private val mDelayTime = 1000L

    private lateinit var mListener: MainActivity
    private lateinit var mCidrListFragment: CidrListFragment

    private lateinit var mHandler: Handler

    private lateinit var mDelayRunnable: Runnable

    private lateinit var mNetwork: Network

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        
        mListener = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler = Handler()

        mDelayRunnable = Runnable {
            mListener.dismissProgressDialog()
            mListener.onBackPressed()
            Toast.makeText(
                    mListener,
                    getString(R.string.network_delete_successful),
                    Toast.LENGTH_LONG
            ).show()
        }

        mNetwork = arguments.getSerializable(NETWORK_INDEX_KEY) as Network

        mCidrListFragment = CidrListFragment.newInstance(mNetwork)

        mListener.setActionBarTitle(mNetwork.name)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_network, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mListener.setActionBarTitle(mNetwork.name)

        childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mCidrListFragment)
                .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_delete.setOnClickListener {
            mListener.showProgressDialog()

            Database.revokeIpAddress(Auth.getUid(), mNetwork.id)

            mHandler.postDelayed(mDelayRunnable, mDelayTime)
        }
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mDelayRunnable)
    }
}
