package com.wtfart.ipaddressmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import com.wtfart.ipaddressmanager.util.firebase.Auth

class SplashActivity : AppCompatActivity() {

    private val mDelayTime = 3000L

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mHandler = Handler()
        mRunnable = Runnable {
            startActivity(
                    Intent(
                            this@SplashActivity,
                            if (Auth.isLoggedIn()) {
                                MainActivity::class.java
                            } else {
                                AuthActivity::class.java
                            }
                    )
            )
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun onResume() {
        super.onResume()

        mHandler.postDelayed(mRunnable, mDelayTime)
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mRunnable)
    }
}
