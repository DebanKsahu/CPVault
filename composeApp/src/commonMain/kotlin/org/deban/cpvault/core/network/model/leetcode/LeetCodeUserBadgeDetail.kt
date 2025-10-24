package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeUserBadgeDetail(
    @SerialName("activeBadge")
    val activeBadge: ActiveBadge,
    @SerialName("badges")
    val badges: List<Badge>,
    @SerialName("badgesCount")
    val badgesCount: Int,
    @SerialName("upcomingBadges")
    val upcomingBadges: List<UpcomingBadge>
)