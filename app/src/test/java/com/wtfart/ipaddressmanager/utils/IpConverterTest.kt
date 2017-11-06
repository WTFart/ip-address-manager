package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/5/2017 AD.
 */
class IpConverterTest {

    @Test
    fun `IP address of 192|30|0|0 should result in a binary representation of 11000000000111100000000000000000`() {
        assertEquals(0b11000000000111100000000000000000, IpConverter.toBinary("192.30.0.0"))
    }

    @Test
    fun `IP address of 192|30|12|0 should result in a binary representation of 11000000000111100000110000000000`() {
        assertEquals(0b11000000000111100000110000000000, IpConverter.toBinary("192.30.12.0"))
    }

    @Test
    fun `IP address of 192|30|12|8 should result in a binary representation of 11000000000111100000110000001000`() {
        assertEquals(0b11000000000111100000110000001000, IpConverter.toBinary("192.30.12.8"))
    }

    @Test
    fun `IP address of 192|30|12|14 should result in a binary representation of 11000000000111100000110000001110`() {
        assertEquals(0b11000000000111100000110000001110, IpConverter.toBinary("192.30.12.14"))
    }

    @Test
    fun `IP address of 192|30|12|15 should result in a binary representation of 11000000000111100000110000001111`() {
        assertEquals(0b11000000000111100000110000001111, IpConverter.toBinary("192.30.12.15"))
    }

    @Test
    fun `IP address of 192|30|12|16 should result in a binary representation of 11000000000111100000110000010000`() {
        assertEquals(0b11000000000111100000110000010000, IpConverter.toBinary("192.30.12.16"))
    }

    @Test
    fun `IP address of 192|30|12|18 should result in a binary representation of 11000000000111100000110000010010`() {
        assertEquals(0b11000000000111100000110000010010, IpConverter.toBinary("192.30.12.18"))
    }

    @Test
    fun `IP address of 192|30|12|64 should result in a binary representation of 11000000000111100000110001000000`() {
        assertEquals(0b11000000000111100000110001000000, IpConverter.toBinary("192.30.12.64"))
    }

    @Test
    fun `IP address of 192|30|12|96 should result in a binary representation of 11000000000111100000110001100000`() {
        assertEquals(0b11000000000111100000110001100000, IpConverter.toBinary("192.30.12.96"))
    }

    @Test
    fun `IP address of 192|30|12|104 should result in a binary representation of 11000000000111100000110001101000`() {
        assertEquals(0b11000000000111100000110001101000, IpConverter.toBinary("192.30.12.104"))
    }

    @Test
    fun `IP address of 192|30|12|112 should result in a binary representation of 11000000000111100000110001110000`() {
        assertEquals(0b11000000000111100000110001110000, IpConverter.toBinary("192.30.12.112"))
    }

    @Test
    fun `IP address of 192|30|12|120 should result in a binary representation of 11000000000111100000110001111000`() {
        assertEquals(0b11000000000111100000110001111000, IpConverter.toBinary("192.30.12.120"))
    }

    @Test
    fun `IP address of 192|30|14|0 should result in a binary representation of 11000000000111100000111000000000`() {
        assertEquals(0b11000000000111100000111000000000, IpConverter.toBinary("192.30.14.0"))
    }

    @Test
    fun `IP address of 192|30|14|64 should result in a binary representation of 11000000000111100000111001000000`() {
        assertEquals(0b11000000000111100000111001000000, IpConverter.toBinary("192.30.14.64"))
    }

    @Test
    fun `IP address of 192|30|14|72 should result in a binary representation of 11000000000111100000111001001000`() {
        assertEquals(0b11000000000111100000111001001000, IpConverter.toBinary("192.30.14.72"))
    }

    @Test
    fun `IP address of 192|30|14|76 should result in a binary representation of 11000000000111100000111001001100`() {
        assertEquals(0b11000000000111100000111001001100, IpConverter.toBinary("192.30.14.76"))
    }

    @Test
    fun `IP address of 192|30|14|78 should result in a binary representation of 11000000000111100000111001001110`() {
        assertEquals(0b11000000000111100000111001001110, IpConverter.toBinary("192.30.14.78"))
    }

