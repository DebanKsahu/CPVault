package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeUserFullProfile(
    @SerialName("contributionPoint")
    val contributionPoint: Int,
    @SerialName("easySolved")
    val easySolved: Int,
    @SerialName("hardSolved")
    val hardSolved: Int,
    @SerialName("matchedUserStats")
    val matchedUserStats: MatchedUserStats,
    @SerialName("mediumSolved")
    val mediumSolved: Int,
    @SerialName("ranking")
    val ranking: Int,
    @SerialName("recentSubmissions")
    val recentSubmissions: List<RecentSubmission>,
    @SerialName("reputation")
    val reputation: Int,
    @SerialName("submissionCalendar")
    val submissionCalendar: SubmissionCalendar,
    @SerialName("totalEasy")
    val totalEasy: Int,
    @SerialName("totalHard")
    val totalHard: Int,
    @SerialName("totalMedium")
    val totalMedium: Int,
    @SerialName("totalQuestions")
    val totalQuestions: Int,
    @SerialName("totalSolved")
    val totalSolved: Int,
    @SerialName("totalSubmissions")
    val totalSubmissions: List<TotalSubmission>
)