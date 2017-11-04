package com.wtfart.ipaddressmanager.utils

import org.junit.Assert.assertArrayEquals
import org.junit.Test
/**
 * Created by mickeycj on 11/2/2017 AD.
 */
class CidrTest {

    @Test
    fun `IP address of 192|30|12|15 and 0 address bit should result in 1 available IP address of 192|30|12|15`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000001111, 0)
        assertArrayEquals(arrayOf(0b11000000000111100000110000001111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|14 and 1 address bit should result in 2 available IP addresses of 192|30|12|14 - 192|30|12|15`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000001110, 1)
        assertArrayEquals(arrayOf(0b11000000000111100000110000001110, 0b11000000000111100000110000001111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|14|72 and 2 address bits should result in 4 available IP addresses of 192|30|14|72 - 192|30|14|75`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000111001001000, 2)
        assertArrayEquals(arrayOf(0b11000000000111100000111001001000, 0b11000000000111100000111001001011), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|8 and 3 address bits should result in 8 available IP addresses of 192|30|12|8 - 192|30|12|15`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000001000, 3)
        assertArrayEquals(arrayOf(0b11000000000111100000110000001000, 0b11000000000111100000110000001111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|96 and 4 address bits should result in 16 available IP addresses of 192|30|12|96 - 192|30|12|111`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110001100000, 4)
        assertArrayEquals(arrayOf(0b11000000000111100000110001100000, 0b11000000000111100000110000001111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|64 and 5 address bits should result in 32 available IP addresses of 192|30|12|64 - 192|30|12|95`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110001011111, 5)
        assertArrayEquals(arrayOf(0b11000000000111100000110001000000, 0b11000000000111100000110001011111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|0 and 6 address bits should result in 64 available IP addresses of 192|30|12|0 - 192|30|12|63`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000000000, 6)
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000110000111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|0 and 7 address bits should result in 128 available IP addresses of 192|30|12|0 - 192|30|12|127`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000000000, 7)
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000110001111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|16|0 and 8 address bits should result in 256 available IP addresses of 192|30|16|0 - 192|30|16|255`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100001000000000000, 8)
        assertArrayEquals(arrayOf(0b11000000000111100001000000000000, 0b11000000000111100001000011111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|0 and 9 address bits should result in 512 available IP addresses of 192|30|12|0 - 192|30|13|255`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000000000, 9)
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000110111111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|12|0 and 10 address bits should result in 1024 available IP addresses of 192|30|12|0 - 192|30|15|255`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000110000000000, 10)
        assertArrayEquals(arrayOf(0b11000000000111100000110000000000, 0b11000000000111100000111111111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|32|0 and 11 address bits should result in 2048 available IP addresses of 192|30|32|0 - 192|30|39|255`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100010000000000000, 11)
        assertArrayEquals(arrayOf(0b11000000000111100010000000000000, 0b11000000000111100010011111111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|0|0 and 13 address bits should result in 8192 available IP addresses of 192|30|0|0 - 192|30|31|255`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000000000000000, 13)
        assertArrayEquals(arrayOf(0b11000000000111100000000000000000, 0b11000000000111100001111111111111), availableIpAddresses)
    }

    @Test
    fun `IP address of 192|30|0|0 and 16 address bits should result in 65536 available IP addresses of 192|30|0|0 - 192|30|255|255`() {
        val availableIpAddresses = Cidr.computeAvailableIpAddresses(0b11000000000111100000000000000000, 16)
        assertArrayEquals(arrayOf(0b11000000000111100000000000000000, 0b11000000000111101111111111111111), availableIpAddresses)
    }
}
