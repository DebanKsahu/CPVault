package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActiveBadge(
    @SerialName("creationDate")
    val creationDate: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("id")
    val id: String
)