package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Extensions(
    @SerialName("handled")
    val handled: Boolean
)