package com.wtfart.ipaddressmanager.model

import java.util.Arrays

/**
 * Created by mickeycj on 11/15/2017.
 */
data class Network(val id: String, val name: String, val cidrNotations: List<Cidr>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Network

        return id == other.id && name == other.name && cidrNotations == other.cidrNotations
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + cidrNotations.hashCode()

        return result
    }
}
