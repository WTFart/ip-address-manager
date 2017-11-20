package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_calculator.*

import com.wtfart.ipaddressmanager.model.Cidr
import com.wtfart.ipaddressmanager.util.firebase.Auth
import com.wtfart.ipaddressmanager.util.firebase.Database

class CalculatorFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = CalculatorFragment()
    }

    private lateinit var mListener: MainActivity
    private lateinit var mCidrListFragment: CidrListFragment

    private lateinit var mCidrNotations: Array<Cidr>

    private var isCalculated = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
        mCidrListFragment = CidrListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCidrNotations = arrayOf()

        mListener.setActionBarTitle(getString(R.string.calculator_name))
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater?.inflate(R.layout.fragment_calculator, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mListener.setActionBarTitle(getString(R.string.calculator_name))

        childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, mCidrListFragment)
                .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_calculate_and_register.setOnClickListener {
            if (isCalculated) {
                val registrationDialog = RegistrationDialog(mListener)

                registrationDialog
                        .setCidrNotationRange(mCidrNotations)
                        .setOnConfirmClickedListener {
                            Database.registerIpAddress(
                                    Auth.getUid(),
                                    registrationDialog.geInputName(),
                                    mCidrNotations.asList()
                            )
                        }
                        .show()
            } else {
                try {
                    calculate()
                    mListener.requestFocus()
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(mListener, getString(R.string.calculator_error_input), Toast.LENGTH_LONG).show()
                }
            }
        }
        button_clear.setOnClickListener {
            isCalculated = false
            edittext_input_ip_address.setText("")
            edittext_input_num_addresses.setText("")
            button_calculate_and_register.text = getString(R.string.calculator_button_calculate)
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
        mCidrNotations = Cidr.compute(
                edittext_input_ip_address.text.toString(),
                edittext_input_num_addresses.text.toString().toInt()
        )
        mCidrListFragment.setCidrNotations(mCidrNotations)
        button_calculate_and_register.text = getString(R.string.calculator_button_register)
        fragment_container.visibility = View.VISIBLE
        isCalculated = true
    }
}
