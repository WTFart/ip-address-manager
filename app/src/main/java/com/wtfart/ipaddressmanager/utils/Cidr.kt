package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
data class Cidr(
        val notation: String,
        val netmask: String,
        val wildcard: String,
        val addresses: Array<String>
) {

    companion object {

        @JvmStatic
        fun computeAddressBitsCombination(numberOfRequestedAddresses: Int) = arrayOf(0)

        @JvmStatic
        fun computeInitialIpAddresses(addressBitsCombination: Array<Int>) = arrayOf("")

        @JvmStatic
        fun computeCidrNotation(ipAddress: String, numberOfMaskBits: Int) = ""

        @JvmStatic
        fun computeCidrNetmask(numberOfMaskBits: Int) = ""

        @JvmStatic
        fun computeWildcardMask(numberOfAddressBits: Int) = ""

        @JvmStatic
        fun computeAvailableIpAddresses(ipAddress: String, numberOfAddressBits: Array<Int>) = arrayOf("")

        @JvmStatic
        fun compute(ipAddress: String, numberOfRequestedAddresses: Int) = arrayOf(
                Cidr(
                        "",
                        "",
                        "",
                        arrayOf("")
                )
        )
    }
}