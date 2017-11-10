package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.fragment_calculator.*

import com.wtfart.ipaddressmanager.model.Cidr

class CalculatorFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = CalculatorFragment()
    }

    private lateinit var mListener: AppCompatActivity

    private lateinit var mCidrNotationsAdapter: ArrayAdapter<String>

    private lateinit var mCidrNotations: Array<Cidr>

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as AppCompatActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_calculate.setOnClickListener {
            mCidrNotations = Cidr.compute(
                    edittext_input_ip_address.text.toString(),
                    edittext_input_num_addresses.text.toString().toInt()
            )

            textview_required_cidr_notations.text =
                    getString(R.string.calculator_info_required_cidr_notations, mCidrNotations.size)
            mCidrNotationsAdapter = ArrayAdapter(
                    mListener,
                    android.R.layout.simple_list_item_1,
                    mCidrNotations.map { cidrNotation -> cidrNotation.notation }
            )
            listview_cidr_notations.adapter = mCidrNotationsAdapter
            layout_calculator_output.visibility = View.VISIBLE
        }
        button_clear.setOnClickListener {
            edittext_input_ip_address.setText("")
            edittext_input_num_addresses.setText("")
            layout_calculator_output.visibility = View.INVISIBLE
        }
    }
}
