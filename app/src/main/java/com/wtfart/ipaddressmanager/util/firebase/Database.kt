package com.wtfart.ipaddressmanager.util.firebase

import com.google.firebase.database.*
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
            getDatabaseReference(uid).addChildEventListener(object : ChildEventListener {

                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {
                    updateNetworkRepository(dataSnapshot)
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    updateNetworkRepository(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {
                    updateNetworkRepository(dataSnapshot)
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {
                    updateNetworkRepository(dataSnapshot)
                }

                override fun onCancelled(dataSnapshot: DatabaseError) {
                }
            })
        }

        @JvmStatic
        fun registerIpAddress(uid: String, name: String, cidrNotations: List<Cidr>) {
            val key = getDatabaseReference(uid).push().key

            getDatabaseReference(uid).child(key).setValue(Network(key, name, cidrNotations))
        }

        @JvmStatic
        fun revokeIpAddress(uid: String, key: String) {
            getDatabaseReference(uid).child(key).setValue(null)
        }

        private fun getDatabaseReference(uid: String) =
            FirebaseDatabase
                    .getInstance()
                    .reference
                    .child(USERS_KEY)
                    .child(uid)

        private fun updateNetworkRepository(dataSnapshot: DataSnapshot) {
            networkRepository.clear()
            dataSnapshot
                    .children
                    .mapNotNullTo(networkRepository.networks) { network ->
                        network.getValue<Network>(Network::class.java)
                    }
        }
    }
}
