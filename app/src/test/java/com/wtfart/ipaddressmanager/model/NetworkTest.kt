package com.wtfart.ipaddressmanager.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/21/2017.
 */
class NetworkTest {

    @Test
    fun `Same network should be equal`() {
        val network1 = Network(
                "1",
                "Network 1",
                mutableListOf()
        )
        val network2 = Network(
                "1",
                "Network 1",
                mutableListOf()
        )

        assertEquals(network1, network2)
    }

    @Test
    fun `Same network should have the same hashcode`() {
        val network1 = Network(
                "1",
                "Network 1",
                mutableListOf()
        )
        val network2 = Network(
                "1",
                "Network 1",
                mutableListOf()
        )

        assertEquals(network1.hashCode(), network2.hashCode())
    }

    @Test
    fun `Different networks should not be equal`() {
        val network1 = Network(
                "1",
                "Network 1",
                mutableListOf()
        )
        val network2 = Network(
                "2",
                "Network 2",
                mutableListOf(
                        Cidr(
                                "192.30.12.0/30",
                                0b11111111111111111111111111111100,
                                0b00000000000000000000000000000011,
                                Pair(
                                        0b11000000000111100000110000000000,
                                        0b11000000000111100000110000000011
                                )
                        )
                )
        )

        assertNotEquals(network1, network2)
    }

    @Test
    fun `Different networks should have different hashcode`() {
        val network1 = Network(
                "1",
                "Network 1",
                mutableListOf()
        )
        val network2 = Network(
                "2",
                "Network 2",
                mutableListOf(
                        Cidr(
                                "192.30.12.0/30",
                                0b11111111111111111111111111111100,
                                0b00000000000000000000000000000011,
                                Pair(
                                        0b11000000000111100000110000000000,
                                        0b11000000000111100000110000000011
                                )
                        )
                )
        )

        assertNotEquals(network1.hashCode(), network2.hashCode())
    }
}
