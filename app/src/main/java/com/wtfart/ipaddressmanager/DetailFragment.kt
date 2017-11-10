package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_detail.*

import com.wtfart.ipaddressmanager.model.Cidr
import com.wtfart.ipaddressmanager.util.IpConverter

class DetailFragment : Fragment() {

    companion object {

        private val CIDR_KEY = "CIDR"

        @JvmStatic
        fun newInstance(cidr: Cidr): DetailFragment {
            val args = Bundle()
            args.putSerializable(CIDR_KEY, cidr)
            val fragment = DetailFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var mListener: MainActivity

    private lateinit var mCidr: Cidr

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCidr = arguments.getSerializable(CIDR_KEY) as Cidr
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textview_notation.text = mCidr.notation
        textview_netmask.text = IpConverter.toIpAddress(mCidr.netmask)
        textview_wildcard_mask.text = IpConverter.toIpAddress(mCidr.wildcardMask)
        val (initialIpAddress, lastIpAddress) = mCidr.ipAddressRange
        textview_ip_address_range.text = if (initialIpAddress == lastIpAddress) {
            IpConverter.toIpAddress(initialIpAddress)
        } else {
            getString(
                    R.string.detail_format_ip_address_range,
                    IpConverter.toIpAddress(initialIpAddress),
                    IpConverter.toIpAddress(lastIpAddress)
            )
        }
    }
}
