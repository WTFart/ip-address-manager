package com.wtfart.ipaddressmanager

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo

import kotlinx.android.synthetic.main.dialog_registration.*

import com.wtfart.ipaddressmanager.model.Cidr

class RegistrationDialog(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_registration)

        edittext_input_name.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE) {
                edittext_input_name.clearFocus()
            }
            false
        }
        button_cancel.setOnClickListener { dismiss() }
    }

    fun setCidrNotationRange(cidrNotations: Array<Cidr>): RegistrationDialog {
        textview_cidr_notation_range.text = context.getString(
                R.string.shared_format_range,
                cidrNotations[0].notation,
                cidrNotations[cidrNotations.size - 1].notation
        )

        return this
    }

    fun setOnConfirmClickedListener(listener: (View) -> Unit): RegistrationDialog {
        button_confirm.setOnClickListener(listener)

        return this
    }

    fun geInputName() = edittext_input_name.text.toString()
}
