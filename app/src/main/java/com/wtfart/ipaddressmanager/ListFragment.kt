package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter

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

    private lateinit var mListener: MainActivity

    private lateinit var mNetworks: MutableList<Network>

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNetworks = NetworkRepository.repository.networks
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listview_networks.adapter = ArrayAdapter(
                mListener,
                android.R.layout.simple_list_item_1,
                mNetworks.map { Network -> Network.name }
        )
        listview_networks.setOnItemClickListener { _, _, i, _->
            mListener.switchFragment(NetworkFragment.newInstance(i))
        }
        fab_register.setOnClickListener {
            mListener.switchFragment(CalculatorFragment.newInstance())
        }
    }
}
