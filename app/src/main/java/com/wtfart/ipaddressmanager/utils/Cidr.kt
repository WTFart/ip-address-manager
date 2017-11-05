package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class Cidr {

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
        fun computeAvailableIpAddresses(initialIpAddress: Long, numberOfAddressBits: Int) =
                (initialIpAddress until initialIpAddress
                        + Math.pow(2.0, numberOfAddressBits.toDouble()).toLong())
                        .map { address -> address }
                        .toTypedArray()

        @JvmStatic
        fun compute(ipAddress: String, numberOfRequestedAddresses: Int) = arrayOf(Cidr())
    }
}