package org.deban.cpvault.landingScreen.ui.di

import org.deban.cpvault.landingScreen.ui.LandingScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getLandingScreenUiModule() = module {
    viewModel {
        LandingScreenViewModel(getLeetCodeUsernameUseCase = get())
    }
}