package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingBadge(
    @SerialName("icon")
    val icon: String,
    @SerialName("name")
    val name: String
)