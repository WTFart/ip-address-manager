package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

    @Test
    fun `1 address bit of 0 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|15`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(0))
        assertArrayEquals(arrayOf("192.30.12.15"), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 1 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|14`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(1))
        assertArrayEquals(arrayOf("192.30.12.14"), initialIpAddresses)
    }

    @Test
    fun `2 address bits of 1 and 0 with IP address of 192|30|12|15 should result in 2 initial IP addresses of 192|30|12|14 and 192|30|12|16`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(1, 0))
        assertArrayEquals(arrayOf("192.30.12.14", "192.30.12.16"), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 3 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|8`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(3))
        assertArrayEquals(arrayOf("192.30.12.8"), initialIpAddresses)
    }

    @Test
    fun `3 address bits of 3, 1 and 0 with IP address of 192|30|12|15 should result in 3 initial IP addresses of 192|30|12|8, 192|30|12|16, and 192|30|12|18`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(3, 1, 0))
        assertArrayEquals(arrayOf("192.30.12.8", "192.30.12.16", "192.30.12.18"), initialIpAddresses)
    }

    @Test
    fun `4 address bits of 6, 5, 3 and 2 with IP address of 192|30|12|15 should result in 4 initial IP addresses of 192|30|12|0, 192|30|12|64, 192|30|12|96, and 192|30|12|104`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(6, 5, 3, 2))
        assertArrayEquals(arrayOf("192.30.12.0", "192.30.12.64", "192.30.12.96", "192.30.12.104"), initialIpAddresses)
    }

    @Test
    fun `5 address bits of 6, 5, 4, 3 and 0 with IP address of 192|30|12|15 should result in 5 initial IP addresses of 192|30|12|0, 192|30|12|64, 192|30|12|96, 192|30|12|112, and 192|30|12|120`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(6, 5, 4, 3, 0))
        assertArrayEquals(arrayOf("192.30.12.0", "192.30.12.64", "192.30.12.96", "192.30.12.112", "192.30.12.120"), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 7 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|12|0`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(7))
        assertArrayEquals(arrayOf("192.30.12.0"), initialIpAddresses)
    }

    @Test
    fun `6 address bits of 9, 6, 3, 2, 1 and 0 with IP address of 192|30|12|15 should result in 6 initial IP addresses of 192|30|12|0, 192|30|14|0, 192|30|14|64, 192|30|14|72, 192|30|14|76, and 192|30|14|78`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(9, 6, 3, 2, 1, 0))
        assertArrayEquals(arrayOf("192.30.12.0", "192.30.14.0", "192.30.14.64", "192.30.14.72", "192.30.14.76", "192.30.14.78"), initialIpAddresses)
    }

    @Test
    fun `7 address bits of 10, 8, 6, 4, 2, 1 and 0 with IP address of 192|30|12|15 should result in 7 initial IP addresses of 192|30|12|0, 192|30|16|0, 192|30|17|0, 192|30|17|64, 192|30|17|80, 192|30|17|84, and 192|30|17|86`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(10, 8, 6, 4, 2, 1, 0))
        assertArrayEquals(arrayOf("192.30.12.0", "192.30.16.0", "192.30.17.0", "192.30.17.64", "192.30.17.80", "192.30.17.84", "192.30.17.86"), initialIpAddresses)
    }

    @Test
    fun `8 address bits of 13, 11, 8, 7, 5, 4, 3 and 1 with IP address of 192|30|12|15 should result in 8 initial IP addresses of 192|30|0|0, 192|30|32|0, 192|30|40|0, 192|30|41|0, 192|30|41|128, 192|30|41|160, 192|30|41|176, and 192|30|41|184`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(13, 11, 8, 7, 5, 4, 3, 1))
        assertArrayEquals(arrayOf("192.30.0.0", "192.30.32.0", "192.30.40.0", "192.30.41.0", "192.30.41.128", "192.30.41.160", "192.30.41.176", "192.30.41.184"), initialIpAddresses)
    }

    @Test
    fun `1 address bit of 16 with IP address of 192|30|12|15 should result in 1 initial IP address of 192|30|0|0`() {
        val initialIpAddresses = Cidr.computeInitialIpAddresses(arrayOf(16))
        assertArrayEquals(arrayOf("192.30.0.0"), initialIpAddresses)
    }
}