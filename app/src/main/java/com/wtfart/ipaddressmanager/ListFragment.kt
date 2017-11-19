package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.model.NetworkRepository

import kotlinx.android.synthetic.main.fragment_list.*

/**
 * Created by oatThanut on 19/11/2017 AD.
 */
class ListFragment :Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()
    }

    private lateinit var mListener: MainActivity
    private lateinit var mSavedList: MutableList<Network>
    private lateinit var mCidrListFragment: CidrListFragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
        mCidrListFragment = CidrListFragment.newInstance()
        mSavedList = NetworkRepository.repository.networks

    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listview_list.adapter = ArrayAdapter(
                mListener,
                android.R.layout.simple_list_item_1,
                mSavedList.map { Network -> Network.name }
        )

        fab.setOnClickListener {
            mListener.switchFragment(CalculatorFragment.newInstance())
        }

        listview_list.setOnItemClickListener { _, _, i, _->
//            mListener.switchFragment(mCidrListFragment)
        }
    }
}
