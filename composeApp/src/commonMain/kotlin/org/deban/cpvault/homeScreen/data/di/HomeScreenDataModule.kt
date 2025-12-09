package org.deban.cpvault.homeScreen.data.di

import org.deban.cpvault.homeScreen.data.repository.HomeScreenRepositoryImpl
import org.deban.cpvault.homeScreen.domain.repository.HomeScreenRepository
import org.koin.dsl.module

fun getHomeScreenDataModule() = module {
    factory<HomeScreenRepository> {
        HomeScreenRepositoryImpl(leetCodeApiService = get())
    }
}