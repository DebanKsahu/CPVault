package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeUserBadgeDetail(
    val activeBadge: ActiveBadge,
    val badges: List<Badge>,
    val badgesCount: Int,
    val upcomingBadges: List<UpcomingBadge>
)