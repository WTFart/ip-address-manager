package com.wtfart.ipaddressmanager.util.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.model.NetworkRepository

/**
 * Created by mickeycj on 11/15/2017.
 */
class Database {

    companion object {

        private val USERS_KEY = "users"

        @JvmStatic
        fun retrieveIpAddresses(uid: String) {
            val networkRepository = NetworkRepository.repository

            FirebaseDatabase
                    .getInstance()
                    .reference
                    .child(USERS_KEY)
                    .child(uid)
                    .addValueEventListener(object : ValueEventListener {

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            networkRepository.clear()

                            dataSnapshot
                                    .children
                                    .mapNotNullTo(networkRepository.networks) { network ->
                                        network.getValue<Network>(Network::class.java)
                                    }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                        }
                    })
        }
    }
}
