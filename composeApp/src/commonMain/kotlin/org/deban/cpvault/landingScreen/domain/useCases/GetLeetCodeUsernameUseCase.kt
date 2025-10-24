package org.deban.cpvault.landingScreen.domain.useCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.deban.cpvault.landingScreen.domain.repository.LandingScreenRepository

class GetLeetCodeUsernameUseCase(
    private val landingScreenRepository: LandingScreenRepository
) {
    operator fun invoke(username: String) = flow {
        val profile = landingScreenRepository.getLeetCodeUserProfile(username = username)
        if (profile.isSuccess) {
            emit(Result.success(profile.getOrThrow().username))
        } else {
            emit(Result.failure(profile.exceptionOrNull() ?: Exception("Some Exception occurred")))
        }
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Unconfined)
}