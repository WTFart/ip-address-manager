package com.wtfart.ipaddressmanager.util.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wtfart.ipaddressmanager.model.Cidr

import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.model.NetworkRepository

/**
 * Created by mickeycj on 11/15/2017.
 */
class Database {

    companion object {

        private val networkRepository = NetworkRepository.repository

        private val USERS_KEY = "users"

        @JvmStatic
        fun retrieveIpAddresses(uid: String) {
            getDatabaseReference(uid).addValueEventListener(object : ValueEventListener {

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

        @JvmStatic
        fun registerIpAddress(uid: String, name: String, cidrNotations: Array<Cidr>) {
            val key = getDatabaseReference(uid).push().key

            getDatabaseReference(uid).child(key).setValue(Network(key, name, cidrNotations))
        }

        private fun getDatabaseReference(uid: String) =
            FirebaseDatabase
                    .getInstance()
                    .reference
                    .child(USERS_KEY)
                    .child(uid)
    }
}
