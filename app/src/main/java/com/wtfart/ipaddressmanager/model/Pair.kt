package com.wtfart.ipaddressmanager.model

import com.google.firebase.database.PropertyName

/**
 * Created by mickeycj on 11/16/2017.
 */
data class Pair(
        @get:PropertyName("first")
        @set:PropertyName("first")
        var first: Long,
        @get:PropertyName("second")
        @set:PropertyName("second")
        var second: Long
) {

    constructor() : this(0b0, 0b0)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pair

        return first == other.first && second == other.second
    }

    override fun hashCode(): Int {
        var result = first.hashCode()
        result = 31 * result + second.hashCode()

        return result
    }
}
