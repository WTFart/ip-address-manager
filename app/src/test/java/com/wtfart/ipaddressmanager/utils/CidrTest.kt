package com.wtfart.ipaddressmanager.utils

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
                "255.255.255.252",
                "0.0.0.3",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1",
                        "192.30.12.2",
                        "192.30.12.3"
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/30",
                "255.255.255.252",
                "0.0.0.3",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1",
                        "192.30.12.2",
                        "192.30.12.3"
                )
        )
        assertTrue(cidr1 == cidr2)
    }

    @Test
    fun `CIDRs with the same notation should have the same hashcode`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                "255.255.255.252",
                "0.0.0.3",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1",
                        "192.30.12.2",
                        "192.30.12.3"
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/30",
                "255.255.255.252",
                "0.0.0.3",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1",
                        "192.30.12.2",
                        "192.30.12.3"
                )
        )
        assertTrue(cidr1.hashCode() == cidr2.hashCode())
    }

    @Test
    fun `CIDRs with different notations should not be equal`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                "255.255.255.252",
                "0.0.0.3",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1",
                        "192.30.12.2",
                        "192.30.12.3"
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/31",
                "255.255.255.254",
                "0.0.0.1",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1"
                )
        )
        assertFalse(cidr1 == cidr2)
    }

    @Test
    fun `CIDRs with different notations should have different hashcode`() {
        val cidr1 = Cidr(
                "192.30.12.0/30",
                "255.255.255.252",
                "0.0.0.3",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1",
                        "192.30.12.2",
                        "192.30.12.3"
                )
        )
        val cidr2 = Cidr(
                "192.30.12.0/31",
                "255.255.255.254",
                "0.0.0.1",
                arrayOf(
                        "192.30.12.0",
                        "192.30.12.1"
                )
        )
        assertFalse(cidr1.hashCode() == cidr2.hashCode())
    }
}