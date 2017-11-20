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
            LongRange(initial, last).contains(LongRange(range.first, range.second))
        }
    }

    fun clearNetworks() {
        networks.clear()
    }

    fun clearIpAddressRanges() {
        ipAddressRanges.clear()
    }

    private fun LongRange.contains(values: LongRange): Boolean {
        forEach { long ->
            if (values.contains(long)) {
                return true
            }
        }
        return false
    }
}
