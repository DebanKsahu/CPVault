package org.deban.cpvault.homeScreen.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository

class GetLeetcodeContestHistoryUseCase(
    private val homeScreenRepository: HomeScreenRepository
) {
    operator fun invoke(username: String) = flow {
        val contestHistory = homeScreenRepository.getLeetCodeUserContestHistory(username = username)
        if (contestHistory.isSuccess) {
            emit(Result.success(contestHistory.getOrThrow()))
        } else {
            emit(Result.failure(contestHistory.exceptionOrNull() ?: Exception("Something went wrong")))
        }
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Unconfined)
}