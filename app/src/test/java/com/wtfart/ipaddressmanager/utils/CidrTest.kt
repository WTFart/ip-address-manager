package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

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
    fun `requesting 3 addresses should result in 2 address bits of 2 and 1`() {
        val addressBitsCombination = Cidr.computeAddressBitsCombination(3)
        assertArrayEquals(arrayOf(2, 1), addressBitsCombination)
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