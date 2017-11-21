package com.wtfart.ipaddressmanager

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

import com.wtfart.ipaddressmanager.R.id.button_logout

import com.wtfart.ipaddressmanager.util.firebase.Auth
import com.wtfart.ipaddressmanager.util.firebase.Database

class MainActivity : AppCompatActivity() {

    private val LOGOUT_KEY = "LOGOUT"

    private val mLogoutTime = 1000L

    private lateinit var mHandler: Handler

    private lateinit var mLogoutRunnable: Runnable

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(action_bar)

        mHandler = Handler()

        mLogoutRunnable = Runnable {
            dismissProgressDialog()

            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            intent.putExtra(LOGOUT_KEY, true)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        progressDialog = ProgressDialog(this@MainActivity)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ListFragment.newInstance())
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            button_logout -> {
                showProgressDialog()

                Auth.logoutUser()
                Database.clearDatabase()

                mHandler.postDelayed(mLogoutRunnable, mLogoutTime)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()

        mHandler.removeCallbacks(mLogoutRunnable)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        setupHomeButton(false)
    }

    fun showProgressDialog() {
        progressDialog.show()
    }

    fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    fun requestFocus() {
        layout_main_activity.requestFocus()
    }

    fun setActionBarTitle(title: String) {
        action_bar.title = title
    }

    fun switchFragment(fragment: Fragment) {
        setupHomeButton(true)
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

    private fun setupHomeButton(status: Boolean) {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportActionBar?.setHomeButtonEnabled(status)
            supportActionBar?.setDisplayHomeAsUpEnabled(status)
        }
    }
}
