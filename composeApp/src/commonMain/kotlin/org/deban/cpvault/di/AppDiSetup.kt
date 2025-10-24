package org.deban.cpvault.di

import org.deban.cpvault.core.network.di.getCoreNetworkModule
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
            getLandingScreenUiModule()
        )
    }
}