package org.deban.cpvault.homeScreen.data.mapper

import org.deban.cpvault.core.network.model.leetcode.LanguageStats
import org.deban.cpvault.core.network.model.leetcode.UserContestRankingHistory
import org.deban.cpvault.homeScreen.domain.model.ContestStats
import org.deban.cpvault.homeScreen.domain.model.DomainLanguageStats
import kotlin.math.roundToInt

fun List<UserContestRankingHistory>.toDomainListOfContestStats(): List<ContestStats> {
    return filter { it ->
        it.attended
    }.map { it ->
        ContestStats(
            contestName = it.contest.title,
            problemSolved = it.problemsSolved,
            totalProblems = it.totalProblems,
            rank = it.ranking,
            newRating = it.rating.roundToInt(),
            treadDirection = it.trendDirection,
            startTime = it.contest.startTime
        )
    }.sortedBy { it -> it.startTime }
}

fun LanguageStats.toDomainListOfLanguageStats(totalQuestions: Int): List<DomainLanguageStats> {
    return this.matchedUser.languageProblemCount.map { it ->
        DomainLanguageStats(
            name = it.languageName,
            solvedQuestion = it.problemsSolved,
            percentage = it.problemsSolved.toDouble()/totalQuestions
        )
    }.sortedBy { it -> -it.solvedQuestion }
}