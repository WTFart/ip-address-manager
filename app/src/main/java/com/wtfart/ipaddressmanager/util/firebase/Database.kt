package com.wtfart.ipaddressmanager.util.firebase

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

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
            getUserReference(uid).addChildEventListener(object : ChildEventListener {

                override fun onChildAdded(dataSnapshot: DataSnapshot?, s: String) {
                    updateIpAddresses(dataSnapshot)
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                    updateIpAddresses(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot?, s: String) {
                    updateIpAddresses(dataSnapshot)
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot?, s: String) {
                    updateIpAddresses(dataSnapshot)
                }

                override fun onCancelled(databaseError: DatabaseError?) = Unit
            })
        }

        @JvmStatic
        fun retrieveIpAddressesRanges() {
            getIpAddressRangesReference().addChildEventListener(object : ChildEventListener {

                override fun onChildAdded(dataSnapshot: DataSnapshot?, s: String) {
                    updateIpAddressesRanges(dataSnapshot)
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
                    updateIpAddressesRanges(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot?, s: String) {
                    updateIpAddressesRanges(dataSnapshot)
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot?, s: String) {
                    updateIpAddressesRanges(dataSnapshot)
                }

                override fun onCancelled(databaseError: DatabaseError?) = Unit
            })
        }

        @JvmStatic
        fun registerIpAddress(uid: String, name: String, cidrNotations: List<Cidr>) {
            val key = getUserReference(uid).push().key

            getUserReference(uid)
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
            getUserReference(uid)
                    .child(key)
                    .setValue(null)
            getIpAddressRangesReference()
                    .child(key)
                    .setValue(null)
        }

        private fun updateIpAddresses(dataSnapshot: DataSnapshot?) {
            networkRepository.clearNetworks()
            dataSnapshot
                    ?.children
                    ?.mapNotNullTo(networkRepository.networks) { network ->
                        network.getValue<Network>(Network::class.java)
                    }
        }

        private fun updateIpAddressesRanges(dataSnapshot: DataSnapshot?) {
            networkRepository.clearIpAddressRanges()
//            dataSnapshot
//                    ?.children
//                    ?.mapNotNullTo(networkRepository.ipAddressRanges) { ipAddressRange ->
//                        ipAddressRange.getValue<Pair<Long, Long>>(Pair::class.java)
//                    }
        }

        private fun getUserReference(uid: String): DatabaseReference {
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
