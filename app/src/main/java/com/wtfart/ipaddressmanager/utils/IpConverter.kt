package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/5/2017 AD.
 */
class IpConverter {

    companion object {

        @JvmStatic
        fun toBinary(ipAddress: String): Long {
            return ipAddress.split('.')
                    .foldIndexed(0b0L) { index, binary, byte ->
                        binary + byte.toLong() * Math.pow(2.0, (24 - 8 * index).toDouble()).toLong()
                    }
        }

        @JvmStatic
        fun toIpAddress(binary: Long): String {
            return LongArray(4, { binary }).mapIndexed { index, bin ->
                        bin / Math.pow(2.0, (24 - 8 * index).toDouble()).toLong()
                    }.fold(StringBuilder()) { ipAddress, bin ->
                        ipAddress.append('.').append(bin % Math.pow(2.0, 8.0).toLong())
                    }.substring(1)
                    .toString()
        }
    }
}