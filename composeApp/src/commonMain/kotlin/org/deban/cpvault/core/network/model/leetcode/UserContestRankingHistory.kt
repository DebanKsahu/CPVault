package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserContestRankingHistory(
    val attended: Boolean,
    val contest: Contest,
    val finishTimeInSeconds: Int,
    val problemsSolved: Int,
    val ranking: Int,
    val rating: Double,
    val totalProblems: Int,
    val trendDirection: String
)