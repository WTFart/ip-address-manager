package com.wtfart.ipaddressmanager

import android.app.Dialog
import android.content.Context
import android.view.Window

class RegistrationDialog(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_registration)
    }
}