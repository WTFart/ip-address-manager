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
    private lateinit var mCidrListFragment: CidrListFragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
        mCidrListFragment = CidrListFragment.newInstance()
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mListener.setTitle(R.string.calculator_name)

        childFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, mCidrListFragment)
                .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_calculate.setOnClickListener {
            try {
                calculate()
                mListener.requestFocus()
            } catch (e: IllegalArgumentException) {
                Toast.makeText(mListener, getString(R.string.calculator_error_input), Toast.LENGTH_LONG).show()
            }
        }
        button_clear.setOnClickListener {
            edittext_input_ip_address.setText("")
            edittext_input_num_addresses.setText("")
            fragment_container.visibility = View.INVISIBLE
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
        mCidrListFragment.setCidrNotations(
                Cidr.compute(
                        edittext_input_ip_address.text.toString(),
                        edittext_input_num_addresses.text.toString().toInt()
                )
        )
        fragment_container.visibility = View.VISIBLE
    }
}
