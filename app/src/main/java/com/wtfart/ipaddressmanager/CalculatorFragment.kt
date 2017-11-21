package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.os.Handler
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

    private val REGISTRATION_DELAY = 2000L

    private lateinit var mListener: MainActivity
    private lateinit var mCidrNotationsFragment: CidrNotationsFragment
    private lateinit var mRegistrationDialog: RegistrationDialog

    private lateinit var mHandler: Handler

    private lateinit var mRegistrationRunnable: Runnable

    private lateinit var mCidrNotations: Array<Cidr>

    private var mIsCalculated = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mListener = context as MainActivity
        mCidrNotationsFragment = CidrNotationsFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRegistrationDialog = RegistrationDialog(mListener)

        mHandler = Handler()

        mRegistrationRunnable = Runnable {
            mListener.dismissProgressDialog()
            mListener.onBackPressed()
        }

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
                .replace(R.id.fragment_container, mCidrNotationsFragment)
                .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_calculate_and_register.setOnClickListener {
            if (mIsCalculated) {
                mRegistrationDialog
                        .setCidrNotationRange(mCidrNotations)
                        .setOnConfirmClickedListener {
                            mListener.showProgressDialog()

                            Database.registerIpAddress(
                                    Auth.getUid(),
                                    mRegistrationDialog.geInputName(),
                                    mCidrNotations.asList()
                            )

                            mRegistrationDialog.dismiss()
                            mHandler.postDelayed(mRegistrationRunnable, REGISTRATION_DELAY)
                        }
                        .show()
            } else {
                try {
                    calculate()
                    mListener.requestFocus()
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(
                            mListener,
                            getString(R.string.calculator_error_input),
                            Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        button_clear.setOnClickListener {
            mIsCalculated = false
            edittext_input_ip_address.setText("")
            edittext_input_num_addresses.setText("")
            setEditTextsEnabled(true)
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

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mRegistrationRunnable)
    }

    private fun calculate() {
        mCidrNotations = Cidr.compute(
                edittext_input_ip_address.text.toString(),
                edittext_input_num_addresses.text.toString().toInt()
        )
        mCidrNotationsFragment.setCidrNotations(mCidrNotations)
        setEditTextsEnabled(false)
        button_calculate_and_register.text = getString(R.string.calculator_button_register)
        fragment_container.visibility = View.VISIBLE
        mIsCalculated = true
    }

    private fun setEditTextsEnabled(status: Boolean) {
        edittext_input_ip_address.isClickable = status
        edittext_input_ip_address.isFocusable = status
        edittext_input_ip_address.isFocusableInTouchMode = status
        edittext_input_num_addresses.isClickable = status
        edittext_input_num_addresses.isFocusable = status
        edittext_input_num_addresses.isFocusableInTouchMode = status
    }
}
