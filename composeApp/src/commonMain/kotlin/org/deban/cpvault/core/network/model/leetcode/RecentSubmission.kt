package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentSubmission(
    @SerialName("lang")
    val lang: String,
    @SerialName("statusDisplay")
    val statusDisplay: String,
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("title")
    val title: String,
    @SerialName("titleSlug")
    val titleSlug: String,
    @SerialName("__typename")
    val typename: String
)