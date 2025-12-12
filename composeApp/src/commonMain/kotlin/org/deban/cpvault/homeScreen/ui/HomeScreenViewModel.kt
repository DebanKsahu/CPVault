package org.deban.cpvault.homeScreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserContestDetail
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserFullProfile
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserProfile
import org.deban.cpvault.homeScreen.domain.model.ContestStats
import org.deban.cpvault.homeScreen.domain.model.DomainLanguageStats
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeContestDetailUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeContestHistoryUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeFullProfileUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeProfileUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeUserLanguageStatsUseCase

class HomeScreenViewModel(
    private val getLeetcodeProfileUseCase: GetLeetcodeProfileUseCase,
    private val getLeetcodeContestHistoryUseCase: GetLeetcodeContestHistoryUseCase,
    private val getLeetcodeContestDetailUseCase: GetLeetcodeContestDetailUseCase,
    private val getLeetcodeFullProfileUseCase: GetLeetcodeFullProfileUseCase,
    private val getLeetcodeUserLanguageStatsUseCase: GetLeetcodeUserLanguageStatsUseCase
): ViewModel() {

    private val _leetcodeProfileUiState = MutableStateFlow(HomeScreen.LeetcodeProfileUiState())
    val leetcodeProfileUiState = this._leetcodeProfileUiState.asStateFlow()

    private val _leetcodeContestHistoryUiState = MutableStateFlow(HomeScreen.LeetcodeContestHistoryUiState())
    val leetcodeContestHistoryUiState = this._leetcodeContestHistoryUiState.asStateFlow()

    private val _leetcodeContestDetailUiState = MutableStateFlow(HomeScreen.LeetcodeContestDetailUiState())
    val leetcodeContestDetailUiState = this._leetcodeContestDetailUiState.asStateFlow()

    private val _leetcodeFullProfileUiState = MutableStateFlow(HomeScreen.LeetcodeFullProfileUiState())
    val leetcodeFullProfileUiState = this._leetcodeFullProfileUiState.asStateFlow()

    private val _leetcodeLanguageStatsUiState = MutableStateFlow(HomeScreen.LeetcodeLanguageStatsUiState())
    val leetcodeLanguageStatsUiState = this._leetcodeLanguageStatsUiState.asStateFlow()

    private val _leetcodeUsername = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _leetcodeUsername
                .filter { username -> username.isNotBlank() }
                .collectLatest { username ->
                    getLeetcodeProfileDetail(username = username)
                    getLeetcodeContestHistory(username = username)
                    getLeetcodeContestDetail(username = username)
                    getLeetcodeFullProfile(username = username)
                    getLeetcodeLanguageStats(username = username)
                }
        }
    }

    suspend fun getLeetcodeProfileDetail(username: String) {
        getLeetcodeProfileUseCase.invoke(username = username)
            .onStart {
                _leetcodeProfileUiState.update {oldState ->
                    oldState.copy(isLoading = true, data = null, error = "")
                }
            }.onEach { result ->
                result.onSuccess {
                    _leetcodeProfileUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = result.getOrNull(), error = "")
                    }
                }.onFailure { error ->
                    _leetcodeProfileUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = null, error = error.message.toString())
                    }
                }
            }.collect()
    }

    suspend fun getLeetcodeContestHistory(username: String) {
        getLeetcodeContestHistoryUseCase.invoke(username = username)
            .onStart {
                _leetcodeContestHistoryUiState.update {oldState ->
                    oldState.copy(isLoading = true, data = emptyList(), error = "")
                }
            }.onEach { result ->
                result.onSuccess {
                    _leetcodeContestHistoryUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = result.getOrNull() ?: emptyList(), error = "")
                    }
                }.onFailure { error ->
                    _leetcodeContestHistoryUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = emptyList(), error = error.message.toString())
                    }
                }
            }.collect()
    }

    suspend fun getLeetcodeContestDetail(username: String) {
        getLeetcodeContestDetailUseCase.invoke(username = username)
            .onStart {
                _leetcodeContestDetailUiState.update {oldState ->
                    oldState.copy(isLoading = true, data = null, error = "")
                }
            }.onEach { result ->
                result.onSuccess {
                    _leetcodeContestDetailUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = result.getOrNull(), error = "")
                    }
                }.onFailure { error ->
                    _leetcodeContestDetailUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = null, error = error.message.toString())
                    }
                }
            }.collect()
    }

    suspend fun getLeetcodeFullProfile(username: String) {
        getLeetcodeFullProfileUseCase.invoke(username = username)
            .onStart {
                _leetcodeFullProfileUiState.update {oldState ->
                    oldState.copy(isLoading = true, data = null, error = "")
                }
            }.onEach { result ->
                if (result.isSuccess) {
                    _leetcodeFullProfileUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = result.getOrNull(), error = "")
                    }
                } else {
                    _leetcodeFullProfileUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = null, error = result.exceptionOrNull()?.message
                            ?: "Unknown error")
                    }
                }
            }.collect()
    }

    suspend fun getLeetcodeLanguageStats(username: String) {
        getLeetcodeUserLanguageStatsUseCase.invoke(username = username)
            .onStart {
                _leetcodeLanguageStatsUiState.update {oldState ->
                    oldState.copy(isLoading = true, data = null, error = "")
                }
            }.onEach { result ->
                if (result.isSuccess) {
                    _leetcodeLanguageStatsUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = result.getOrNull(), error = "")
                    }
                } else {
                    _leetcodeLanguageStatsUiState.update {oldState ->
                        oldState.copy(isLoading = false, data = null, error = result.exceptionOrNull()?.message
                            ?: "Error From HomeScreen ViewModel")
                    }
                }
            }.collect()
    }
}

object HomeScreen {
    data class LeetcodeProfileUiState(
        val isLoading: Boolean = false,
        val data: LeetCodeUserProfile? = null,
        val error: String = ""
    )
    data class LeetcodeContestHistoryUiState(
        val isLoading: Boolean = false,
        val data: List<ContestStats> = emptyList(),
        val error: String = ""
    )
    data class LeetcodeContestDetailUiState(
        val isLoading: Boolean = false,
        val data: LeetCodeUserContestDetail? = null,
        val error: String = ""
    )
    data class LeetcodeFullProfileUiState(
        val isLoading: Boolean = false,
        val data: LeetCodeUserFullProfile? = null,
        val error: String = ""
    )
    data class LeetcodeLanguageStatsUiState(
        val isLoading: Boolean = false,
        val data: List<DomainLanguageStats>? = null,
        val error: String = ""
    )
}