package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val userContestRanking: UserContestRanking,
    val userContestRankingHistory: List<UserContestRankingHistory>
)