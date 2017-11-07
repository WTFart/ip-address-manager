package com.wtfart.ipaddressmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val DELAY_TIME = 3000L

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mHandler = Handler()
        mRunnable = Runnable {
            TODO("Implement an entry point for the next activity - MainActivity")
        }
    }

    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(mRunnable, DELAY_TIME)
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(mRunnable)
    }
}
