package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_detail.*

import com.wtfart.ipaddressmanager.model.Cidr
import com.wtfart.ipaddressmanager.util.IpConverter
import kotlinx.android.synthetic.main.activity_main.*

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

//        mListener.title = mCidr.notation
        mListener.my_toolbar.title = mCidr.notation
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val (notation, netmask, wildcardMask, ipAddressRange) = mCidr
        val (initialIpAddress, lastIpAddress) = ipAddressRange

        textview_notation.text = notation
        textview_netmask.text = IpConverter.toIpAddress(netmask)
        textview_wildcard_mask.text = IpConverter.toIpAddress(wildcardMask)
        textview_ip_address_range.text = if (initialIpAddress == lastIpAddress) {
            IpConverter.toIpAddress(initialIpAddress)
        } else {
            getString(
                    R.string.shared_format_range,
                    IpConverter.toIpAddress(initialIpAddress),
                    IpConverter.toIpAddress(lastIpAddress)
            )
        }
    }
}
