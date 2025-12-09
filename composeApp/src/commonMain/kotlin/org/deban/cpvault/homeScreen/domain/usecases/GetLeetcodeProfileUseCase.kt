package org.deban.cpvault.homeScreen.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository

class GetLeetcodeProfileUseCase(
    private val homeScreenRepository: HomeScreenRepository
) {
    operator fun invoke(username: String) = flow {
        val profile = homeScreenRepository.getLeetCodeUserProfile(username = username)
        if (profile.isSuccess) {
            emit(profile)
        } else {
            emit(Result.failure(profile.exceptionOrNull() ?: Exception("Some exception occured during profile retrival")))
        }
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Unconfined)
}