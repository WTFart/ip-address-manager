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

    fun contains(range: Pair): Boolean {
        return ipAddressRanges.any { (initial, last) ->
            val (first, second) = range
            (first <= initial || first in initial..last)
                    && (second in initial..last || second >= last)
        }
    }

    fun clearNetworks() {
        networks.clear()
    }

    fun clearIpAddressRanges() {
        ipAddressRanges.clear()
    }
}
