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
        fun computeCidrNotation(ipAddress: String, numberOfMaskBits: Int) =
                "$ipAddress/$numberOfMaskBits"

        @JvmStatic
        fun computeCidrNetmask(numberOfMaskBits: Int) = IntRange(1, 32)
                .fold(StringBuilder()) { bitStr, bit ->
                    bitStr.append(
                            if (bit <= numberOfMaskBits) {
                                '1'
                            } else {
                                '0'
                            }
                    )
                }.toString()
                .toLong(2)

        @JvmStatic
        fun computeWildcardMask(numberOfAddressBits: Int) = IntRange(1, 32)
                .fold(StringBuilder()) { bitStr, bit ->
                    bitStr.append(
                            if (bit > 32 - numberOfAddressBits) {
                                '1'
                            } else {
                                '0'
                            }
                    )
                }.toString()
                .toLong(2)

        @JvmStatic
        fun computeAvailableIpAddresses(initialIpAddress: Long, wildcardMask: Long): Array<Long> {
            val lastIpAddress = initialIpAddress + wildcardMask
            return if (initialIpAddress == lastIpAddress) {
                arrayOf(initialIpAddress)
            } else {
                arrayOf(initialIpAddress, lastIpAddress)
            }
        }

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