package com.wtfart.ipaddressmanager.model

/**
 * Created by mickeycj on 11/15/2017.
 */
data class NetworkRepository(
        val networks: MutableList<Network> = mutableListOf(),
        val ipAddressRanges: MutableList<Pair> = mutableListOf()
) {

    companion object {

        @JvmField
        val repository = NetworkRepository()
    }

    fun contains(ipAddressRange: Pair) = false

    fun clearNetworks() {
        networks.clear()
    }

    fun clearIpAddressRanges() {
        ipAddressRanges.clear()
    }
}
