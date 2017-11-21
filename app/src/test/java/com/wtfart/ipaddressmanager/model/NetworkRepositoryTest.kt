package com.wtfart.ipaddressmanager.model

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

/**
 * Created by mickeycj on 11/21/2017.
 */
class NetworkRepositoryTest {

    private val networkRepository = NetworkRepository.repository
    private val ipAddressRanges = networkRepository.ipAddressRanges

    @Before
    fun setUp() {
        ipAddressRanges.clear()
        ipAddressRanges.add(Pair(10, 14))
        ipAddressRanges.add(Pair(16, 20))
    }

    @Test
    fun `This IP address range should contain an IP address range of 5 to 10`() {
        val isContained = networkRepository.contains(Pair(5, 10))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 5 to 15`() {
        val isContained = networkRepository.contains(Pair(5, 15))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 5 to 20`() {
        val isContained = networkRepository.contains(Pair(5, 20))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 5 to 25`() {
        val isContained = networkRepository.contains(Pair(5, 25))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 10 to 25`() {
        val isContained = networkRepository.contains(Pair(10, 25))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 15 to 25`() {
        val isContained = networkRepository.contains(Pair(15, 25))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 20 to 25`() {
        val isContained = networkRepository.contains(Pair(20, 25))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should contain an IP address range of 12 to 18`() {
        val isContained = networkRepository.contains(Pair(12, 18))

        assertTrue(isContained)
    }

    @Test
    fun `This IP address range should not contain an IP address range of 1 to 9`() {
        val isContained = networkRepository.contains(Pair(1, 9))

        assertFalse(isContained)
    }

    @Test
    fun `This IP address range should not contain an IP address range of 21 to 30`() {
        val isContained = networkRepository.contains(Pair(21, 30))

        assertFalse(isContained)
    }

    @Test
    fun `This IP address range should not contain an IP address of 15`() {
        val isContained = networkRepository.contains(Pair(15, 15))

        assertFalse(isContained)
    }
}
