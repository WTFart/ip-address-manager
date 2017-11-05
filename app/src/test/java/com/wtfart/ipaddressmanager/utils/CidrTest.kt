package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

    @Test
    fun `CIDRs with the same notation should be equal`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                0b11111111111111111111111111111100,
                0b00000000000000000000000000000011,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001,
                        0b11000000000111100000110000000010,
                        0b11000000000111100000110000000011
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/30",
                0b11111111111111111111111111111100,
                0b00000000000000000000000000000011,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001,
                        0b11000000000111100000110000000010,
                        0b11000000000111100000110000000011
                )
        )
        assertTrue(cidr1 == cidr2)
    }

    @Test
    fun `CIDRs with the same notation should have the same hashcode`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                0b11111111111111111111111111111100,
                0b00000000000000000000000000000011,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001,
                        0b11000000000111100000110000000010,
                        0b11000000000111100000110000000011
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/30",
                0b11111111111111111111111111111100,
                0b00000000000000000000000000000011,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001,
                        0b11000000000111100000110000000010,
                        0b11000000000111100000110000000011
                )
        )
        assertTrue(cidr1.hashCode() == cidr2.hashCode())
    }

    @Test
    fun `CIDRs with different notations should not be equal`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                0b11111111111111111111111111111100,
                0b00000000000000000000000000000011,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001,
                        0b11000000000111100000110000000010,
                        0b11000000000111100000110000000011
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/31",
                0b11111111111111111111111111111110,
                0b00000000000000000000000000000001,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001
                )
        )
        assertFalse(cidr1 == cidr2)
    }

    @Test
    fun `CIDRs with different notations should have different hashcode`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                0b11111111111111111111111111111100,
                0b00000000000000000000000000000011,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001,
                        0b11000000000111100000110000000010,
                        0b11000000000111100000110000000011
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/31",
                0b11111111111111111111111111111110,
                0b00000000000000000000000000000001,
                arrayOf(
                        0b11000000000111100000110000000000,
                        0b11000000000111100000110000000001
                )
        )
        assertFalse(cidr1.hashCode() == cidr2.hashCode())
    }

    @Test
    fun `requesting 1 address should result in 1 address bit of 0`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(1)
        assertArrayEquals(arrayOf(0), addressBitsCombination)
    }

    @Test
    fun `requesting 2 addresses should result in an address bit of 1`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(2)
        assertArrayEquals(arrayOf(1), addressBitsCombination)
    }

    @Test
    fun `requesting 3 addresses should result in 2 address bits of 1 and 0`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(3)
        assertArrayEquals(arrayOf(1, 0), addressBitsCombination)
    }

    @Test
    fun `requesting 8 addresses should result in an address bit of 3`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(8)
        assertArrayEquals(arrayOf(3), addressBitsCombination)
    }

    @Test
    fun `requesting 11 addresses should result in 3 address bits of 3, 1, and 0`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(11)
        assertArrayEquals(arrayOf(3, 1, 0), addressBitsCombination)
    }

    @Test
    fun `requesting 108 addresses should result in 4 address bits of 6, 5, 3, and 2`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(108)
        assertArrayEquals(arrayOf(6, 5, 3, 2), addressBitsCombination)
    }

    @Test
    fun `requesting 121 addresses should result in 5 address bits of 6, 5, 4, 3, and 0`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(121)
        assertArrayEquals(arrayOf(6, 5, 4, 3, 0), addressBitsCombination)
    }

    @Test
    fun `requesting 128 addresses should result in an address bit of 7`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(128)
        assertArrayEquals(arrayOf(7), addressBitsCombination)
    }

    @Test
    fun `requesting 591 addresses should result in 6 address bits of 9, 6, 3, 2, 1, and 0`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(591)
        assertArrayEquals(arrayOf(9, 6, 3, 2, 1, 0), addressBitsCombination)
    }

    @Test
    fun `requesting 1367 addresses should result in 7 address bits of 10, 8, 6, 4, 2, 1, and 0`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(1367)
        assertArrayEquals(arrayOf(10, 8, 6, 4, 2, 1, 0), addressBitsCombination)
    }

    @Test
    fun `requesting 10682 addresses should result in 8 address bits of 13, 11, 8, 7, 5, 4, 3, and 1`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(10682)
        assertArrayEquals(arrayOf(13, 11, 8, 7, 5, 4, 3, 1), addressBitsCombination)
    }

    @Test
    fun `requesting 65536 addresses should result in an address bit of 16`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(65536)
        assertArrayEquals(arrayOf(16), addressBitsCombination)
    }

    @Test
    fun `1 address bit of 0 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|15`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(0))
        assertArrayEquals(arrayOf(0b11000000000111100000110000001111), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 1 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|14`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(1))
        assertArrayEquals(arrayOf(0b11000000000111100000110000001110), initialIpAddresses)
    }

    @Test
    fun `2 address bits of 1 and 0 with IP address of 192|30|12|15 should result in 2 initial IP addresses of 192|30|12|14 and 192|30|12|16`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(1, 0))
        assertArrayEquals(arrayOf(0b11000000000111100000110000001110, 0b11000000000111100000110000010000), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 3 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|8`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(3))
        assertArrayEquals(arrayOf(0b11000000000111100000110000001000), initialIpAddresses)
    }

    @Test
    fun `3 address bits of 3, 1 and 0 with IP address of 192|30|12|15 should result in 3 initial IP addresses of 192|30|12|8, 192|30|12|16, and 192|30|12|18`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(3, 1, 0))
        assertArrayEquals(arrayOf(0b11000000000111100000110000001000, 0b11000000000111100000110000010000, 0b11000000000111100000110000010010), initialIpAddresses)
    }

    @Test
    fun `4 address bits of 6, 5, 3 and 2 with IP address of 192|30|12|15 should result in 4 initial IP addresses of 192|30|12|0, 192|30|12|64, 192|30|12|96, and 192|30|12|104`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(6, 5, 3, 2))
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000110001000000, 0b11000000000111100000110001100000, 0b11000000000111100000110001101000), initialIpAddresses)
    }

    @Test
    fun `5 address bits of 6, 5, 4, 3 and 0 with IP address of 192|30|12|15 should result in 5 initial IP addresses of 192|30|12|0, 192|30|12|64, 192|30|12|96, 192|30|12|112, and 192|30|12|120`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(6, 5, 4, 3, 0))
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000110001000000, 0b11000000000111100000110001100000, 0b11000000000111100000110001110000, 0b11000000000111100000110001111000), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 7 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|0`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(7))
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000), initialIpAddresses)
    }

    @Test
    fun `6 address bits of 9, 6, 3, 2, 1 and 0 with IP address of 192|30|12|15 should result in 6 initial IP addresses of 192|30|12|0, 192|30|14|0, 192|30|14|64, 192|30|14|72, 192|30|14|76, and 192|30|14|78`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(9, 6, 3, 2, 1, 0))
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000111000000000, 0b11000000000111100000111001000000, 0b11000000000111100000111001001000, 0b11000000000111100000111001001100, 0b11000000000111100000111001001110), initialIpAddresses)
    }

    @Test
    fun `7 address bits of 10, 8, 6, 4, 2, 1 and 0 with IP address of 192|30|12|15 should result in 7 initial IP addresses of 192|30|12|0, 192|30|16|0, 192|30|17|0, 192|30|17|64, 192|30|17|80, 192|30|17|84, and 192|30|17|86`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(10, 8, 6, 4, 2, 1, 0))
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100001000000000000, 0b11000000000111100001000100000000, 0b11000000000111100001000101000000, 0b11000000000111100001000101010000, 0b11000000000111100001000101010100, 0b11000000000111100001000101010110), initialIpAddresses)
    }

    @Test
    fun `8 address bits of 13, 11, 8, 7, 5, 4, 3 and 1 with IP address of 192|30|12|15 should result in 8 initial IP addresses of 192|30|0|0, 192|30|32|0, 192|30|40|0, 192|30|41|0, 192|30|41|128, 192|30|41|160, 192|30|41|176, and 192|30|41|184`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(13, 11, 8, 7, 5, 4, 3, 1))
        assertArrayEquals(arrayOf(0b11000000000111100000000000000000, 0b11000000000111100010000000000000, 0b11000000000111100010100000000000, 0b11000000000111100010100100000000, 0b11000000000111100010100110000000, 0b11000000000111100010100110100000, 0b11000000000111100010100110110000, 0b11000000000111100010100110111000), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 16 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|0|0`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(0b11000000000111100000110000001111, arrayOf(16))
        assertArrayEquals(arrayOf(0b11000000000111100000000000000000), initialIpAddresses)
    }
}