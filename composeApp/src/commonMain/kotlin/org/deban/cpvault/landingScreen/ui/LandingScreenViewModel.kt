package org.deban.cpvault.landingScreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.deban.cpvault.landingScreen.domain.useCases.GetLeetCodeUsernameUseCase

@OptIn(FlowPreview::class)
class LandingScreenViewModel(
    private val getLeetCodeUsernameUseCase: GetLeetCodeUsernameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(LandingScreen.UiState())
    val uiState = _uiState.asStateFlow()

    private val _leetcodeUsername = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _leetcodeUsername
                .filter { username -> username.isNotBlank() }
                .distinctUntilChanged()
                .debounce(1000)
                .collectLatest { username ->
                    verifyLeetCodeUsername(username = username)
                }
        }
    }

    fun updateLeetCodeUsername(username: String) {
        _leetcodeUsername.update {
            username
        }
    }

    fun verifyLeetCodeUsername(username: String) {
        viewModelScope.launch {
            getLeetCodeUsernameUseCase.invoke(username = username)
                .onStart {
                    _uiState.update {
                        LandingScreen.UiState(isLoading = true)
                    }
                }.onEach { result ->
                    result.onSuccess {
                        _uiState.update {
                            LandingScreen.UiState(isUsernameAvailable = true)
                        }
                    }.onFailure { error ->
                        _uiState.update {
                            LandingScreen.UiState(
                                error = error.message.toString(),
                                isUsernameAvailable = false
                            )
                        }
                    }
                }.collect()
        }
    }
}


object LandingScreen {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val isUsernameAvailable: Boolean? = null
    )
}