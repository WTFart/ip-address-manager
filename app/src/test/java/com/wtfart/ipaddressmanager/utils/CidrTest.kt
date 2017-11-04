package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

    @Test
    fun `0 address bit should result in 0|0|255|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(0)
        assertEquals(0b00000000000000000000000000000000, wildcardMask)
    }

    @Test
    fun `1 address bit should result in 0|0|127|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(1)
        assertEquals(0b00000000000000000000000000000001, wildcardMask)
    }

    @Test
    fun `2 address bits should result in 0|0|63|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(2)
        assertEquals(0b00000000000000000000000000000011, wildcardMask)
    }

    @Test
    fun `3 address bits should result in 0|0|31|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(3)
        assertEquals(0b00000000000000000000000000000111, wildcardMask)
    }

    @Test
    fun `4 address bits should result in 0|0|15|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(4)
        assertEquals(0b00000000000000000000000000001111, wildcardMask)
    }

    @Test
    fun `5 address bits should result in 0|0|7|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(5)
        assertEquals(0b00000000000000000000000000011111, wildcardMask)
    }

    @Test
    fun `6 address bits should result in 0|0|3|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(6)
        assertEquals(0b00000000000000000000000000111111, wildcardMask)
    }

    @Test
    fun `7 address bits should result in 0|0|1|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(7)
        assertEquals(0b00000000000000000000000001111111, wildcardMask)
    }

    @Test
    fun `8 address bits should result in 0|0|0|255 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(8)
        assertEquals(0b00000000000000000000000011111111, wildcardMask)
    }

    @Test
    fun `9 address bits should result in 0|0|0|127 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(9)
        assertEquals(0b00000000000000000000000111111111, wildcardMask)
    }

    @Test
    fun `10 address bits should result in 0|0|0|63 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(10)
        assertEquals(0b00000000000000000000001111111111, wildcardMask)
    }

    @Test
    fun `11 address bits should result in 0|0|0|31 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(11)
        assertEquals(0b00000000000000000000011111111111, wildcardMask)
    }

    @Test
    fun `12 address bits should result in 0|0|0|15 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(12)
        assertEquals(0b00000000000000000000111111111111, wildcardMask)
    }

    @Test
    fun `13 address bits should result in 0|0|0|7 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(13)
        assertEquals(0b00000000000000000001111111111111, wildcardMask)
    }

    @Test
    fun `14 address bits should result in 0|0|0|3 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(14)
        assertEquals(0b00000000000000000011111111111111, wildcardMask)
    }

    @Test
    fun `15 address bits should result in 0|0|0|1 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(15)
        assertEquals(0b00000000000000000111111111111111, wildcardMask)
    }

    @Test
    fun `16 address bits should result in 0|0|0|0 wildcard mask`() {
        val wildcardMask = Cidr.computeWildcardMask(16)
        assertEquals(0b00000000000000001111111111111111, wildcardMask)
    }
}