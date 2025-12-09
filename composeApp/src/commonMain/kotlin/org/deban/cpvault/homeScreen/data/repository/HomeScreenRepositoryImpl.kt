package org.deban.cpvault.homeScreen.data.repository

import org.deban.cpvault.core.network.apiService.LeetCodeApiService
import org.deban.cpvault.core.network.model.leetcode.DataX
import org.deban.cpvault.core.network.model.leetcode.LanguageStats
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserContestDetail
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserFullProfile
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserProfile
import org.deban.cpvault.core.network.model.leetcode.MatchedUser
import org.deban.cpvault.homeScreen.data.mapper.toDomainListOfContestStats
import org.deban.cpvault.homeScreen.data.mapper.toDomainListOfLanguageStats
import org.deban.cpvault.homeScreen.domain.model.ContestStats
import org.deban.cpvault.homeScreen.domain.model.DomainLanguageStats
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository

class HomeScreenRepositoryImpl(
    private val leetCodeApiService: LeetCodeApiService
): HomeScreenRepository {

    override suspend fun getLeetCodeUserProfile(username: String): Result<LeetCodeUserProfile> {
        val response = leetCodeApiService.getUserProfile(username = username)
        return if (response.isSuccess) {
            Result.success(response.getOrThrow())
        } else {
            Result.failure(response.exceptionOrNull() ?: Exception("Some Exception Occured"))
        }
    }

    override suspend fun getLeetCodeUserContestDetail(username: String): Result<LeetCodeUserContestDetail> {
        val response  = leetCodeApiService.getUserContestDetail(username = username)
        return if (response.isSuccess) {
            Result.success(response.getOrThrow())
        } else {
            Result.failure(response.exceptionOrNull() ?: Exception("Some Error Occured"))
        }
    }

    override suspend fun getLeetCodeUserContestHistory(username: String): Result<List<ContestStats>> {
        val response  = leetCodeApiService.getUserContestDetail(username = username)
        return if (response.isSuccess) {
            val contestHistory = response.getOrNull()?.data?.userContestRankingHistory?.toDomainListOfContestStats() ?: emptyList()
            Result.success(contestHistory)
        } else {
            Result.failure(response.exceptionOrNull() ?: Exception("Some Error Occured"))
        }
    }

    override suspend fun getLeetCodeFullUserProfile(username: String): Result<LeetCodeUserFullProfile> {
        val response = leetCodeApiService.getUserFullProfile(username = username)
        return if (response.isSuccess) {
            Result.success(response.getOrThrow())
        } else {
            Result.failure(response.exceptionOrNull() ?: Exception("Some Exception Occured"))
        }
    }

    override suspend fun getLeetCodeUserLanguageStats(username: String): Result<List<DomainLanguageStats>> {
        val response = leetCodeApiService.getUserLanguageStats(username = username)
        var totalQuestions = 0
        return if (response.isSuccess) {
            val default = LanguageStats(
                data = DataX(username),
                errors = null,
                matchedUser = MatchedUser(
                    languageProblemCount = emptyList()
                )
            )
            val data = response.getOrNull() ?: default
            data.matchedUser.languageProblemCount.forEach {
                totalQuestions += it.problemsSolved
            }
            Result.success(data.toDomainListOfLanguageStats(totalQuestions = totalQuestions))

        } else {
            Result.failure(response.exceptionOrNull() ?: Exception("Some Error Occured"))
        }
    }


}