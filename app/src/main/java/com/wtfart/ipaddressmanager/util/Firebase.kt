package com.wtfart.ipaddressmanager.util

import android.support.v7.app.AppCompatActivity

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by mickeycj on 11/15/2017.
 */
class Firebase {

    companion object {

        @JvmStatic
        fun logInUser(
                activity: AppCompatActivity,
                email: String,
                password: String,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, onCompleteListener)
        }

        @JvmStatic
        fun registerUser(
                activity: AppCompatActivity,
                email: String,
                password: String,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, onCompleteListener)
        }

        @JvmStatic
        fun isLoggedIn() = FirebaseAuth.getInstance().currentUser != null
    }
}
