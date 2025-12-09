package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserContestRanking(
    val attendedContestsCount: Int,
    val badge: BadgeX,
    val globalRanking: Int,
    val rating: Double,
    val topPercentage: Double,
    val totalParticipants: Int
)