package org.deban.cpvault.homeScreen.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository

class GetLeetcodeContestDetailUseCase(
    private val homeScreenRepository: HomeScreenRepository
) {
    operator fun invoke(username: String) = flow {
        val contestDetail = homeScreenRepository.getLeetCodeUserContestDetail(username = username)
        if (contestDetail.isSuccess) {
            emit(Result.success(contestDetail.getOrThrow()))
        } else {
            emit(Result.failure(contestDetail.exceptionOrNull() ?: Exception("Some exception occured")))
        }
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Unconfined)
}