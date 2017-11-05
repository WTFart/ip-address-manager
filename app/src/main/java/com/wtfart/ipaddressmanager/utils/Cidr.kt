package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
data class Cidr(
        val notation: String,
        val netmask: Long,
        val wildcard: Long,
        val addresses: Array<Long>
) {

    companion object {

        @JvmStatic
        fun computeAddressBitsCombination(numberOfRequestedAddresses: Int): Array<Int> {
            val binaryStr = numberOfRequestedAddresses.toString(2)
            return binaryStr.mapIndexed { power, digit ->
                        if (digit.toInt() - '0'.toInt() > 0) {
                            binaryStr.length - power - 1
                        } else {
                            -1
                        }
                    }.filter { bit ->
                        bit > -1
                    }.toTypedArray()
        }

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
                        0b0,
                        0b0,
                        arrayOf(0b0)
                )
        )
    }

    override fun equals(other: Any?) = notation == (other as Cidr).notation

    override fun hashCode() = notation.hashCode()
}