package com.wtfart.ipaddressmanager.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Created by mickeycj on 11/21/2017.
 */
class PairTest {

    @Test
    fun `Pair with the same values should be equal`() {
        val pair1 = Pair(1, 2)
        val pair2 = Pair(1, 2)

        assertEquals(pair1, pair2)
    }

    @Test
    fun `Pair with the same values should have the same hashcode`() {
        val pair1 = Pair(1, 2)
        val pair2 = Pair(1, 2)

        assertEquals(pair1.hashCode(), pair2.hashCode())
    }

    @Test
    fun `Pair with different values should not be equal`() {
        val pair1 = Pair(1, 2)
        val pair2 = Pair(2, 4)

        assertNotEquals(pair1, pair2)
    }

    @Test
    fun `Pair with different values should have different hashcode`() {
        val pair1 = Pair(1, 2)
        val pair2 = Pair(2, 4)

        assertNotEquals(pair1.hashCode(), pair2.hashCode())
    }
}
