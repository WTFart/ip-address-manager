package com.wtfart.ipaddressmanager

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class AuthActivity : AppCompatActivity() {

    private val LOGOUT_KEY = "LOGOUT"

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        progressDialog = ProgressDialog(this@AuthActivity)

        if (intent.getBooleanExtra(LOGOUT_KEY, false)) {
            Toast.makeText(
                    this@AuthActivity,
                    getString(R.string.auth_logout_successful),
                    Toast.LENGTH_LONG
            ).show()
        }

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, LoginFragment.newInstance())
                .commit()
    }

    fun showProgressDialog() {
        progressDialog.show()
    }

    fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right
                )
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }
}