    @Test
    fun `IP address of 192|30|16|0 should result in a binary representation of 11000000000111100001000000000000`() {
        assertEquals(0b11000000000111100001000000000000, IpConverter.toBinary("192.30.16.0"))
    }

    @Test
    fun `IP address of 192|30|17|0 should result in a binary representation of 11000000000111100001000100000000`() {
        assertEquals(0b11000000000111100001000100000000, IpConverter.toBinary("192.30.17.0"))
    }

    @Test
    fun `IP address of 192|30|17|64 should result in a binary representation of 11000000000111100001000101000000`() {
        assertEquals(0b11000000000111100001000101000000, IpConverter.toBinary("192.30.17.64"))
    }

    @Test
    fun `IP address of 192|30|17|80 should result in a binary representation of 11000000000111100001000101010000`() {
        assertEquals(0b11000000000111100001000101010000, IpConverter.toBinary("192.30.17.80"))
    }

    @Test
    fun `IP address of 192|30|17|84 should result in a binary representation of 11000000000111100001000101010100`() {
        assertEquals(0b11000000000111100001000101010100, IpConverter.toBinary("192.30.17.84"))
    }

    @Test
    fun `IP address of 192|30|17|86 should result in a binary representation of 11000000000111100001000101010110`() {
        assertEquals(0b11000000000111100001000101010110, IpConverter.toBinary("192.30.17.86"))
    }

    @Test
    fun `IP address of 192|30|32|0 should result in a binary representation of 11000000000111100010000000000000`() {
        assertEquals(0b11000000000111100010000000000000, IpConverter.toBinary("192.30.32.0"))
    }

    @Test
    fun `IP address of 192|30|40|0 should result in a binary representation of 11000000000111100010100000000000`() {
        assertEquals(0b11000000000111100010100000000000, IpConverter.toBinary("192.30.40.0"))
    }

    @Test
    fun `IP address of 192|30|41|0 should result in a binary representation of 11000000000111100010100100000000`() {
        assertEquals(0b11000000000111100010100100000000, IpConverter.toBinary("192.30.41.0"))
    }

    @Test
    fun `IP address of 192|30|41|128 should result in a binary representation of 11000000000111100010100110000000`() {
        assertEquals(0b11000000000111100010100110000000, IpConverter.toBinary("192.30.41.128"))
    }

    @Test
    fun `IP address of 192|30|41|160 should result in a binary representation of 11000000000111100010100110100000`() {
        assertEquals(0b11000000000111100010100110100000, IpConverter.toBinary("192.30.41.160"))
    }

    @Test
    fun `IP address of 192|30|41|176 should result in a binary representation of 11000000000111100010100110110000`() {
        assertEquals(0b11000000000111100010100110110000, IpConverter.toBinary("192.30.41.176"))
    }

