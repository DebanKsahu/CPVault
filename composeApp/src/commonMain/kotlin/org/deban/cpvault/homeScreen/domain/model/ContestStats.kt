package org.deban.cpvault.homeScreen.domain.model

data class ContestStats(
    val contestName: String,
    val problemSolved: Int,
    val totalProblems: Int,
    val rank: Int,
    val newRating: Int,
    val treadDirection: String,
    val startTime: Long
)
