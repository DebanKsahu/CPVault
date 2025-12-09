package org.deban.cpvault.homeScreen.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository

class GetLeetcodeUserLanguageStatsUseCase(
    private val homeScreenRepository: HomeScreenRepository
) {
    operator fun invoke(username: String) = flow {
        val profile = homeScreenRepository.getLeetCodeUserLanguageStats(username = username)
        emit(profile)
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Unconfined)
}