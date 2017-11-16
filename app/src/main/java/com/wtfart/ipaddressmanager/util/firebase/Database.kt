package com.wtfart.ipaddressmanager.util.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

import com.wtfart.ipaddressmanager.model.Cidr
import com.wtfart.ipaddressmanager.model.Network
import com.wtfart.ipaddressmanager.model.NetworkRepository
import com.wtfart.ipaddressmanager.model.Pair

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
            DatabaseDelegate.retrieveIpAddresses(uid)
        }

        @JvmStatic
        fun retrieveIpAddressesRanges() {
            DatabaseDelegate.retrieveIpAddressRanges()
        }

        @JvmStatic
        fun registerIpAddress(uid: String, name: String, cidrNotations: List<Cidr>) {
            val key = getUserReference(uid).push().key

            val ipAddressRange = Pair(
                    cidrNotations.first().ipAddressRange.first,
                    cidrNotations.last().ipAddressRange.second
            )
            if (!networkRepository.contains(ipAddressRange)) {
                getUserReference(uid)
                        .child(key)
                        .setValue(Network(key, name, cidrNotations))
                getIpAddressRangeReference()
                        .child(key)
                        .setValue(
                                Pair(
                                        cidrNotations.first().ipAddressRange.first,
                                        cidrNotations.last().ipAddressRange.second
                                )
                        )
            }
        }

        @JvmStatic
        fun revokeIpAddress(uid: String, key: String) {
            getUserReference(uid)
                    .child(key)
                    .setValue(null)
            getIpAddressRangeReference()
                    .child(key)
                    .setValue(null)
        }

        @JvmStatic
        fun updateIpAddresses(uid: String, dataSnapshot: DataSnapshot) {
            networkRepository.clearNetworks()
            if (uid == dataSnapshot.key) {
                dataSnapshot
                        .children
                        .mapNotNullTo(networkRepository.networks) { network ->
                            network.getValue<Network>(Network::class.java)
                        }
            }
        }

        @JvmStatic
        fun updateIpAddressesRanges(dataSnapshot: DataSnapshot) {
            networkRepository.clearIpAddressRanges()
            if (dataSnapshot.key == IP_ADDRESS_RANGES_KEY) {
                dataSnapshot
                        .children
                        .mapNotNullTo(networkRepository.ipAddressRanges) { ipAddressRange ->
                            ipAddressRange.getValue<Pair>(Pair::class.java)
                        }
            }
        }

        @JvmStatic
        fun getUsersReference(): DatabaseReference = getDatabaseReference().child(USERS_KEY)

        @JvmStatic
        fun getIpAddressRangesReference(): DatabaseReference = getDatabaseReference()

        private fun getDatabaseReference() = FirebaseDatabase.getInstance().reference

        private fun getUserReference(uid: String): DatabaseReference = getDatabaseReference().child(USERS_KEY).child(uid)

        private fun getIpAddressRangeReference(): DatabaseReference = getDatabaseReference().child(IP_ADDRESS_RANGES_KEY)
    }
}
