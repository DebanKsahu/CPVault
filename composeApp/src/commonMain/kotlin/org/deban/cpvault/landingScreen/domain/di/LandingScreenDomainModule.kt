package org.deban.cpvault.landingScreen.domain.di

import org.deban.cpvault.landingScreen.domain.useCases.GetLeetCodeUsernameUseCase
import org.koin.dsl.module

fun getLandingScreenDomainModule() = module {
    factory {
        GetLeetCodeUsernameUseCase(landingScreenRepository = get())
    }
}