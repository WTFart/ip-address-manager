package com.wtfart.ipaddressmanager

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_calculator.*

import com.wtfart.ipaddressmanager.model.Cidr
import com.wtfart.ipaddressmanager.model.NetworkRepository
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

    private var mInitialSize = -1

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
            if (mInitialSize != NetworkRepository.INSTANCE.networks.size) {
                mListener.onBackPressed()
                Toast.makeText(
                        mListener,
                        getString(R.string.shared_registration_successful),
                        Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                        mListener,
                        getString(R.string.calculator_ip_addresses_taken),
                        Toast.LENGTH_LONG
                ).show()
            }
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

        edittext_input_num_addresses.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE) {
                calculateOrRegister()
            }
            false
        }
        button_calculate_and_register.setOnClickListener {
            calculateOrRegister()
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

    private fun calculateOrRegister() {
        if (mIsCalculated) {
            mRegistrationDialog
                    .setCidrNotationRange(mCidrNotations)
                    .setOnConfirmClickedListener {
                        val name = mRegistrationDialog.geInputName()

                        if (name != "") {
                            mListener.showProgressDialog()

                            mInitialSize = NetworkRepository.INSTANCE.networks.size

                            Database.registerIpAddress(Auth.getUid(), name, mCidrNotations.asList())

                            mHandler.postDelayed(mRegistrationRunnable, REGISTRATION_DELAY)
                            mRegistrationDialog.dismiss()
                        } else {
                            Toast.makeText(
                                    mListener,
                                    getString(R.string.shared_error_empty_input),
                                    Toast.LENGTH_LONG
                            ).show()
                        }
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
