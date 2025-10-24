package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchedUserStats(
    @SerialName("acSubmissionNum")
    val acSubmissionNum: List<AcSubmissionNum>,
    @SerialName("totalSubmissionNum")
    val totalSubmissionNum: List<TotalSubmissionNum>
)