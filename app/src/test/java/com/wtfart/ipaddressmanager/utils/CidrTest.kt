package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

    @Test
    fun `IP Address of 192|30|12|15 and 32 mask bits should result in 192|30|12|15,32 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000001111, 32)
        assertEquals("192.30.12.15/32", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|15 and 31 mask bits should result in 192|30|12|14,31 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000001110, 31)
        assertEquals("192.30.12.14/31", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|14|72 and 30 mask bits should result in 192|30|14|72,30 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000111001001000, 30)
        assertEquals("192.30.14.72/30", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|8 and 29 mask bits should result in 192|30|12|8,29 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000001000, 29)
        assertEquals("192.30.12.8/29", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|96 and 28 mask bits should result in 192|30|12|96,28 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110001100000, 28)
        assertEquals("192.30.12.96/28", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|64 and 27 mask bits should result in 192|30|12|64,27 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000100000, 27)
        assertEquals("192.30.12.64/27", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|0 and 26 mask bits should result in 192|30|12|0,26 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000000000, 26)
        assertEquals("192.30.12.0/26", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|0 and 25 mask bits should result in 192|30|12|0,25 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000000000, 25)
        assertEquals("192.30.12.0/25", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|16|0 and 24 mask bits should result in 192|30|16|0,24 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100001000000000000, 24)
        assertEquals("192.30.16.0/24", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|0 and 23 mask bits should result in 192|30|12|0,23 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000000000, 23)
        assertEquals("192.30.12.0/22", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|12|0 and 22 mask bits should result in 192|30|12|0,22 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000110000000000, 22)
        assertEquals("192.30.12.0/22", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|32|0 and 21 mask bits should result in 192|30|32|0,21 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100010000000000000, 21)
        assertEquals("192.30.32.0/21", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|0|0 and 19 mask bits should result in 192|30|0|0,19 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000000000000000, 19)
        assertEquals("192.30.0.0/19", cidrNotation)
    }

    @Test
    fun `IP Address of 192|30|0|0 and 16 mask bits should result in 192|30|0|0,16 CIDR notation`() {
        val cidrNotation = Cidr.computeCidrNotation(0b11000000000111100000000000000000, 16)
        assertEquals("192.30.0.0/16", cidrNotation)
    }
}