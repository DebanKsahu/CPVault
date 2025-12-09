package org.deban.cpvault.di

import org.deban.cpvault.core.network.di.getCoreNetworkModule
import org.deban.cpvault.homeScreen.data.di.getHomeScreenDataModule
import org.deban.cpvault.homeScreen.domain.di.getHomeScreenDomainModule
import org.deban.cpvault.homeScreen.ui.di.getHomeScreenUiModule
import org.deban.cpvault.landingScreen.data.di.getLandingScreenDataModule
import org.deban.cpvault.landingScreen.domain.di.getLandingScreenDomainModule
import org.deban.cpvault.landingScreen.ui.di.getLandingScreenUiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(
    koinApplication: ((KoinApplication) -> Unit)? = null
) {
    startKoin {
        modules(
            getCoreNetworkModule(),

            getLandingScreenDataModule(),
            getLandingScreenDomainModule(),
            getLandingScreenUiModule(),

            getHomeScreenDataModule(),
            getHomeScreenDomainModule(),
            getHomeScreenUiModule()
        )
    }
}