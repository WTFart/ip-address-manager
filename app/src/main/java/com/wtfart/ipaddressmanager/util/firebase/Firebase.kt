package com.wtfart.ipaddressmanager.util.firebase

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
            firebaseAuthHelper(
                    activity,
                    { getFirebaseAuthInstance().signInWithEmailAndPassword(email, password) },
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
            firebaseAuthHelper(
                    activity,
                    { getFirebaseAuthInstance().createUserWithEmailAndPassword(email, password) },
                    onCompleteListener
            )
        }

        @JvmStatic
        fun isLoggedIn() = getFirebaseAuthInstance().currentUser != null

        private fun firebaseAuthHelper(
                activity: AppCompatActivity,
                action: () -> Task<AuthResult>,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            action().addOnCompleteListener(activity, onCompleteListener)
        }

        private fun getFirebaseAuthInstance() = FirebaseAuth.getInstance()
    }
}
