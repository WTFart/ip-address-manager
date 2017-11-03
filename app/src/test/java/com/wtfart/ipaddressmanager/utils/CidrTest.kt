package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

    @Test
    fun `16 mask bits should result in 255|255|0|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(16)
        assertEquals("255.255.0.0", cidrNetmask)
    }

    @Test
    fun `17 mask bits should result in 255|255|128|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(17)
        assertEquals("255.255.128.0", cidrNetmask)
    }

    @Test
    fun `18 mask bits should result in 255|255|192|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(18)
        assertEquals("255.255.192.0", cidrNetmask)
    }

    @Test
    fun `19 mask bits should result in 255|255|224|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(19)
        assertEquals("255.255.224.0", cidrNetmask)
    }

    @Test
    fun `20 mask bits should result in 255|255|240|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(20)
        assertEquals("255.255.240.0", cidrNetmask)
    }

    @Test
    fun `21 mask bits should result in 255|255|248|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(21)
        assertEquals("255.255.248.0", cidrNetmask)
    }

    @Test
    fun `22 mask bits should result in 255|255|252|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(22)
        assertEquals("255.255.252.0", cidrNetmask)
    }

    @Test
    fun `23 mask bits should result in 255|255|254|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(23)
        assertEquals("255.255.254.0", cidrNetmask)
    }

    @Test
    fun `24 mask bits should result in 255|255|255|0 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(24)
        assertEquals("255.255.255.0", cidrNetmask)
    }

    @Test
    fun `25 mask bits should result in 255|255|255|128 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(25)
        assertEquals("255.255.255.128", cidrNetmask)
    }

    @Test
    fun `26 mask bits should result in 255|255|255|192 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(26)
        assertEquals("255.255.255.192", cidrNetmask)
    }

    @Test
    fun `27 mask bits should result in 255|255|255|224 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(27)
        assertEquals("255.255.255.224", cidrNetmask)
    }

    @Test
    fun `28 mask bits should result in 255|255|255|240 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(28)
        assertEquals("255.255.255.240", cidrNetmask)
    }

    @Test
    fun `29 mask bits should result in 255|255|255|248 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(29)
        assertEquals("255.255.255.248", cidrNetmask)
    }

    @Test
    fun `30 mask bits should result in 255|255|255|252 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(30)
        assertEquals("255.255.255.252", cidrNetmask)
    }

    @Test
    fun `31 mask bits should result in 255|255|255|254 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(31)
        assertEquals("255.255.255.254", cidrNetmask)
    }

    @Test
    fun `32 mask bits should result in 255|255|255|255 CIDR netmask`() {
        val cidrNetmask = Cidr.computeCidrNetmask(32)
        assertEquals("255.255.255.255", cidrNetmask)
    }
}