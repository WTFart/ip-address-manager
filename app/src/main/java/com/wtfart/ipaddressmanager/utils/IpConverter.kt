package com.wtfart.ipaddressmanager.utils

/**
 * Created by mickeycj on 11/5/2017 AD.
 */
class IpConverter {

    companion object {

        @JvmStatic
        fun toBinary(ipAddress: String) = ipAddress.split('.')
                .foldIndexed(0b0L) { index, binary, byte ->
                    binary + byte.toLong() * Math.pow(2.0, (24 - 8 * index).toDouble()).toLong()
                }

        @JvmStatic
        fun toIpAddress(binary: Long) = ""
    }
}