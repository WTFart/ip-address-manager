package com.wtfart.ipaddressmanager.model

import com.wtfart.ipaddressmanager.util.IpConverter

import java.io.Serializable

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
data class Cidr(
        val notation: String,
        val netmask: Long,
        val wildcardMask: Long,
        val ipAddressRange: Pair<Long, Long>
) : Serializable {

    companion object {

        @JvmStatic
        fun computeAddressBitsCombination(requestedAddresses: Int): Array<Int> {
            val binaryStr = requestedAddresses.toString(2)
            val length = binaryStr.length

            return binaryStr
                    .mapIndexed { power, digit ->
                        if (digit.toInt() - '0'.toInt() > 0) {
                            length - power - 1
                        } else {
                            -1
                        }
                    }.filter { bit ->
                        bit > -1
                    }.toTypedArray()
        }

        @JvmStatic
        fun computeInitialIpAddresses(ipAddress: Long, addressBitsCombination: Array<Int>): Array<Long> {
            val initialIpAddress = ipAddress and IntRange(1, 32)
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

            return (0 until addressBitsCombination.size)
                    .map { address ->
                        initialIpAddress + (0 until address)
                                .fold(0L) { offset, index ->
                                    offset + Math.pow(
                                            2.0,
                                            addressBitsCombination[index].toDouble()
                                    ).toLong()
                                }
                    }.toTypedArray()
        }

        @JvmStatic
        fun computeNotation(ipAddress: String, maskBits: Int) = "$ipAddress/$maskBits"

        @JvmStatic
        fun computeNetmask(maskBits: Int): Long {
            return IntRange(1, 32)
                    .fold(StringBuilder()) { bitStr, bit ->
                        bitStr.append(
                                if (bit <= maskBits) {
                                    '1'
                                } else {
                                    '0'
                                }
                        )
                    }.toString()
                    .toLong(2)
        }

        @JvmStatic
        fun computeWildcardMask(addressBits: Int): Long {
            return IntRange(1, 32)
                    .fold(StringBuilder()) { bitStr, bit ->
                        bitStr.append(
                                if (bit > 32 - addressBits) {
                                    '1'
                                } else {
                                    '0'
                                }
                        )
                    }.toString()
                    .toLong(2)
        }

        @JvmStatic
        fun computeIpAddressRange(initialIpAddress: Long, wildcardMask: Long): Pair<Long, Long> {
            return Pair(initialIpAddress, initialIpAddress + wildcardMask)
        }

        @JvmStatic
        fun compute(ipAddress: String, requestedAddresses: Int): Array<Cidr> {
            val addressBitsCombination = computeAddressBitsCombination(requestedAddresses)
            val initialIpAddresses = computeInitialIpAddresses(
                    IpConverter.toBinary(ipAddress),
                    addressBitsCombination
            )

            return initialIpAddresses
                    .zip(addressBitsCombination)
                    .map { (initialIpAddress, numberOfAddressBits) ->
                        val numberOfMaskBits = 32 - numberOfAddressBits
                        val notation = computeNotation(
                                IpConverter.toIpAddress(initialIpAddress),
                                numberOfMaskBits
                        )
                        val netmask = computeNetmask(numberOfMaskBits)
                        val wildcardMask = computeWildcardMask(numberOfAddressBits)
                        val ipAddressRange = computeIpAddressRange(initialIpAddress, wildcardMask)

                        Cidr(notation, netmask, wildcardMask, ipAddressRange)
                    }.toTypedArray()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cidr

        if (notation != other.notation) return false
        if (netmask != other.netmask) return false
        if (wildcardMask != other.wildcardMask) return false
        if (ipAddressRange != other.ipAddressRange) return false

        return true
    }

    override fun hashCode(): Int {
        var result = notation.hashCode()
        result = 31 * result + netmask.hashCode()
        result = 31 * result + wildcardMask.hashCode()
        result = 31 * result + ipAddressRange.hashCode()

        return result
    }
}
