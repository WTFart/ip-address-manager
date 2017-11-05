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
}