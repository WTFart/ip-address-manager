package com.wtfart.ipaddressmanager.model

/**
 * Created by mickeycj on 11/16/2017.
 */
data class Pair(var first: Long, var second: Long) {

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
