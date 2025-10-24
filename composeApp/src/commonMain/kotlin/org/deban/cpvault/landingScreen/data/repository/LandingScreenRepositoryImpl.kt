package org.deban.cpvault.landingScreen.data.repository

import org.deban.cpvault.core.network.apiService.LeetCodeApiService
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserProfile
import org.deban.cpvault.landingScreen.domain.repository.LandingScreenRepository

class LandingScreenRepositoryImpl(
    private val leetcodeApiService: LeetCodeApiService
): LandingScreenRepository {

    override suspend fun getLeetCodeUserProfile(username: String): Result<LeetCodeUserProfile> {
        val result = leetcodeApiService.getUserProfile(username = username)
        return if (result.isSuccess) {
            Result.success(result.getOrThrow())
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("username not available"))
        }
    }
}