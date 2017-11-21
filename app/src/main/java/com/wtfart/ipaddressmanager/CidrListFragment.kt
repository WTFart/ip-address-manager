package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.fragment_cidr_list.*

import com.wtfart.ipaddressmanager.model.Cidr
import com.wtfart.ipaddressmanager.model.Network

class CidrListFragment : Fragment() {

    companion object {

        private val NETWORK_KEY = "NETWORK"

        @JvmStatic
        fun newInstance() = CidrListFragment()

        @JvmStatic
        fun newInstance(network: Network): CidrListFragment {
            val args = Bundle()
            args.putSerializable(NETWORK_KEY, network)
            val fragment = CidrListFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var mListener: MainActivity

    private lateinit var mCidrNotationsArray: Array<Cidr>
    private lateinit var mCidrNotationsList: List<Cidr>

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCidrNotationsArray = arrayOf()


        mCidrNotationsList = if (arguments != null && arguments.containsKey(NETWORK_KEY)) {
            (arguments.getSerializable(NETWORK_KEY) as Network).cidrNotations
        } else {
            listOf()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_cidr_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (mCidrNotationsList.isNotEmpty()) {
            setCidrNotationsHelper(mCidrNotationsList.toTypedArray())
        }
        listview_cidr_notations.setOnItemClickListener { _, _, i, _ ->
            mListener.switchFragment(
                    DetailFragment.newInstance(
                            if (mCidrNotationsList.isNotEmpty()) {
                                mCidrNotationsList[i]
                            } else {
                                mCidrNotationsArray[i]
                            }
                    )
            )
        }
    }

    fun setCidrNotations(cidrNotations: Array<Cidr>) {
        if (mCidrNotationsList.isEmpty()) {
            setCidrNotationsHelper(cidrNotations)
            mCidrNotationsArray = cidrNotations
        }
    }

    private fun setCidrNotationsHelper(cidrNotations: Array<Cidr>) {
        textview_required_cidr_notations.text = cidrNotations.size.toString()
        listview_cidr_notations.adapter = ArrayAdapter(
                mListener,
                android.R.layout.simple_list_item_1,
                cidrNotations.map { cidrNotation -> cidrNotation.notation }
        )
    }
}
