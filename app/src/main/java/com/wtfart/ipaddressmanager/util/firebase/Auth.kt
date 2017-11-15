package com.wtfart.ipaddressmanager.util.firebase

import android.support.v7.app.AppCompatActivity

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by mickeycj on 11/15/2017.
 */
class Auth {

    companion object {

        @JvmStatic
        fun logInUser(
                activity: AppCompatActivity,
                email: String,
                password: String,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            authHelper(
                    activity,
                    { getAuthInstance().signInWithEmailAndPassword(email, password) },
                    onCompleteListener
            )
        }

        @JvmStatic
        fun registerUser(
                activity: AppCompatActivity,
                email: String,
                password: String,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            authHelper(
                    activity,
                    { getAuthInstance().createUserWithEmailAndPassword(email, password) },
                    onCompleteListener
            )
        }

        @JvmStatic
        fun isLoggedIn() = getAuthInstance().currentUser != null

        private fun authHelper(
                activity: AppCompatActivity,
                action: () -> Task<AuthResult>,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            action().addOnCompleteListener(activity, onCompleteListener)
        }

        private fun getAuthInstance() = FirebaseAuth.getInstance()
    }
}
