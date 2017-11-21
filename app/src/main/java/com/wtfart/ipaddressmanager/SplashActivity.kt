package com.wtfart.ipaddressmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import com.wtfart.ipaddressmanager.util.firebase.Auth
import com.wtfart.ipaddressmanager.util.firebase.Database

class SplashActivity : AppCompatActivity() {

    private val LOGOUT_KEY = "LOGOUT"

    private val mStartMainActivityTime = 2000L
    private val mDelayTime = 1000L

    private lateinit var mHandler: Handler

    private lateinit var mStartMainActivityRunnable: Runnable
    private lateinit var mDelayRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mHandler = Handler()

        mStartMainActivityRunnable = Runnable {
            start(MainActivity::class.java)
        }
        mDelayRunnable = Runnable {
            if (Auth.isLoggedIn()) {
                Database.retrieveDatabase(Auth.getUid())

                mHandler.postDelayed(mStartMainActivityRunnable, mStartMainActivityTime)
            } else {
                start(AuthActivity::class.java)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        mHandler.postDelayed(mDelayRunnable, mDelayTime)
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mDelayRunnable)
        mHandler.removeCallbacks(mStartMainActivityRunnable)
    }

    private fun start(cls: Class<*>) {
        val intent = Intent(this@SplashActivity, cls)
        if (cls == AuthActivity::class.java) {
            intent.putExtra(LOGOUT_KEY, false)
        }
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
