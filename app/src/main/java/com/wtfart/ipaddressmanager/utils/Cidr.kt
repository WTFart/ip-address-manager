package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class Cidr {

    companion object {

        @JvmStatic
        fun computeAddressBitsCombination(numberOfRequestedAddresses: Int) = arrayOf(0)

        @JvmStatic
        fun computeInitialIpAddresses(ipAddress: Long, addressBitsCombination: Array<Int>): Array<Long> {
            val reducedIpAddress =
                    ipAddress and IntRange(1, 32)
                            .fold(StringBuilder()) { bitStr, bit ->
                                bitStr.append(
                                        if (bit <= 32 - addressBitsCombination[0]) {
                                            '1'
                                        } else {
                                            '0'
                                        }
                                )
                            }.toString()
                            .toLong(2)
            return (0 until addressBitsCombination.size).map { address ->
                        reducedIpAddress + (0 until address).fold(0L) { offset, index ->
                            offset + Math.pow(
                                    2.0,
                                    addressBitsCombination[index].toDouble()
                            ).toLong()
                        }
                    }.toTypedArray()
        }

        @JvmStatic
        fun computeCidrNotation(ipAddress: String, numberOfMaskBits: Int) = ""

        @JvmStatic
        fun computeCidrNetmask(numberOfMaskBits: Int) = ""

        @JvmStatic
        fun computeWildcardMask(numberOfAddressBits: Int) = ""

        @JvmStatic
        fun computeAvailableIpAddresses(ipAddress: String, numberOfAddressBits: Array<Int>) = arrayOf("")

        @JvmStatic
        fun compute(ipAddress: String, numberOfRequestedAddresses: Int) = arrayOf(Cidr())
    }
}