    @Test
    fun `IP address of 192|30|41|184 should result in a binary representation of 11000000000111100010100110111000`() {
        assertEquals(0b11000000000111100010100110111000, IpConverter.toBinary("192.30.41.184"))
    }

//    @Test
//    fun `Binary representation of 11000000000111100000000000000000 should result in an IP address of 192|30|0|0`() {
//        assertEquals("192.30.0.0", IpConverter.toIpAddress(0b11000000000111100000000000000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110000000000 should result in an IP address of 192|30|12|0`() {
//        assertEquals("192.30.12.0", IpConverter.toIpAddress(0b11000000000111100000110000000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110000001000 should result in an IP address of 192|30|12|8`() {
//        assertEquals("192.30.12.8", IpConverter.toIpAddress(0b11000000000111100000110000001000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110000001110 should result in an IP address of 192|30|12|14`() {
//        assertEquals("192.30.12.14", IpConverter.toIpAddress(0b11000000000111100000110000001110))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110000001111 should result in an IP address of 192|30|12|15`() {
//        assertEquals("192.30.12.15", IpConverter.toIpAddress(0b11000000000111100000110000001111))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110000010000 should result in an IP address of 192|30|12|16`() {
//        assertEquals("192.30.12.16", IpConverter.toIpAddress(0b11000000000111100000110000010000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110000010010 should result in an IP address of 192|30|12|18`() {
//        assertEquals("192.30.12.18", IpConverter.toIpAddress(0b11000000000111100000110000010010))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110001000000 should result in an IP address of 192|30|12|64`() {
//        assertEquals("192.30.12.64", IpConverter.toIpAddress(0b11000000000111100000110001000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110001100000 should result in an IP address of 192|30|12|96`() {
//        assertEquals("192.30.12.96", IpConverter.toIpAddress(0b11000000000111100000110001100000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110001101000 should result in an IP address of 192|30|12|104`() {
//        assertEquals("192.30.12.104", IpConverter.toIpAddress(0b11000000000111100000110001101000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110001110000 should result in an IP address of 192|30|12|112`() {
//        assertEquals("192.30.12.112", IpConverter.toIpAddress(0b11000000000111100000110001110000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000110001111000 should result in an IP address of 192|30|12|120`() {
//        assertEquals("192.30.12.120", IpConverter.toIpAddress(0b11000000000111100000110001111000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000111000000000 should result in an IP address of 192|30|14|0`() {
//        assertEquals("192.30.14.0", IpConverter.toIpAddress(0b11000000000111100000111000000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000111001000000 should result in an IP address of 192|30|14|64`() {
//        assertEquals("192.30.14.64", IpConverter.toIpAddress(0b11000000000111100000111001000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000111001001000 should result in an IP address of 192|30|14|72`() {
//        assertEquals("192.30.14.72", IpConverter.toIpAddress(0b11000000000111100000111001001000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000111001001100 should result in an IP address of 192|30|14|76`() {
//        assertEquals("192.30.14.76", IpConverter.toIpAddress(0b11000000000111100000111001001100))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100000111001001110 should result in an IP address of 192|30|14|78`() {
//        assertEquals("192.30.14.78", IpConverter.toIpAddress(0b11000000000111100000111001001110))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100001000000000000 should result in an IP address of 192|30|16|0`() {
//        assertEquals("192.30.16.0", IpConverter.toIpAddress(0b11000000000111100001000000000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100001000100000000 should result in an IP address of 192|30|17|0`() {
//        assertEquals("192.30.17.0", IpConverter.toIpAddress(0b11000000000111100001000100000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100001000101000000 should result in an IP address of 192|30|17|64`() {
//        assertEquals("192.30.17.64", IpConverter.toIpAddress(0b11000000000111100001000101000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100001000101010000 should result in an IP address of 192|30|17|80`() {
//        assertEquals("192.30.17.80", IpConverter.toIpAddress(0b11000000000111100001000101010000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100001000101010100 should result in an IP address of 192|30|17|84`() {
//        assertEquals("192.30.17.84", IpConverter.toIpAddress(0b11000000000111100001000101010100))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100001000101010110 should result in an IP address of 192|30|17|86`() {
//        assertEquals("192.30.17.86", IpConverter.toIpAddress(0b11000000000111100001000101010110))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010000000000000 should result in an IP address of 192|30|32|0`() {
//        assertEquals("192.30.32.0", IpConverter.toIpAddress(0b11000000000111100010000000000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010100000000000 should result in an IP address of 192|30|40|0`() {
//        assertEquals("192.30.40.0", IpConverter.toIpAddress(0b11000000000111100010100000000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010100100000000 should result in an IP address of 192|30|41|0`() {
//        assertEquals("192.30.41.0", IpConverter.toIpAddress(0b11000000000111100010100100000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010100110000000 should result in an IP address of 192|30|41|128`() {
//        assertEquals("192.30.41.128", IpConverter.toIpAddress(0b11000000000111100010100110000000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010100110100000 should result in an IP address of 192|30|41|160`() {
//        assertEquals("192.30.41.160", IpConverter.toIpAddress(0b11000000000111100010100110100000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010100110110000 should result in an IP address of 192|30|41|176`() {
//        assertEquals("192.30.41.176", IpConverter.toIpAddress(0b11000000000111100010100110110000))
//    }
//
//    @Test
//    fun `Binary representation of 11000000000111100010100110111000 should result in an IP address of 192|30|41|184`() {
//        assertEquals("192.30.41.184", IpConverter.toIpAddress(0b11000000000111100010100110111000))
//    }
}