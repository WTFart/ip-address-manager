package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
data class Cidr(
        val notation: String,
        val netmask: Long,
        val wildcardMask: Long,
        val ipAddressRange: Pair<Long, Long>
) {

    companion object {

        @JvmStatic
        fun computeAddressBitsCombination(numberOfRequestedAddresses: Int): Array<Int> {
            val binaryStr = numberOfRequestedAddresses.toString(2)
            val length = binaryStr.length
            return binaryStr.mapIndexed { power, digit ->
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
            val reducedIpAddress = ipAddress and
                    IntRange(1, 32).fold(StringBuilder()) { bitStr, bit ->
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
                reducedIpAddress +
                        (0 until address).fold(0L) { offset, index ->
                            offset + Math.pow(
                                    2.0,
                                    addressBitsCombination[index].toDouble()
                            ).toLong()
                        }
            }.toTypedArray()
        }

        @JvmStatic
        fun computeCidrNotation(ipAddress: String, numberOfMaskBits: Int): String {
            return "$ipAddress/$numberOfMaskBits"
        }

        @JvmStatic
        fun computeCidrNetmask(numberOfMaskBits: Int): Long {
            return IntRange(1, 32).fold(StringBuilder()) { bitStr, bit ->
                bitStr.append(
                        if (bit <= numberOfMaskBits) {
                            '1'
                        } else {
                            '0'
                        }
                )
            }.toString()
            .toLong(2)
        }

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
        fun computeIpAddressRange(initialIpAddress: Long, wildcardMask: Long) =
                Pair(initialIpAddress, initialIpAddress + wildcardMask)

        @JvmStatic
        fun compute(ipAddress: String, numberOfRequestedAddresses: Int): Array<Cidr> {
            val addressBitsCombination = computeAddressBitsCombination(numberOfRequestedAddresses)
            val initialIpAddresses = computeInitialIpAddresses(IpConverter.toBinary(ipAddress), addressBitsCombination)
            return initialIpAddresses.zip(addressBitsCombination).map { (initialIpAddress, numberOfAddressBits) ->
                val numberOfMaskBits = 32 - numberOfAddressBits
                val notation = computeCidrNotation(IpConverter.toIpAddress(initialIpAddress), numberOfMaskBits)
                val netmask = computeCidrNetmask(numberOfMaskBits)
                val wildcard = computeWildcardMask(numberOfAddressBits)
                val addresses = computeIpAddressRange(initialIpAddress, wildcard)
                Cidr(notation, netmask, wildcard, addresses)
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