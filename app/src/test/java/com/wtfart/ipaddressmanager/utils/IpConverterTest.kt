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
}