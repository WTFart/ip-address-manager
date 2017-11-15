package com.wtfart.ipaddressmanager.model

import java.io.Serializable
import java.util.regex.Pattern

import com.google.firebase.database.PropertyName

import com.wtfart.ipaddressmanager.util.IpConverter

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
data class Cidr(
        @get:PropertyName("notation")
        var notation: String,
        @get:PropertyName("netmask")
        var netmask: Long,
        @get:PropertyName("wildcard_mask")
        var wildcardMask: Long,
        @get:PropertyName("ip_address_range")
        var ipAddressRange: Pair<Long, Long>
) : Serializable {

    constructor() : this(
            "",
            0b0,
            0b0,
            Pair(0b0, 0b0)
    )

    companion object {

        private val PATTERN = Pattern.compile(
                "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\$"
        )

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
            if (!PATTERN.matcher(ipAddress).matches() || requestedAddresses < 1) {
                throw IllegalArgumentException()
            }

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

        return notation == other.notation
                || netmask == other.netmask
                || wildcardMask == other.wildcardMask
                || ipAddressRange == other.ipAddressRange
    }

    override fun hashCode(): Int {
        var result = notation.hashCode()
        result = 31 * result + netmask.hashCode()
        result = 31 * result + wildcardMask.hashCode()
        result = 31 * result + ipAddressRange.hashCode()

        return result
    }
}
