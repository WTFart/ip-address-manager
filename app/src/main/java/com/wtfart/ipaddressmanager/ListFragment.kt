package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_list.*

import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.model.NetworkRepository

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class ListFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()
    }

    private val mRefreshTime = 1000L

    private lateinit var mListener: MainActivity

    private lateinit var mHandler: Handler

    private lateinit var mRefreshRunnable: Runnable

    private lateinit var mNetworks: MutableList<Network>

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHandler = Handler()

        mNetworks = NetworkRepository.repository.networks

        mListener.setActionBarTitle(getString(R.string.app_name))
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(
                mListener,
                getString(R.string.list_login_successful),
                Toast.LENGTH_LONG
        ).show()

        mRefreshRunnable = Runnable {
            listview_networks.adapter = ArrayAdapter(
                    mListener,
                    android.R.layout.simple_list_item_1,
                    mNetworks.map { Network -> Network.name }
            )
            layout_swipe_refresh.isRefreshing = false
        }

        mListener.setActionBarTitle(getString(R.string.app_name))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layout_swipe_refresh.setOnRefreshListener {
            mHandler.postDelayed(mRefreshRunnable, mRefreshTime)
        }
        layout_swipe_refresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        )
        listview_networks.adapter = ArrayAdapter(
                mListener,
                android.R.layout.simple_list_item_1,
                mNetworks.map { Network -> Network.name }
        )
        listview_networks.setOnItemClickListener { _, _, i, _->
            mListener.switchFragment(NetworkFragment.newInstance(mNetworks[i]))
        }
        fab_register.setOnClickListener {
            mListener.switchFragment(CalculatorFragment.newInstance())
        }
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mRefreshRunnable)
    }
}
