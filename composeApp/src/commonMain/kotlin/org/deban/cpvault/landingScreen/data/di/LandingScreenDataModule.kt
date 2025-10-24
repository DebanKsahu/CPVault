package org.deban.cpvault.landingScreen.data.di

import org.deban.cpvault.landingScreen.data.repository.LandingScreenRepositoryImpl
import org.deban.cpvault.landingScreen.domain.repository.LandingScreenRepository
import org.koin.dsl.module

fun getLandingScreenDataModule() = module {
    factory<LandingScreenRepository> {
        LandingScreenRepositoryImpl(leetcodeApiService = get())
    }
}