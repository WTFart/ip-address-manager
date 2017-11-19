package com.wtfart.ipaddressmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

//        supportFragmentManager
//                .beginTransaction()
//                .add(R.id.auth_container, LoginFragment.newInstance())
//                .commit()
    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.auth_container, fragment)
                .addToBackStack(null)
                .commit()
    }
}
