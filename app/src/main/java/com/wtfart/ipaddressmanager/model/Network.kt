package com.wtfart.ipaddressmanager.model

import com.google.firebase.database.PropertyName

/**
 * Created by mickeycj on 11/15/2017.
 */
data class Network(
        @get:PropertyName("network_id")
        @set:PropertyName("network_id")
        var id: String,
        @get:PropertyName("name")
        @set:PropertyName("name")
        var name: String,
        @get:PropertyName("cidr_notations")
        @set:PropertyName("cidr_notations")
        var cidrNotations: List<Cidr>
) {

    constructor() : this(
            "",
            "",
            mutableListOf()
    )

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
