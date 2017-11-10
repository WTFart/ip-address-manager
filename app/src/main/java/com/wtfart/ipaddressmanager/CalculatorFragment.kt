package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_calculator.*

import com.wtfart.ipaddressmanager.model.Cidr

class CalculatorFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = CalculatorFragment()
    }

    private lateinit var mListener: MainActivity

    private lateinit var mCidrNotationsAdapter: ArrayAdapter<String>

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
        return inflater?.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_calculate.setOnClickListener {
            try {
                calculate()
            } catch (e: IllegalArgumentException) {
                Toast.makeText(mListener, getString(R.string.calculator_error_input), Toast.LENGTH_LONG).show()
            }
        }
        button_clear.setOnClickListener {
            edittext_input_ip_address.setText("")
            edittext_input_num_addresses.setText("")
            layout_calculator_output.visibility = View.INVISIBLE
        }
        listview_cidr_notations.setOnItemClickListener { _, _, i, _ ->
            mListener.switchFragment(DetailFragment.newInstance(mCidrNotations[i]))
        }
    }

    override fun onResume() {
        super.onResume()

        try {
            calculate()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    private fun calculate() {
        mCidrNotations = Cidr.compute(
                edittext_input_ip_address.text.toString(),
                edittext_input_num_addresses.text.toString().toInt()
        )

        textview_required_cidr_notations.text = mCidrNotations.size.toString()
        mCidrNotationsAdapter = ArrayAdapter(
                mListener,
                android.R.layout.simple_list_item_1,
                mCidrNotations.map { cidrNotation -> cidrNotation.notation }
        )
        listview_cidr_notations.adapter = mCidrNotationsAdapter
        layout_calculator_output.visibility = View.VISIBLE
    }
}
