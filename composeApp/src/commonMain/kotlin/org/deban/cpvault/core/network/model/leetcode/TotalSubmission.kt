package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalSubmission(
    @SerialName("count")
    val count: Int,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("submissions")
    val submissions: Int
)