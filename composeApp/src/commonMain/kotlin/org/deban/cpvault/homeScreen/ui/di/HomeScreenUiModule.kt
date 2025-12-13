package org.deban.cpvault.homeScreen.ui.di

import org.deban.cpvault.homeScreen.ui.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getHomeScreenUiModule() = module {
    viewModel {(username: String) ->
        HomeScreenViewModel(
            getLeetcodeProfileUseCase = get(),
            getLeetcodeContestHistoryUseCase = get(),
            getLeetcodeContestDetailUseCase = get(),
            getLeetcodeFullProfileUseCase = get(),
            getLeetcodeUserLanguageStatsUseCase = get(),
            username = username
        )
    }
}