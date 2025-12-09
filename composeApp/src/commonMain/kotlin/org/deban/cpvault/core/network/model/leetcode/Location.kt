package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("column")
    val column: Int,
    @SerialName("line")
    val line: Int
)