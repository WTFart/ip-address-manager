package com.wtfart.ipaddressmanager.model

data class NetworkRepository(
        val networks: MutableList<Network> = mutableListOf(),
        val ipAddressRanges: MutableList<Pair> = mutableListOf()
) {

    companion object {

        @JvmField
        val INSTANCE = NetworkRepository()
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
