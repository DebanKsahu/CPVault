package org.deban.cpvault.homeScreen.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository

class GetLeetcodeFullProfileUseCase(
    private val homeScreenRepository: HomeScreenRepository
) {
    operator fun invoke(username:  String) = flow {
        val fullProfile = homeScreenRepository.getLeetCodeFullUserProfile(username = username)
        if (fullProfile.isSuccess) {
            emit(fullProfile)
        } else {
            emit(Result.failure(fullProfile.exceptionOrNull() ?: Exception("Some error occured in usecase")))
        }
    }.catch {error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.Unconfined)
}