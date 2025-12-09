package org.deban.cpvault.homeScreen.domain.repository

import org.deban.cpvault.core.network.model.leetcode.LanguageStats
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserContestDetail
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserFullProfile
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserProfile
import org.deban.cpvault.homeScreen.domain.model.ContestStats
import org.deban.cpvault.homeScreen.domain.model.DomainLanguageStats

interface HomeScreenRepository {

    suspend fun getLeetCodeUserProfile(username: String): Result<LeetCodeUserProfile>

    suspend fun getLeetCodeUserContestDetail(username: String): Result<LeetCodeUserContestDetail>

    suspend fun getLeetCodeUserContestHistory(username: String): Result<List<ContestStats>>

    suspend fun getLeetCodeFullUserProfile(username: String): Result<LeetCodeUserFullProfile>

    suspend fun getLeetCodeUserLanguageStats(username: String): Result<List<DomainLanguageStats>>
}