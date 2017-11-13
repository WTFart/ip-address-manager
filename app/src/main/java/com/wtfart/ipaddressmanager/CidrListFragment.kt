package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.fragment_cidr_list.*

import com.wtfart.ipaddressmanager.model.Cidr

class CidrListFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = CidrListFragment()
    }

    private lateinit var mListener: MainActivity

    private lateinit var mCidrNotations: Array<Cidr>

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_cidr_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listview_cidr_notations.setOnItemClickListener { _, _, i, _ ->
            mListener.switchFragment(DetailFragment.newInstance(mCidrNotations[i]))
        }
    }

    fun setCidrNotations(cidrNotations: Array<Cidr>) {
        textview_required_cidr_notations.text = cidrNotations.size.toString()
        listview_cidr_notations.adapter = ArrayAdapter(
                mListener,
                android.R.layout.simple_list_item_1,
                cidrNotations.map { cidrNotation -> cidrNotation.notation }
        )
        mCidrNotations = cidrNotations
    }
}
