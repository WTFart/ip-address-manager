package com.wtfart.ipaddressmanager.model

import java.io.Serializable

import com.google.firebase.database.PropertyName

data class Network(
        @get:PropertyName("id")
        @set:PropertyName("id")
        var id: String,

        @get:PropertyName("name")
        @set:PropertyName("name")
        var name: String,

        @get:PropertyName("cidr_notations")
        @set:PropertyName("cidr_notations")
        var cidrNotations: List<Cidr>
) : Serializable {

    constructor() : this("", "", mutableListOf())

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
