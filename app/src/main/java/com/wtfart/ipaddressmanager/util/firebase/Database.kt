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

        private val USERS_KEY = "users"
        private val IP_ADDRESS_RANGES_KEY = "ip_address_ranges"

        private val networkRepository = NetworkRepository.repository

        @JvmStatic
        fun retrieveIpAddresses(uid: String) {
            getUsersReference(uid).addChildEventListener(object : ChildEventListener {

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
            val key = getUsersReference(uid).push().key

            getUsersReference(uid)
                    .child(key)
                    .setValue(Network(key, name, cidrNotations))
            getIpAddressRangesReference()
                    .child(key)
                    .setValue(
                        Pair(
                                cidrNotations.first().ipAddressRange.first,
                                cidrNotations.last().ipAddressRange.second
                        )
                    )
        }

        @JvmStatic
        fun revokeIpAddress(uid: String, key: String) {
            getUsersReference(uid)
                    .child(key)
                    .setValue(null)
            getIpAddressRangesReference()
                    .child(key)
                    .setValue(null)
        }

        private fun updateNetworkRepository(dataSnapshot: DataSnapshot) {
            networkRepository.clear()
            dataSnapshot
                    .children
                    .mapNotNullTo(networkRepository.networks) { network ->
                        network.getValue<Network>(Network::class.java)
                    }
        }

        private fun getUsersReference(uid: String): DatabaseReference {
            return FirebaseDatabase
                    .getInstance()
                    .reference
                    .child(USERS_KEY)
                    .child(uid)
        }


        private fun getIpAddressRangesReference(): DatabaseReference {
            return FirebaseDatabase
                    .getInstance()
                    .reference
                    .child(IP_ADDRESS_RANGES_KEY)
        }
    }
}
