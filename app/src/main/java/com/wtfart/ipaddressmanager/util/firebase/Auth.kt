package com.wtfart.ipaddressmanager.util.firebase

import android.support.v7.app.AppCompatActivity

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Auth {

    companion object {

        @JvmStatic
        fun loginUser(
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
        fun logoutUser() {
            getAuthInstance().signOut()
        }

        @JvmStatic
        fun getUid() = getCurrentUser()!!.uid

        @JvmStatic
        fun isLoggedIn() = getCurrentUser() != null

        private fun authHelper(
                activity: AppCompatActivity,
                action: () -> Task<AuthResult>,
                onCompleteListener: (Task<AuthResult>) -> Unit
        ) {
            action().addOnCompleteListener(activity, onCompleteListener)
        }

        private fun getAuthInstance() = FirebaseAuth.getInstance()

        private fun getCurrentUser() = getAuthInstance().currentUser
    }
}
