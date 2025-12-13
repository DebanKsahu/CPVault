package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeUserFullProfile(
    val contributionPoint: Int,
    val easySolved: Int,
    val hardSolved: Int,
    val matchedUserStats: MatchedUserStats? = null,
    val mediumSolved: Int,
    val ranking: Int,
    val recentSubmissions: List<RecentSubmission>,
    val reputation: Int,
    val totalEasy: Int,
    val totalHard: Int,
    val totalMedium: Int,
    val totalQuestions: Int,
    val totalSolved: Int,
    val totalSubmissions: List<TotalSubmission>
)