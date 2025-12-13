package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Badge(
    val creationDate: String,
    val displayName: String,
    val icon: String,
    val id: String
)