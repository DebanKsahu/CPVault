package org.deban.cpvault.landingScreen.domain.repository

import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserProfile

interface LandingScreenRepository {

    suspend fun getLeetCodeUserProfile(username: String): Result<LeetCodeUserProfile>
}